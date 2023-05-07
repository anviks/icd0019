package reflection.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.URLEncoder;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Serializer {

    public String serialize(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> field.setAccessible(true));

        Pattern pattern = Pattern.compile("%([0-9A-F]{2})");
        Function<String, String> encode = string -> pattern.matcher(URLEncoder.encode(string, StandardCharsets.UTF_8))
                .replaceAll(matchResult -> matchResult.group().toLowerCase());

        List<String> pairs = new ArrayList<>();
        for (Field field : fields) {
            String pair;
            try {
                pair = encode.apply(field.getName()) + ":" + encode.apply(field.get(instance).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            pairs.add(pair);
        }

        return String.join("|", pairs);
    }

    public <T> T deserialize(String inputString,
                            Class<T> clazz) {

        Function<String, String> decode = s -> URLDecoder.decode(s, StandardCharsets.UTF_8);
        Function<String, String> grabValues = s -> s.split(":")[1];

        String[] args = Arrays.stream(inputString.split("\\|"))
                .map(grabValues)
                .map(decode)
                .toList()
                .toArray(new String[0]);
        System.out.println(Arrays.toString(args));

        T object;
        try {
            object = clazz.getDeclaredConstructor(String.class, String.class).newInstance((Object[]) args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return object;
    }

}

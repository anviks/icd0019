package reflection.tester;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    private final List<String> results = new ArrayList<>();

    public void runTests(List<String> testClassNames) {
        for (String testClass : testClassNames) {
            try {
                Class<?> c = Class.forName(testClass);
                Object instance = c.getConstructor().newInstance();
                Arrays.stream(c.getDeclaredMethods()).forEach(method -> {
                    if (method.getAnnotation(MyTest.class) != null) {
                        runTest(method, instance);
                    }
                });
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runTest(Method method, Object instance) {
        String result = method.getName() + "() - ";
        MyTest annotation = method.getAnnotation(MyTest.class);

        try {
            method.invoke(instance);
            result += annotation.expected() == MyTest.None.class ? "OK" : "FAILED";
        } catch (InvocationTargetException e) {
            Class<? extends Throwable> expected = annotation.expected();
            result += expected.isAssignableFrom(e.getCause().getClass()) ? "OK" : "FAILED";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        results.add(result);
    }

    public String getResult() {
        return String.join("\n", results);
    }
}

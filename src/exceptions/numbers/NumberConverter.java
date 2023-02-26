package exceptions.numbers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class NumberConverter {

    private final Properties properties;

    public NumberConverter(String lang) {

        String filePath = "src/exceptions/numbers/numbers_" + lang + ".properties";
        properties = new Properties();
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            properties.load(reader);
        } catch (IOException e) {
            throw new MissingLanguageFileException(lang, e);
        } catch (IllegalArgumentException e) {
            throw new BrokenLanguageFileException(lang, e);
        } finally {
            close(stream);
        }

        String necessaryProperties = """
                0
                1
                2
                3
                4
                5
                6
                7
                8
                9
                teen
                tens-suffix
                tens-after-delimiter
                hundred
                hundreds-before-delimiter
                hundreds-after-delimiter
                """;

        for (String key : necessaryProperties.split("\n")) {
            if (!properties.containsKey(key)) {
                throw new MissingTranslationException(lang);
            }
        }
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {
        }
    }

    public String numberInWords(Integer number) {
        String num = number.toString();

        switch (num.length()) {
            case 3 -> {
                return dealWithHundreds(num);
            }
            case 2 -> {
                return dealWithTens(num, "");
            }
            case 1 -> {
                return dealWithOnes(num, "");
            }
        }

        return null;
    }


    private String dealWithHundreds(String num) {
        if (properties.getProperty(num) != null) {
            return properties.getProperty(num);
        }

        String result;

        if (properties.getProperty(num.charAt(0) + "00") != null) {
            result = properties.getProperty(num.charAt(0) + "00");
        } else {
            result = properties.getProperty(num.substring(0, 1))
                     + properties.getProperty("hundreds-before-delimiter")
                     + properties.getProperty("hundred");
        }

        String withAfterDelimiter = result + properties.getProperty("hundreds-after-delimiter");

        if (num.charAt(1) == '0') {
            return num.charAt(2) == '0'
                    ? result
                    : dealWithOnes(num.substring(2), withAfterDelimiter);
        }

        return dealWithTens(num.substring(1), withAfterDelimiter);
    }

    private String dealWithTens(String num, String result) {
        if (properties.getProperty(num) != null) {
            return result + properties.getProperty(num);
        }

        if (num.charAt(0) == '1') {
            return result
                   + properties.getProperty(num.substring(1))
                   + properties.getProperty("teen");
        }

        if (properties.getProperty(num.charAt(0) + "0") != null) {
            result += properties.getProperty(num.charAt(0) + "0");
        } else {
            result += properties.getProperty(num.substring(0, 1))
                      + properties.getProperty("tens-suffix");
        }

        if (num.charAt(1) == '0') {
            return result;
        }

        result += properties.getProperty("tens-after-delimiter");
        return dealWithOnes(num.substring(1), result);
    }

    private String dealWithOnes(String num, String result) {
        return result + properties.getProperty(num);
    }
}

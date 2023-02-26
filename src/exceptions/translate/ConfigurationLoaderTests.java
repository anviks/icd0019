package exceptions.translate;

import org.junit.Test;

public class ConfigurationLoaderTests {

    @Test
    public void exceptionTranslationExample() throws Exception {
        String configPath = "./";
        String configPath2 = "./hello";

        new ConfigurationLoader().loadConfiguration(configPath);
        new ConfigurationLoader().loadConfiguration(configPath2);
    }

}

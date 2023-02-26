package exceptions.channel;

import java.io.IOException;

public class Program {

    ConstantProvider provider = new ConstantProvider();

    public static void main(String[] args) {

        Program program = new Program();

//        program.provider.makeItThrowMissingConstantException();
//        program.provider.makeItThrowCorruptConfigurationException();

        program.main(7); // 7 is arbitrary value
    }

    public void main(int input) {
        String formatted;

        try {
            double result = calculate(input);
            formatted = format(String.valueOf(result));
        } catch (MissingConstantException e) {
            formatted = formatError("Constant is missing");
        } catch (CorruptConfigurationException e) {
            formatted = formatError("Configuration file is corrupt");
        } catch (IOException e) {
            formatted = formatError(e.getMessage());
        }

        present(formatted);

    }

    private double calculate(int input) throws IOException {
        // an arbitrary calculation that uses some
        // data from external source

        return (input + 42) * provider.getMultiplier2();
    }

    private String format(String data) {
        return "### Result is %s ###".formatted(data);
    }

    private String formatError(String message) {
        return "### Error: %s ###".formatted(message);
    }

    private void present(String data) {
        System.out.println(data);
    }
}

package fp;

import org.junit.Test;

public class Timer {

    @Test
    public void measuresExecutionTime() {

        executeAndTime(() -> methodToBeTimed());

    }

    private void executeAndTime(Runnable code) {
        long start = System.currentTimeMillis();

        code.run();

        double passedMills = System.currentTimeMillis() - start;
        String result = String.format("%ss", passedMills / 1000);
        System.out.println(result);
    }

    public void howToMeasureExecutionTimeExample() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1e9; i++) {
            i++;
            i--;
        }

        double passedMills = System.currentTimeMillis() - start;
        String result = String.format("%s s", passedMills / 1000);
        System.out.println(result);
    }


    private void methodToBeTimed() {
        for (int i = 0; i < 1e9; i++) {
            i++;
            i--;
        }
    }

}

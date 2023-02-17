package oo.hide;

public class Timer {

    public long time = System.currentTimeMillis();

    public String getPassedTime() {
        return (System.currentTimeMillis() - time) / 1000 + "";
    }
}

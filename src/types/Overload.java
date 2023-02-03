package types;

public class Overload {

    public static void main(String[] args) {
        System.out.println(add(1L, 4L));
        System.out.println(add(1, 2));
        System.out.println(add("2", "8"));
    }

    public static long add(long x, long y) {
        System.out.println("Adding longs");
        return x + y;
    }

    public static int add(int x, int y) {
        System.out.println("Adding integers");
        return x + y;
    }

    public static long add(String x, String y) {
        System.out.println("Adding numbers from strings");
        return Long.parseLong(x) + Long.parseLong(y);
    }

}

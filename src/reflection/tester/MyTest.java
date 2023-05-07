package reflection.tester;

public @interface MyTest {
    Class<? extends Throwable> expected() default None.class;

    class None extends Throwable {}
}

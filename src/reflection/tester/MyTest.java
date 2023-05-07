package reflection.tester;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
    Class<? extends Throwable> expected() default None.class;

    class None extends Throwable {}
}

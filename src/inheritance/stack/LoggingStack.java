package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer> {
    @Override
    public Integer push(Integer item) {
        System.out.println("Push method was called.");
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {
        System.out.println("Pop method was called.");
        return super.pop();
    }

    public void pushAll(Integer... numbers) {
        for (Integer num : numbers) {
            super.push(num);
        }
    }
}

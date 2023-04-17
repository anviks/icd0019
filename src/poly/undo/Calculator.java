package poly.undo;

import java.util.Stack;
import java.util.function.Function;

public class Calculator {

    private double value;
    private Stack<Function<Double, Double>> undoStack = new Stack<>();

    public void input(double value) {
        undoStack.push(e -> 0d);
        this.value = value;
    }

    public void add(double addend) {
        undoStack.push(e -> e - addend);
        value += addend;
    }

    public void multiply(double multiplier) {
        undoStack.push(e -> e / multiplier);
        value *= multiplier;
    }

    public double getResult() {
        return value;
    }

    public void undo() {
        value = undoStack.pop().apply(value);
    }
}

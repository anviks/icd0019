package junit.sales;

import java.lang.reflect.Array;

public class DynamicArray<E> {
    private Object[] array;

    public DynamicArray() {
        this.array = new Object[0];
    }

    public void add(E element) {
        Object[] newArray = new Object[array.length + 1];
        int i = 0;

        for (Object item : array) {
            newArray[i++] = item;
        }

        newArray[i] = element;

        array = newArray;
    }

    public boolean contains(Object element) {
        for (Object item : array) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    public Integer indexOf(Object element) {
        if (!this.contains(element)) {
            return null;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }

        return null;
    }

    public int length() {
        return array.length;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) array[index];
    }

    public void set(int index, E element) {
        array[index] = element;
    }

    public void insert(int index, E element) {
        Object[] newArray = new Object[array.length + 1];
        int inserted = 0;

        for (int i = 0; i < newArray.length; i++) {

            if (i == index && inserted == 0) {
                newArray[i] = element;
                inserted = 1;
            } else {
                newArray[i] = array[i - inserted];
            }

        }

        array = newArray;
    }

    public void remove(int index) {
        Object[] newArray = new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        array = newArray;
    }

    public void clear() {
        array = new Object[0];
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < this.length()) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.length());
        }

        for (int i = 0; i < this.length(); i++) {
            array[i] = (T) this.array[i];
        }

        if (array.length > this.length()) {
            array[this.length()] = null;
        }

        return array;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (Object element : array) {
            builder.append(element).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
}

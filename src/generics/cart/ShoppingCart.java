package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {
    private final List<T> content = new ArrayList<>();
    private final List<Double> discounts = new ArrayList<>();

    public void add(T item) {
        content.add(item);
    }

    public void removeById(String id) {
        content.removeIf(item -> item.getId().equals(id));
    }

    public Double getTotal() {
        return content.stream().mapToDouble(T::getPrice).sum() * discounts.stream().reduce(1.0, (a, b) -> a * b);
    }

    public void increaseQuantity(String id) {
        add(content.stream().filter(item -> item.getId().equals(id)).findAny().orElseThrow());
    }

    public void applyDiscountPercentage(Double discount) {
        discounts.add((100 - discount) / 100);
    }

    public void removeLastDiscount() {
        discounts.remove(discounts.size() - 1);
    }

    public void addAll(List<T> items) {
        content.addAll(items);
    }

    private Double getPrice(String id) {
        return content.stream().filter(item -> Objects.equals(item.getId(), id)).findAny().orElseThrow().getPrice();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String id : content.stream().map(T::getId).distinct().toList()) {
            builder.append("(")
                    .append(id)
                    .append(", ")
                    .append(getPrice(id))
                    .append(", ")
                    .append(Collections.frequency(content.stream().map(T::getId).toList(), id))
                    .append(")")
                    .append(", ");
        }

        return builder.substring(0, builder.length() - 2);
    }
}

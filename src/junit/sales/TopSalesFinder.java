package junit.sales;


public class TopSalesFinder {

    public DynamicArray<SalesRecord> sales;

    public TopSalesFinder() {
        this.sales = new DynamicArray<>();
    }

    public void registerSale(SalesRecord record) {
        sales.add(record);
    }

    public String[] findItemsSoldOver(int amount) {
        DynamicArray<String> productIds = new DynamicArray<>();
        DynamicArray<Integer> productEarnings = new DynamicArray<>();
        DynamicArray<String> filteredItems = new DynamicArray<>();

        for (int i = 0; i < sales.length(); i++) {
            String productId = sales.get(i).getProductId();
            int price = sales.get(i).getProductPrice();
            int count = sales.get(i).getItemsSold();

            if (!productIds.contains(productId)) {
                productIds.add(productId);
                productEarnings.add(price * count);
            } else {
                int index = productIds.indexOf(productId);
                int originalValue = productEarnings.get(index);
                productEarnings.set(index, originalValue + price * count);
            }
        }

        for (int i = 0; i < productEarnings.length(); i++) {
            if (productEarnings.get(i) > amount) {
                filteredItems.add(productIds.get(i));
            }
        }

        return filteredItems.toArray(new String[filteredItems.length()]);
    }

}



package inheritance.analyser;

import java.util.HashMap;
import java.util.List;

public sealed abstract class AbstractAnalyser permits DifferentiatedTaxSalesAnalyser, FlatTaxSalesAnalyser, TaxFreeSalesAnalyser {
    private final List<SalesRecord> records;
    protected Double vat = 0.2;

    public AbstractAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    public Double getTotalSales() {
        double earnings = 0.0;

        return getEarnings(earnings, records);
    }

    public Double getTotalSalesByProductId(String id) {
        double earnings = 0.0;
        List<SalesRecord> filteredRecords = records.stream()
                .filter(e -> e.getProductId().equals(id))
                .toList();

        return getEarnings(earnings, filteredRecords);
    }

    private Double getEarnings(double earnings, List<SalesRecord> records) {
        for (SalesRecord record : records) {
            if (differentiated() && record.hasReducedRate()) {
                earnings += record.getItemsSold() * record.getProductPrice() / 1.1;
            } else {
                earnings += record.getItemsSold() * record.getProductPrice() / (1 + vat);
            }
        }

        return earnings;
    }

    public String getIdOfMostPopularItem() {
        HashMap<String, Integer> popularity = new HashMap<>();
        String mostPopular = null;

        for (SalesRecord record : records) {
            String id = record.getProductId();
            int soldItems = popularity.getOrDefault(id, 0) + record.getItemsSold();

            if (soldItems > popularity.getOrDefault(mostPopular, 0)) {
                mostPopular = id;
            }

            popularity.put(id, soldItems);
        }

        return mostPopular;
    }

    public String getIdOfItemWithLargestTotalSales() {
        HashMap<String, Double> itemEarnings = new HashMap<>();
        String mostEarned = null;

        for (SalesRecord record : records) {
            String id = record.getProductId();
            double earnings;

            if (differentiated() && record.hasReducedRate()) {
                earnings = itemEarnings.getOrDefault(id, 0.0) + record.getItemsSold() * record.getProductPrice() / 1.1;
            } else {
                earnings = itemEarnings.getOrDefault(id, 0.0) + record.getItemsSold() * record.getProductPrice() / (1 + vat);
            }

            if (earnings > itemEarnings.getOrDefault(mostEarned, 0.0)) {
                mostEarned = id;
            }

            itemEarnings.put(id, earnings);
        }

        return mostEarned;
    }

    protected abstract boolean differentiated();

}

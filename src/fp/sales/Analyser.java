package fp.sales;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {
        return repository.getEntries().stream().mapToDouble(Entry::getAmount).sum();
    }

    public Double getSalesByCategory(String category) {
        return repository.getEntries().stream()
                .filter(entry -> entry.getCategory().equals(category))
                .mapToDouble(Entry::getAmount)
                .sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.getEntries().stream()
                .filter(entry -> entry.getDate().isAfter(start) && entry.getDate().isBefore(end))
                .mapToDouble(Entry::getAmount)
                .sum();
    }

    public String mostExpensiveItems() {
        return repository.getEntries().stream()
                .sorted(Comparator.comparingDouble(Entry::getAmount).reversed())
                .limit(3)
                .map(Entry::getProductId)
                .sorted()
                .reduce((s, s2) -> s + ", " + s2)
                .orElse("");
    }

    public String statesWithBiggestSales() {
        return String.join(", ",
                repository.getEntries().stream()
                        .collect(Collectors.toMap(
                                Entry::getState,
                                Entry::getAmount,
                                Double::sum
                        ))
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingDouble(mapEntry -> -mapEntry.getValue()))
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .toList());
    }
}

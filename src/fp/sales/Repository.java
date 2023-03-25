package fp.sales;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {
        List<List<String>> content;
        try {
            content = Files.readAllLines(Path.of(FILE_PATH), StandardCharsets.UTF_8)
                    .stream()
                    .map(s -> List.of(s.split("\t")))
                    .skip(1)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.stream()
                .map(row -> {
                    Entry entry = new Entry();
                    entry.setDate(LocalDate.parse(row.get(0), formatter));
                    entry.setState(row.get(1));
                    entry.setProductId(row.get(2));
                    entry.setCategory(row.get(3));
                    entry.setAmount(Double.valueOf(
                            row.get(5).replace(",", ".")
                    ));
                    return entry;
                })
                .toList();
    }

}

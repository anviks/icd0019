package poly.customer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    public List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            String[] info = line.split(";");
            if (info[0].equalsIgnoreCase("regular")) {
                customers.add(new RegularCustomer(info[1], info[2], Integer.parseInt(info[3]), LocalDate.parse(info[4])));
            } else {
                customers.add(new GoldCustomer(info[1], info[2], Integer.parseInt(info[3])));
            }
        }
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return customers.stream().filter(c -> c.id.equalsIgnoreCase(id)).findFirst();
    }

    public void remove(String id) {
        customers.removeIf(c -> c.id.equals(id));
        updateFile();
    }

    public void save(AbstractCustomer customer) {
        if (getCustomerById(customer.id).isPresent()) {
            remove(customer.id);
        }
        customers.add(customer);
        updateFile();
    }

    private void updateFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(String.join("\n", customers.stream().map(AbstractCustomer::asString).toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCustomerCount() {
        return customers.size();
    }
}

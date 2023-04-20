package poly.installments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class InstallmentGenerator {

    public List<Installment> generateRowsFor(
            Integer amount, LocalDate periodStart, LocalDate periodEnd) {

        // Find dates where payment is necessary.
        List<LocalDate> dates = new ArrayList<>(
                periodStart.datesUntil(periodEnd)
                        .filter(localDate -> localDate.getDayOfMonth() == 1)
                        .toList()
        );

        if (!dates.contains(periodStart)) {
            dates.add(0, periodStart);
        }

        int payment = amount / dates.size();

        // Minimum payment is 3, therefore if payment resulted in a smaller value,
        // remove the last date from the list and find payment again.
        while (payment < 3 && dates.size() > 1) {
            dates.remove(dates.size() - 1);
            payment = amount / dates.size();
        }

        List<Integer> payments = new ArrayList<>(Collections.nCopies(dates.size(), payment));
        int remainder = amount - payments.stream().mapToInt(e -> e).sum();

        // Iterate through the list backwards and increase payment amounts until remainder is 0.
        for (int i = 0; i < remainder; i++) {
            int index = payments.size() - 1 - i;
            int previousValue = payments.get(index);
            payments.set(index, previousValue + 1);
        }

        return IntStream.range(0, payments.size())
                .mapToObj(i -> new Installment(payments.get(i), dates.get(i)))
                .toList();
    }
}

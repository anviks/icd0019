package poly.customer;

public class BonusCollector {

    private CustomerRepository repository;

    public BonusCollector(CustomerRepository repository) {
        this.repository = repository;
    }

    public void gatherCustomerBonus(String customerId, Order order) {

        repository.getCustomerById(customerId).ifPresent(customer -> {
            System.out.println(customer.bonusPoints);
            customer.collectBonusPointsFrom(order);
            System.out.println(customer.bonusPoints);
            repository.save(customer);
            System.out.println(new CustomerRepository().getCustomerById(customerId).get().bonusPoints);
        });
    }


}

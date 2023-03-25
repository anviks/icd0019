package fp;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;


public class Persons {

    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById() {
        Person person = persons.stream().filter(p -> p.getId() == 2).findFirst().orElse(null);
    }

    @Test
    public void removePersonById() {
        persons.removeIf(person -> person.getId() == 2);
    }

    @Test
    public void findsPersonNames() {
        String names = String.join(", ", persons.stream().map(Person::getName).toList());
    }

    @Test
    public void reverseSortedByAge() {
        persons.sort(Comparator.comparingInt(Person::getAge).reversed());
    }
}

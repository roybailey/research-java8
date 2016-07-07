package me.roybailey.research.nomads;

import com.google.common.reflect.ClassPath;
import org.junit.Test;

import java.io.IOException;

import static java.util.Arrays.asList;

/**
 * https://dzone.com/articles/functor-and-monad-examples-in-plain-java?utm_content=buffer7227c&utm_medium=social&utm_source=twitter.com&utm_campaign=buffer
 */
public class FunctorTest {

    @Test
    public void testFunctor() throws IOException {
        Identity<String> idString = new Identity<>("abc");
        Identity<Integer> idInt = idString.map(String::length);
    }

    @Test
    public void testFunctorChaining() {
        Customer customer = new Customer("Anna", new Address("18", "Garrods", "Ipswich", "UK"));
        Identity<byte[]> idBytes = new Identity<>(customer)
                .map(Customer::getAddress)
                .map(Address::getStreet)
                .map((String s) -> s.substring(0, 3))
                .map(String::toLowerCase)
                .map(String::getBytes);
    }

    @Test
    public void testFunctorList() {
        Customer cust1 = new Customer("Anna", new Address("18", "Garrods", "Ipswich", "UK"));
        Customer cust2 = new Customer("George", new Address("11", "Harold Rd", "Newcastle", "UK"));
        FList<Customer> customers = new FList<>(asList(cust1, cust2));
        // this didn't compile, so stopping here
//        FList<String> streets = customers
//                .map(Customer::getAddress)
//                .map(Address::getStreet);
    }
}

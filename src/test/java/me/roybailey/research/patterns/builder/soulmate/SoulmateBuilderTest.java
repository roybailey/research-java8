package me.roybailey.research.patterns.builder.soulmate;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author roybailey
 */
public class SoulmateBuilderTest {

    @Test
    public void testNestedBuilder() {

        Address home = Address.Builder()
                .withAddress("1 High Street", "Somewhere", "Bigcity")
                .withPostcode("1HS 1BC")
                .build();

        assertThat(home.getAddress().size(), is(3));
        assertThat(home.getAddress().get(0), is("1 High Street"));
        assertThat(home.getAddress().get(1), is("Somewhere"));
        assertThat(home.getAddress().get(2), is("Bigcity"));
        assertThat(home.getPostcode(), is("1HS 1BC"));

        Address work = Address.Builder()
                .withAddress("1 Business Park", "Industrial Estate", "Working")
                .withPostcode("1BP 1IE")
                .build();

        assertThat(work.getAddress().size(), is(3));
        assertThat(work.getAddress().get(0), is("1 Business Park"));
        assertThat(work.getAddress().get(1), is("Industrial Estate"));
        assertThat(work.getAddress().get(2), is("Working"));
        assertThat(work.getPostcode(), is("1BP 1IE"));

        AddressBook addressBook = AddressBook.Builder()
                .withPrimaryAddress("home", home)
                .withAddress("work", work)
                .build();

        assertThat(addressBook.getAddresses().size(), is(2));
        assertThat(addressBook.getPrimaryAddressAlias(), is("home"));
        assertThat(addressBook.getAddress("home").getPostcode(), is("1HS 1BC"));
        assertThat(addressBook.getAddress("work").getPostcode(), is("1BP 1IE"));
        assertThat(addressBook.getPrimaryAddressAlias(), is("home"));
        
        Person person = Person.Builder()
                .withName("Anna")
                .withAge(20)
                .withAddressBook(addressBook)
                .build();

        assertThat(person.getName(), is("Anna"));
        assertThat(person.getAge(), is(20));

        AddressBook actualAddressBook = person.getAddressBook();
        assertThat(actualAddressBook.getAddresses().size(), is(2));
        assertThat(actualAddressBook.getPrimaryAddressAlias(), is("home"));
        assertThat(actualAddressBook.getAddress("home").getPostcode(), is("1HS 1BC"));
        assertThat(actualAddressBook.getAddress("work").getPostcode(), is("1BP 1IE"));
        assertThat(actualAddressBook.getPrimaryAddressAlias(), is("home"));

        Address actualHomeAddress = actualAddressBook.getPrimaryAddress();
        assertThat(actualHomeAddress.getAddress().size(), is(3));
        assertThat(actualHomeAddress.getAddress().get(0), is("1 High Street"));
        assertThat(actualHomeAddress.getAddress().get(1), is("Somewhere"));
        assertThat(actualHomeAddress.getAddress().get(2), is("Bigcity"));
        assertThat(actualHomeAddress.getPostcode(), is("1HS 1BC"));

    }
}

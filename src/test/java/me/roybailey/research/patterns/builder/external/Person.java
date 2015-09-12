package me.roybailey.research.patterns.builder.external;

/**
 * Person object with address book.
 */
public class Person {

    private String name;
    private Integer age;
    private AddressBook addressBook;

    public Person(String name, Integer age, AddressBook addressBook) {
        this.name = name;
        this.age = age;
        this.addressBook = addressBook;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

}

package me.roybailey.research.patterns.builder.external;

/**
 * External Person Builder object.
 */
public class PersonBuilder {

    private String name;
    private Integer age;
    private AddressBook addressBook;

    public PersonBuilder() {
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public PersonBuilder withAddressBook(AddressBook addressBook) {
        this.addressBook = new AddressBook(addressBook);
        return this;
    }

    public Person build() {
        return new Person(name, age, addressBook);
    }
}

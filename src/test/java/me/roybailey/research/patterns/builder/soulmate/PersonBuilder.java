package me.roybailey.research.patterns.builder.soulmate;

/**
 * PersonBuilder object with package level access to Person access.
 */
public class PersonBuilder {

    private Person template = new Person();

    protected PersonBuilder() {
    }

    public PersonBuilder withName(String name) {
        this.template.name = name;
        return this;
    }

    public PersonBuilder withAge(Integer age) {
        this.template.age = age;
        return this;
    }

    public PersonBuilder withAddressBook(AddressBook addressBook) {
        this.template.addressBook = new AddressBook(addressBook);
        return this;
    }

    public Person build() {
        return new Person(template);
    }
}

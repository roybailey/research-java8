package me.roybailey.research.patterns.builder.soulmate;

/**
 * Person object with address book.
 */
public class Person {

    public static PersonBuilder Builder() {
        return new PersonBuilder();
    }

    /**
     * Package level member variables for PersonBuilder to use.
     */
    String name;
    Integer age;
    AddressBook addressBook;

    /**
     * Package level constructor for PersonBuilder to use.
     */
    Person() {
    }

    public Person(Person template) {
        this(   template.getName(),
                template.getAge(),
                template.getAddressBook());
    }

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

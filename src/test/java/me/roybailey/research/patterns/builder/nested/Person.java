package me.roybailey.research.patterns.builder.nested;

/**
 * Person object with address book.
 */
public class Person {

    public static class Builder {

        private String name;
        private Integer age;
        private AddressBook addressBook;

        public Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder withAddressBook(AddressBook addressBook) {
            this.addressBook = new AddressBook(addressBook);
            return this;
        }

        public Person build() {
            return new Person(name, age, addressBook);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

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

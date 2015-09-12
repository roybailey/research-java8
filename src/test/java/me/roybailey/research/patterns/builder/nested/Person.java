package me.roybailey.research.patterns.builder.nested;

/**
 * Immutable Person object with address book.
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return !(age != null ? !age.equals(person.age) : person.age != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
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

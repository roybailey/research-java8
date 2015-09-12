package me.roybailey.research.patterns.builder.soulmate;

/**
 * Immutable Person object with address book.
 */
public class Person {

    public static PersonBuilder Builder() {
        return new PersonBuilder();
    }

    String name;
    Integer age;
    AddressBook addressBook;

    Person() {
    }

    public Person(Person template) {
        this(template.getName(), template.getAge(), template.getAddressBook());
    }

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

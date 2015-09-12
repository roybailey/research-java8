package me.roybailey.research.patterns.builder.soulmate;

/**
 * AddressBookBuilder object with package level access to AddressBook access.
 */
public class AddressBookBuilder {

    private AddressBook template = new AddressBook();

    protected AddressBookBuilder() {
    }

    public AddressBookBuilder withAddress(String alias, Address address) {
        this.template.addresses.put(alias, address);
        return this;
    }

    public AddressBookBuilder withPrimaryAddress(String alias, Address address) {
        this.withAddress(alias, address).withPrimaryAddressAlias(alias);
        return this;
    }

    private AddressBookBuilder withPrimaryAddressAlias(String alias) {
        this.template.primaryAddressAlias = alias;
        return this;
    }

    public AddressBook build() {
        return new AddressBook(template);
    }

}

package me.roybailey.research.patterns.builder.soulmate;

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable AddressBook object to capture collection of aliases addresses.
 */
public class AddressBookBuilder {

    private Map<String, Address> addresses = new HashMap<>();
    private String primaryAddress;

    protected AddressBookBuilder() {
    }

    public AddressBookBuilder withAddress(String alias, Address address) {
        this.addresses.put(alias, address);
        return this;
    }

    public AddressBookBuilder withPrimaryAddress(String alias, Address address) {
        this.withAddress(alias, address).withPrimaryAddress(alias);
        return this;
    }

    private AddressBookBuilder withPrimaryAddress(String alias) {
        this.primaryAddress = alias;
        return this;
    }

    public AddressBook build() {
        return new AddressBook(addresses, primaryAddress);
    }

}
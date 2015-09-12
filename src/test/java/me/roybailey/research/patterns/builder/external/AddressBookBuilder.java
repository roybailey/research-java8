package me.roybailey.research.patterns.builder.external;

import java.util.HashMap;
import java.util.Map;

/**
 * AddressBook object to capture collection of aliases addresses.
 */
public class AddressBookBuilder {

    private Map<String, Address> addresses = new HashMap<>();
    private String primaryAddress;

    public AddressBookBuilder() {
    }

    public AddressBookBuilder withAddress(String alias, Address address) {
        this.addresses.put(alias, address);
        return this;
    }

    public AddressBookBuilder withPrimaryAddress(String alias, Address address) {
        this.withAddress(alias, address).withPrimaryAddressAlias(alias);
        return this;
    }

    private AddressBookBuilder withPrimaryAddressAlias(String alias) {
        this.primaryAddress = alias;
        return this;
    }

    public AddressBook build() {
        return new AddressBook(addresses, primaryAddress);
    }

}

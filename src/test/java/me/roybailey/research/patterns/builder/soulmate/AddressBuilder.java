package me.roybailey.research.patterns.builder.soulmate;

import java.util.Arrays;

/**
 * AddressBuilder object with package level access to Address access.
 */
public class AddressBuilder {

    private Address template = new Address();

    protected AddressBuilder() {
    }

    public AddressBuilder withAddress(String... address) {
        this.template.address.clear();
        this.template.address.addAll(Arrays.asList(address));
        return this;
    }

    public AddressBuilder appendAddress(String... address) {
        this.template.address.addAll(Arrays.asList(address));
        return this;
    }

    public AddressBuilder withPostcode(String postcode) {
        this.template.postcode = postcode;
        return this;
    }

    public Address build() {
        return new Address(template);
    }
}

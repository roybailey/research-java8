package me.roybailey.research.patterns.builder.external;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Address object.
 */
public class AddressBuilder {

    private List<String> address = new ArrayList<>();
    private String postcode;

    public AddressBuilder() {
    }

    public AddressBuilder withAddress(String... address) {
        this.address.clear();
        this.address.addAll(Arrays.asList(address));
        return this;
    }

    public AddressBuilder appendAddress(String... address) {
        this.address.addAll(Arrays.asList(address));
        return this;
    }

    public AddressBuilder withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Address build() {
        return new Address(address, postcode);
    }
}

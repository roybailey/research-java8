package me.roybailey.research.patterns.builder.soulmate;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable Address object.
 */
public class Address {

    public static AddressBuilder Builder() {
        return new AddressBuilder();
    }

    List<String> address = new ArrayList<>();
    String postcode;

    Address() {
    }

    public Address(List<String> address, String postcode) {
        this.address.addAll(address);
        this.postcode = postcode;
    }

    public Address(Address template) {
        this(template.address, template.postcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address=" + address +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (address != null ? !address.equals(address1.address) : address1.address != null) return false;
        return !(postcode != null ? !postcode.equals(address1.postcode) : address1.postcode != null);

    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }

    public List<String> getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }
}

package me.roybailey.research.patterns.builder.nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Address object.
 */
public class Address {

    public static class Builder {

        private List<String> address = new ArrayList<>();
        private String postcode;

        public Builder() {
        }

        public Builder withAddress(String... address) {
            this.address.clear();
            this.address.addAll(Arrays.asList(address));
            return this;
        }

        public Builder appendAddress(String... address) {
            this.address.addAll(Arrays.asList(address));
            return this;
        }

        public Builder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Address build() {
            return new Address(address, postcode);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    private List<String> address = new ArrayList<>();
    private String postcode;

    public Address(List<String> address, String postcode) {
        this.address.addAll(address);
        this.postcode = postcode;
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

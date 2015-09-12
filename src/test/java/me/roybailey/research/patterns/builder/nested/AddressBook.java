package me.roybailey.research.patterns.builder.nested;

import java.util.Map;

/**
 * Immutable AddressBook object to capture collection of aliases addresses.
 */
public class AddressBook {

    public static class Builder {

        private Map<String, Address> addresses;
        private String primaryAddress;

        public Builder() {
        }

        public Builder withAddress(String alias, Address address) {
            this.addresses.put(alias, address);
            return this;
        }

        public Builder withPrimaryAddress(String alias, Address address) {
            this.withAddress(alias, address).withPrimaryAddress(alias);
            return this;
        }

        private Builder withPrimaryAddress(String alias) {
            this.primaryAddress = alias;
            return this;
        }

        public AddressBook build() {
            return new AddressBook(addresses, primaryAddress);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    private Map<String, Address> addresses;
    private String primaryAddressAlias;

    public AddressBook(Map<String, Address> addresses, String primaryAddressAlias) {
        this.addresses = addresses;
        this.primaryAddressAlias = primaryAddressAlias;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "addresses=" + addresses +
                ", primaryAddressAlias='" + primaryAddressAlias + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBook that = (AddressBook) o;

        if (addresses != null ? !addresses.equals(that.addresses) : that.addresses != null) return false;
        return !(primaryAddressAlias != null ? !primaryAddressAlias.equals(that.primaryAddressAlias) : that.primaryAddressAlias != null);

    }

    @Override
    public int hashCode() {
        int result = addresses != null ? addresses.hashCode() : 0;
        result = 31 * result + (primaryAddressAlias != null ? primaryAddressAlias.hashCode() : 0);
        return result;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }

    public String getPrimaryAddressAlias() {
        return primaryAddressAlias;
    }
}

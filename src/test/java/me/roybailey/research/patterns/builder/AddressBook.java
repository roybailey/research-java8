package me.roybailey.research.patterns.builder;

import java.util.Map;

/**
 * Immutable AddressBook object to capture collection of aliases addresses.
 */
public class AddressBook {

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
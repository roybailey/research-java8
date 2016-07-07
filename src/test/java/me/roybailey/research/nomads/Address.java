package me.roybailey.research.nomads;

public class Address {

    private String house;
    private String street;
    private String town;
    private String country;

    public Address(String house, String street, String town, String country) {
        this.house = house;
        this.street = street;
        this.town = town;
        this.country = country;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (house != null ? !house.equals(address.house) : address.house != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (town != null ? !town.equals(address.town) : address.town != null) return false;
        return country != null ? country.equals(address.country) : address.country == null;

    }

    @Override
    public int hashCode() {
        int result = house != null ? house.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}

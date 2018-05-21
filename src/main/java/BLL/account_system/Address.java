package BLL.account_system;

import ACQ.IAddress;

public class Address implements IAddress {
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String municipality;
    private String country;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStreetName() {
        return streetName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZipCode() {
        return zipCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMunicipality() {
        return municipality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * Set the street name.
     * @param streetName any street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Set the house number.
     * @param houseNumber a house number that matches the street name
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Set the zip code.
     * @param zipCode a zip code that matches the other information
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Set the city.
     * @param city a city that matches the other information
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Set the municipality.
     * @param municipality a municipality that matches the other information
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * Set the country
     * @param country a country where rest exists
     */
    public void setCountry(String country) {
        this.country = country;
    }
}

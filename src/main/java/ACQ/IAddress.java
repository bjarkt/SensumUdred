package ACQ;

public interface IAddress {
    /**
     * Get the street name of the address.
     * @return a street name
     */
    String getStreetName();

    /**
     * Get the house number of the address.
     * @return a house number
     */
    String getHouseNumber();

    /**
     * Get the zip code of the address.
     * @return a zip code
     */
    String getZipCode();

    /**
     * Get the city of the address.
     * @return a city
     */
    String getCity();

    /**
     * Get the municipality the address is registered at.
     * @return a municipality
     */
    String getMunicipality();

    /**
     * Get the country of the address.
     * @return a country
     */
    String getCountry();
}

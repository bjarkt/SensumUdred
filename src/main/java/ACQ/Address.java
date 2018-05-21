package ACQ;

public class Address implements IAddress {

    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String municipality;
    private String country;

    @Override
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCountry() {
        return country;
    }
}

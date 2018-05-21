package ACQ;

public class Address implements IAddress {

    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String municipality;
    private String country;

    @Override
    public String getStreetName() {
        return streetName;
    }

    @Override
    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getMunicipality() {
        return municipality;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

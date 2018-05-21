package ACQ;

public interface IAddress {

    void setStreetName(String streetName);
    void setHouseNumber(String houseNumber);
    void setZipCode(String zipCode);
    void setCity(String city);
    void setMunicipality(String municipality);
    void setCountry(String country);

    String getStreetName();
    String getHouseNumber();
    String getZipCode();
    String getCity();
    String getMunicipality();
    String getCountry();

}

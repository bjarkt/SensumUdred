package BLL.getter.user_getter;

import ACQ.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GetUser implements IGetUser {
    private IHttp httpClient;
    private String apiUrl;
    private Gson gson;
    private JsonParser jsonParser;
    private Class<? extends IAddress> addressClass;
    private Class<? extends IUser> userClass;

    public GetUser(IHttp httpClient, Class<? extends IAddress> addressClass, Class<? extends IUser> userClass) {
        this.httpClient = httpClient;
        this.apiUrl = "https://sensumudred-api.herokuapp.com/cpr/get-user-address";
        this.gson = new Gson();
        this.jsonParser = new JsonParser();
        this.addressClass = addressClass;
        this.userClass = userClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getUser(String cpr) {
        IUser user = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("cpr", cpr);
        try {
            String result = new String(httpClient.makeHttpRequest(apiUrl, queryMap, HttpMethod.POST, HttpAcceptType.JSON));

            JsonObject o = jsonParser.parse(result).getAsJsonObject();
            JsonObject addressData = o.getAsJsonObject("addressData");
            JsonObject userData = o.getAsJsonObject("userData");

            JsonElement addressElement = gson.fromJson(addressData.toString(), JsonElement.class);
            userData.add("address", addressElement);
            userData.addProperty("ssn", cpr);


            user = gson.fromJson(userData.toString(), userClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    private IUser createUser(String ssn, String firstName, String lastName, IAddress address, String phoneNumber, String email) {
        return new IUser() {
            @Override
            public String getSocialSecurityNumber() {
                return ssn;
            }

            @Override
            public String getFirstName() {
                return firstName;
            }

            @Override
            public String getLastName() {
                return lastName;
            }

            @Override
            public IAddress getAddress() {
                return address;
            }

            @Override
            public String getPhoneNumber() {
                return phoneNumber;
            }

            @Override
            public String getEmail() {
                return email;
            }

            @Override
            public String getName() {
                return getFirstName() + " " + getLastName();
            }
        };
    }

    private IAddress createAddress(String streetName, String houseNumber, String zipCode, String city, String municipality, String country) {
        return new IAddress() {
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
        };
    }
}

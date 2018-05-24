package BLL.getter.user_getter;

import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import ACQ.IHttp;
import ACQ.IUser;
import BLL.account_system.Address;
import BLL.account_system.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GetUser implements IGetUser {
    private IHttp httpClient;
    private String apiUrl;
    private Gson gson;

    public GetUser(IHttp httpClient) {
        this.httpClient = httpClient;
        this.apiUrl = "https://sensumudred-api.herokuapp.com/cpr/get-user-address";
        this.gson = new Gson();
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
            Type type = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
            Map<String, Map<String, String>> myMap = gson.fromJson(result, type);
            if (myMap != null) {
                Map<String, String> addressData = myMap.get("addressData");
                Map<String, String> userData = myMap.get("userData");

                Address address = new Address();
                address.setStreetName(addressData.get("streetName"));
                address.setHouseNumber(addressData.get("houseNumber"));
                address.setZipCode(addressData.get("zipCode"));
                address.setCity(addressData.get("city"));
                address.setMunicipality(addressData.get("municipality"));
                address.setCountry(addressData.get("country"));

                String ssn = cpr;
                String firstName = userData.get("firstName");
                String lastName = userData.get("lastName");
                String email = userData.get("email");
                String phoneNumber = userData.get("phoneNumber");

                User tmpUser = new User(ssn, firstName, lastName);
                tmpUser.setEmail(email);
                tmpUser.setPhoneNumber(phoneNumber);
                tmpUser.setAddress(address);

                user = tmpUser;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}

package BLL.getter.user_getter;

import ACQ.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetUser implements IGetUser {
    private IHttp httpClient;
    private String apiUrl;
    private Gson gson;
    private JsonParser jsonParser;
    private Class<? extends IUser> userClass;

    public GetUser(IHttp httpClient, Class<? extends IUser> userClass) {
        this.httpClient = httpClient;
        this.apiUrl = "https://sensumudred-api.herokuapp.com/cpr/get-user-address";
        this.gson = new Gson();
        this.jsonParser = new JsonParser();
        this.userClass = userClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getUser(String cpr) {
        IUser user = null;
        if (cpr.matches("^[0-9]+$")) {
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
        }

        return user;
    }
}

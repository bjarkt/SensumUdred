package BLL.address_getter;

import BLL.ACQ.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetAddress implements IGetAddress {
    private IHttp httpClient;
    private String apiUrl;
    private Gson gson;

    public GetAddress(IHttp httpClient) {
        this.httpClient = httpClient;
        this.apiUrl = "https://sensumudred-api.herokuapp.com/cpr/get-address";
        this.gson = new Gson();
    }

    public IAddress getAddress(String cpr) {
        IAddress address = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("cpr", cpr);
        try {
            String result = new String(httpClient.makeHttpRequest(apiUrl, queryMap, HttpMethod.POST, HttpAcceptType.JSON));
            address = gson.fromJson(result, Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return address;
    }
}

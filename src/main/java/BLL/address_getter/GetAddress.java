package BLL.address_getter;

import ACQ.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetAddress implements IGetAddress {
    private IHttp httpClient;
    private String apiUrl;
    private Gson gson;
    private Class<? extends IAddress> addressClass;

    public GetAddress(IHttp httpClient, Class<? extends IAddress> addressClass) {
        this.httpClient = httpClient;
        this.addressClass = addressClass;
        this.apiUrl = "https://sensumudred-api.herokuapp.com/cpr/get-address";
        this.gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    public IAddress getAddress(String cpr) {
        IAddress address = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("cpr", cpr);
        try {
            String result = new String(httpClient.makeHttpRequest(apiUrl, queryMap, HttpMethod.POST, HttpAcceptType.JSON));
            address = gson.fromJson(result, addressClass);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return address;
    }
}

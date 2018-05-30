package ACQ;

/**
 * What types of request methods can be used
 */
public enum HttpMethod {
    GET("GET"), POST("POST");


    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

    /**
     * Pretty name of method
     * @return name
     */
    public String getName() {
        return name;
    }
}

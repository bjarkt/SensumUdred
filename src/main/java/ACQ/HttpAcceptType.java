package ACQ;

/**
 * What types of data can be returned in a HTTP request
 */
public enum HttpAcceptType {
    JSON("json"), TEXT("text"), PDF("pdf");


    private String name;

    HttpAcceptType(String name) {
        this.name = name;
    }

    /**
     * Pretty name
     * @return name
     */
    public String getName() {
        return name;
    }
}

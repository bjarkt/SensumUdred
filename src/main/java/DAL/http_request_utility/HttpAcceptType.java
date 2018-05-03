package DAL.http_request_utility;

public enum HttpAcceptType {
    JSON("json"), TEXT("text"), PDF("pdf");

    private String name;

    HttpAcceptType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

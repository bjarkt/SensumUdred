package BLL.ACQ;

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

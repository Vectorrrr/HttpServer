package model;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public enum TypeRequest {
    POST("POST"), GET("GET"), UNKNOWN("UNKNOWN"),;
    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String typeRequest() {
        return typeRequest;
    }
}

package service;

/**
 * Created by fvillela on 5/15/17.
 */

public enum ServiceErrors {
    UNKNOWN(0, "Unknown"),
    SERIALIZATION_ERROR(1, "SerializationError"),
    SERVER_ERROR(2, "ServerError"),
    CONNECTION_UNAVAILABLE(3, "InternetConnectionError");

    private Integer code;
    private String description;

    ServiceErrors(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(Integer abbreviation) {
        for (ServiceErrors item : values()) {
            if (item.getCode() == abbreviation) {
                return item.getDescription();
            }
        }

        return null;
    }

    public static Integer getCode(String description) {
        for (ServiceErrors item : values()) {
            if (item.getDescription().equals(description)) {
                return item.getCode();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

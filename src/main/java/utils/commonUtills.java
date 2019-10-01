package utils;

import java.util.HashMap;
import java.util.Map;

import static utils.Serializer.toJson;

public class commonUtills {
    public static String errorResponse(UserException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", exception.getMessage());
        return toJson(errorResponse);
    }
    public static String errorResponse(AccountException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", exception.getMessage());
        return toJson(errorResponse);
    }
}

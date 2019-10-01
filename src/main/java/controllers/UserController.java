package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.User;
import service.UserService;
import utils.Serializer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static utils.Serializer.readJson;


public class UserController {
    public static void init() {
        post("/user/add", (request, response) -> {
            JsonNode reqPayload = readJson(request.body());
            JsonNode userName = reqPayload.get("name");
            JsonNode userEmail = reqPayload.get("email");
            return Serializer.toJson(UserService.create(userName.asText(), userEmail.asText()));
        });
        get("/user/:id", (request, response) -> {
            User user = UserService.getById(request.params(":id"));
            return Serializer.toJson(user);
        });
        delete("/user/:id", (request, response) -> {
            Map<String, String> responseMap = UserService.delete(request.params(":id"));
            return Serializer.toJson(responseMap);
        });
        get("/user", (request, response) -> {
            Map<String, Collection<User>> userMap = new HashMap<>();
            userMap.put("users", UserService.getAll());
            return Serializer.toJson(userMap);
        });
    }
}

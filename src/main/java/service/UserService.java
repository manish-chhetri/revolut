package service;

import model.Model;
import model.User;
import utils.UserException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class UserService {
    public static User create(String name, String email) {
        if ("".equals(name) || "".equals(email)) {
            throw new UserException(
                    "Name and email can not be empty", 400
            );
        }
        return Model.userFactory.create(name, email);
    }

    public static User getById(String id) {
        User user = Model.userFactory.getById(id);
        if (isNull(user)) {
            throw new UserException(
                    String.format("User id# %s not found", id), 404
            );
        }
        return user;
    }

    public static Map<String, String> delete(String id) {
        User user = Model.userFactory.delete(id);
        if (isNull(user)) {
            throw new UserException(
                    String.format("User with id# %s not found", id), 404
            );
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "User deleted successfully");
        return responseMap;
    }

    public static Collection<User> getAll() {
        return Model.userFactory.getAll();
    }
}

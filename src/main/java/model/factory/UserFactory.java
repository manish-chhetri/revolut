package model.factory;

import model.User;
import utils.DateUtil;
import utils.UserException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

public class UserFactory {
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private Map<String, User> userEmailMap = new HashMap<>();
    private Map<String, User> userIdMap = new HashMap<>();
    private static DateUtil dateUtil = new DateUtil();

    public User create(String name, String email) {
        if (!isNull(userEmailMap.get(email))) {
            throw new UserException(
                    String.format("User already exists with email:: %s", email), 400
            );
        }
        String userId = String.valueOf(COUNTER.incrementAndGet());
        User user = new User(userId, name, email, dateUtil.getDate(), dateUtil.getDate());
        userEmailMap.put(email, user);
        userIdMap.put(userId, user);
        return user;
    }
    public User getById(String id) {
        return userIdMap.get(id);
    }

    public User delete(String id) {
        User user = userIdMap.remove(id);
        if (!isNull(user)) {
            userEmailMap.remove(user.getEmail());
        }
        return user;
    }

    public Collection<User> getAll() {
        return userIdMap.values();
    }

    public void deleteAll() {
        userEmailMap.clear();
        userIdMap.clear();
    }
}

package com.itquandui.ruikanghouduan.Repository;

import com.itquandui.ruikanghouduan.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private ConcurrentHashMap<String, User> userDb = new ConcurrentHashMap<>();

    public User findByUsername(String username) {
        return userDb.get(username);
    }

    public void save(User user) {
        userDb.put(user.getUsername(), user);
    }
}
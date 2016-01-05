package net.maynard.examples.j2ee.web.controller.service;

import java.util.HashMap;
import java.util.Map;

public class FakeAuthenticationService implements AuthenticationService {

    private Map<String, String> users = new HashMap<>();

    public void addUser(String user, String pass) {
        users.put(user, pass);
    }

    @Override
    public boolean isValidLogin(String username, String password) {
        boolean results = false;

        if (users.containsKey(username)
                && users.containsValue(password)) {
            results = true;
        }
        return results;
    }
}

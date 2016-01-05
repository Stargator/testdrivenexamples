package net.maynard.examples.j2ee.web.controller.service;

public interface AuthenticationService {
    boolean isValidLogin(String username, String password);
}

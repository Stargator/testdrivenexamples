package net.maynard.examples.j2ee.web.controller.authenticator;

public interface AuthenticationService {
    boolean isValidLogin(String username, String password);
}

package net.maynard.examples.j2ee.web.controller.spring;

import net.maynard.examples.j2ee.web.controller.authenticator.FakeAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {

    private final String CORRECT_PASSWORD = "correctpassword";
    private final String VALID_USERNAME = "validuser";
    private final String WRONG_PASSWORD = "nosuchpassword";

    private FakeAuthenticationService mock;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.addParameter("j_username", VALID_USERNAME);

        response = new MockHttpServletResponse();
        mock = new FakeAuthenticationService();

        loginController = new LoginController();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage()
            throws Exception {
        request.addParameter("j_password", WRONG_PASSWORD);
        mock.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        loginController.setAuthenticationService(mock);
        ModelAndView v = loginController.handleRequest(request, response);

        assertEquals("wrongpassword", v.getViewName());
    }

    @Test
    public void validLoginForwardsToFrontPage() throws  Exception {
        request.addParameter("j_password", CORRECT_PASSWORD);
        mock.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        loginController.setAuthenticationService(mock);
        ModelAndView v = loginController.handleRequest(request, response);

        assertEquals("frontpage", v.getViewName());
    }
}
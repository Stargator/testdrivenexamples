package net.maynard.examples.j2ee.web.controller.servlet;

import static org.junit.Assert.assertEquals;

import net.maynard.examples.j2ee.web.controller.authenticator.AuthenticationService;
import net.maynard.examples.j2ee.web.controller.authenticator.FakeAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoginServletTest {

    private final String CORRECT_PASSWORD = "correctpassword";
    private final String VALID_USERNAME = "validuser";

    private LoginServlet servlet;
    private FakeAuthenticationService authenticator;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        authenticator = new FakeAuthenticationService();
        authenticator.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        servlet = new LoginServlet() {
            @Override
            protected AuthenticationService getAuthenticationService() {
                return authenticator;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        request.addParameter(servlet.getPARAMETER_USER(), VALID_USERNAME);
        request.addParameter(servlet.getPARAMETER_PASS(), "wrongpassword");

        servlet.service(request, response);
        assertEquals("/invalidlogin", response.getRedirectedUrl());
    }

    @Test
    public void validLoginForwardToFrontPageAndStoreUsername() throws  Exception {
        request.addParameter(servlet.getPARAMETER_USER(), VALID_USERNAME);
        request.addParameter(servlet.getPARAMETER_PASS(), CORRECT_PASSWORD);

        servlet.service(request, response);
        assertEquals("/frontpage", response.getRedirectedUrl());
        assertEquals(VALID_USERNAME, request.getSession().getAttribute("username"));
    }
}
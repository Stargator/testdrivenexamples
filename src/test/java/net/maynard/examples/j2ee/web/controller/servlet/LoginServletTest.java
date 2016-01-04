package net.maynard.examples.j2ee.web.controller.servlet;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServlet;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoginServletTest {

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", "nosuchuser");
        request.addParameter("j_password", "wrongpassword");

        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpServlet servlet = new LoginServlet();
        servlet.service(request, response);
        assertEquals("/invalidlogin", response.getRedirectedUrl());
    }
}
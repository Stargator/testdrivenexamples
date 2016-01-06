package net.maynard.examples.j2ee.web.controller.spring;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {

    @Test
    public void wrongPasswordShouldRedirectToErrorPage()
            throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("j_username", "nosuchuser");
        request.addParameter("j_password", "nosuchpassword");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Controller c = new LoginController();
        ModelAndView v = c.handleRequest(request, response);

        assertEquals("wrongpassword", v.getViewName());
    }
}
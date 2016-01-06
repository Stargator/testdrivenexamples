package net.maynard.examples.j2ee.web.controller.spring;

import net.maynard.examples.j2ee.web.controller.authenticator.AuthenticationService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    private AuthenticationService authenicator;

    public void setAuthenticationService(AuthenticationService authService) {
        this.authenicator = authService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String user = request.getParameter("j_username");
        String pass = request.getParameter("j_password");

        if (authenicator.isValidLogin(user, pass)) {
            return new ModelAndView("frontpage");
        }

        return new ModelAndView("wrongpassword");
    }
}

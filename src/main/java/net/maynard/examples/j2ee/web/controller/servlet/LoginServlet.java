package net.maynard.examples.j2ee.web.controller.servlet;

import net.maynard.examples.j2ee.web.controller.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final String PARAMETER_USER = "j_username";
    private final String PARAMETER_PASS = "j_password";

    protected AuthenticationService getAuthenticationService() {
        return null;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String user = request.getParameter("j_username").toString();
        String pass = request.getParameter("j_password").toString();

        if (getAuthenticationService().isValidLogin(user, pass)) {
            response.sendRedirect("/frontpage");
            request.getSession().setAttribute("username", user);
        } else {
            response.sendRedirect("/invalidlogin");
        }
    }

    public String getPARAMETER_USER() {
        return PARAMETER_USER;
    }

    public String getPARAMETER_PASS() {
        return PARAMETER_PASS;
    }
}

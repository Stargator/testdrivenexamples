package net.maynard.examples.j2ee.web.view.jsp.jsptest;

public class LoginPageTest extends JspTestCase {
    public void testFormFieldsArePresent() throws Exception {
        get("/login.jsp");
        form().shouldHaveField("j_username");
        form().shouldHaveField("j_password");
        form().shouldHaveSubmitButton("login");
    }
}
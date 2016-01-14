package net.maynard.examples.j2ee.web.view.jsp.jsptest;

import net.sf.jsptest.HtmlTestCase;

public abstract class JspTestCase extends HtmlTestCase {
    @Override
    protected String getWebRoot() {
        return "./websrc/jsp";
    }
}
package by.yan.cafe.test;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class GetInfoTag extends TagSupport
{
    public int doStartTag() throws JspException
    {
        String str = "By Yan Smolik, 2018 | Epam";
        try
        {
            JspWriter out = pageContext.getOut();
            out.write(str);
        }
        catch (IOException e)
        {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}


package by.yan.cafe.filter;

import by.yan.cafe.constant.PagePath;
import by.yan.cafe.constant.RoleType;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter
{
    private FilterConfig filterConfig;
    private static final String COMMAND_LOGOUT = "LOGOUT";
    private static final String COMMAND_MAIN_FORWARS = "MAIN_FORWARD";
    private static final String PARAMETER_COMMAND = "command";
    private static final String INDEX_JSP = "/index.jsp";
    private static final String SESSION_LANG = "lang";
    private static final String SESSION_IS_LOGIN = "isLogin";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_PASSWORD = "password";
    private static final String SESSION_EMAIL = "email";
    private static final String SESSION_MAIL_CODE = "mailCode";

    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getServletPath().equals(INDEX_JSP))
        {
            HttpSession session=request.getSession(true);
            session.setAttribute(SESSION_LANG, "en");
            session.setAttribute(SESSION_IS_LOGIN , "false");
            session.setAttribute(SESSION_ROLE, RoleType.VISITOR);
            session.setAttribute(SESSION_LAST_PAGE, PagePath.MAIN_PAGE);
        }
        else
        {
            HttpSession session = request.getSession(false);
            if (session == null)
            {
                response.sendRedirect(INDEX_JSP);
                return;
            }
            if((((String)session.getAttribute(SESSION_LAST_PAGE)).equals(PagePath.MAIL_CONFIRMATION_PAGE))
                    &&(request.getParameter(PARAMETER_COMMAND).equals(COMMAND_MAIN_FORWARS)))
            {
                session.removeAttribute(SESSION_LOGIN);
                session.removeAttribute(SESSION_PASSWORD);
                session.removeAttribute(SESSION_EMAIL);
                session.removeAttribute(SESSION_MAIL_CODE);
            }
        }
        filterChain.doFilter(request, response);
    }

    public void destroy()
    {

    }

}

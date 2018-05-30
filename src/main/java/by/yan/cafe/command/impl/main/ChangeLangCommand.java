package by.yan.cafe.command.impl.main;

import by.yan.cafe.command.Command;
import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLangCommand implements Command
{
    private static final String LANG = "lang";
    private static final String SESSION_LAST_PAGE = "lastPage";

    @Override
    public Router execute(HttpServletRequest request)
    {
        String langVal = request.getParameter(LANG);

        HttpSession session = request.getSession();
        session.setAttribute(LANG, langVal);
        String pagePath=(String)session.getAttribute(SESSION_LAST_PAGE);
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(pagePath);

        return router;
    }

}

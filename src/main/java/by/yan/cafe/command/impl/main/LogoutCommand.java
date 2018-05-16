package by.yan.cafe.command.impl.main;

import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command
{
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_IS_LOGIN = "isLogin";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_BASKET = "basket";

    @Override
    public Router execute(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_LOGIN);
        session.removeAttribute(SESSION_BASKET);
        session.setAttribute(SESSION_IS_LOGIN,"false");
        session.setAttribute(SESSION_ROLE, RoleType.VISITOR);

        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.MAIN_PAGE);
        return router;
    }
}

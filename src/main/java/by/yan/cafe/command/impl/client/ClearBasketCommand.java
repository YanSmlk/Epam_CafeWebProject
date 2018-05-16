package by.yan.cafe.command.impl.client;

import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClearBasketCommand implements Command
{
    private static final String SESSION_BASKET = "basket";

    @Override
    public Router execute(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        session.removeAttribute(SESSION_BASKET);

        Router router = new Router();
        router.setPagePath(PagePath.FOOD_MENU_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

}

package by.yan.cafe.command.impl.main;

import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.MAIN_PAGE);
        return router;
    }

}

package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.main.MainForwardAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;

public class MainForwardCommand implements Command
{
    private static final String PARAM_ACTION="action";
    private MainForwardAction mainForwardAction;

    public MainForwardCommand(MainForwardAction mainForwardAction)
    {
        this.mainForwardAction=mainForwardAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {

        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);

        String actionValue=request.getParameter(PARAM_ACTION);
        String pagePath=mainForwardAction.getPagePath(actionValue);

        switch (pagePath)
        {
            case PagePath.CLIENT_PROFILE_PAGE:
                router.setRouteType(Router.RouteType.REDIRECT);
                break;
            case PagePath.DEMO_FOOD_MENU_PAGE:
                router.setRouteType(Router.RouteType.REDIRECT);
                break;
            case PagePath.MANAGER_PROFILE_PAGE:
                router.setRouteType(Router.RouteType.REDIRECT);
                break;
            case PagePath.CONTROL_MENU_PAGE:
                router.setRouteType(Router.RouteType.REDIRECT);
                break;
        }

        router.setPagePath(pagePath);
        return router;
    }

}

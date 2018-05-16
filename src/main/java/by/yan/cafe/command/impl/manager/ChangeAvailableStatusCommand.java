package by.yan.cafe.command.impl.manager;

import by.yan.cafe.action.manager.ChangeFoodInfoAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class ChangeAvailableStatusCommand implements Command
{
    private static final String PARAM_FOOD = "foodElem";
    private static final int INDEX_FOOD_TYPE = 0;
    private static final int INDEX_FOOD_ID = 1;
    private ChangeFoodInfoAction changeFoodInfoAction;

    public ChangeAvailableStatusCommand(ChangeFoodInfoAction changeFoodInfoAction)
    {
       this.changeFoodInfoAction=changeFoodInfoAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(PagePath.CONTROL_MENU_PAGE);

        String selectedFood=request.getParameter(PARAM_FOOD);
        String[] parts =selectedFood.split(Pattern.quote("|"));
        String foodType=parts[INDEX_FOOD_TYPE];
        String foodId=(parts[INDEX_FOOD_ID]);
        try
        {
            changeFoodInfoAction.changeAvailabilityStatus(foodType, Integer.parseInt(foodId));
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        return router;
    }

}

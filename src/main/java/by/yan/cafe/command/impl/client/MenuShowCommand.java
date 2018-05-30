package by.yan.cafe.command.impl.client;

import by.yan.cafe.action.main.MenuShowAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.yan.cafe.servlet.FrontController.LOGGER;

public class MenuShowCommand implements Command
{
    private static final String PARAM_MEALS="mealArr";
    private static final String PARAM_DESSERTS="dessertArr";
    private static final String PARAM_DRINKS="drinkArr";
    private MenuShowAction menuShowAction;

    public MenuShowCommand(MenuShowAction menuShowAction)
    {
        this.menuShowAction=menuShowAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.FOOD_MENU_PAGE);
        try
        {
            List<BaseFood> mealArr=menuShowAction.loadMeal();
            List<BaseFood> dessertArr=menuShowAction.loadDessert();
            List<BaseFood> drinkArr=menuShowAction.loadDrink();

            request.setAttribute(PARAM_MEALS,mealArr);
            request.setAttribute(PARAM_DESSERTS,dessertArr);
            request.setAttribute(PARAM_DRINKS,drinkArr);
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        return router;
    }

}

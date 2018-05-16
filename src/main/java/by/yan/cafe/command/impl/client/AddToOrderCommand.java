package by.yan.cafe.command.impl.client;

import by.yan.cafe.action.client.AddToOrderAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.entity.Order;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import static by.yan.cafe.servlet.FrontController.LOGGER;

public class AddToOrderCommand implements Command
{
    private static final String SESSION_BASKET = "basket";
    private static final String PARAM_FOOD = "foodElem";
    private static final int INDEX_FOOD_TYPE = 0;
    private static final int INDEX_FOOD_ID = 1;
    private AddToOrderAction addToOrderAction;

    public AddToOrderCommand(AddToOrderAction addToOrderAction)
    {
        this.addToOrderAction=addToOrderAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(PagePath.FOOD_MENU_PAGE);

        String selectedFood=request.getParameter(PARAM_FOOD);
        String[] parts =selectedFood.split(Pattern.quote("|"));
        String foodType=parts[INDEX_FOOD_TYPE];
        String foodId=(parts[INDEX_FOOD_ID]);
        int foodAmount=Integer.parseInt(request.getParameter(selectedFood));

        HttpSession session = request.getSession();
        Order basket;
        if(session.getAttribute(SESSION_BASKET)!=null)
        {
            basket=(Order)session.getAttribute(SESSION_BASKET);
        }
        else
        {
            basket=new Order();
        }
        try
        {
            addToOrderAction.addFoodToOrder(foodType, Integer.parseInt(foodId), foodAmount,basket);
            session.setAttribute(SESSION_BASKET,basket);
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }

        return router;
    }

}

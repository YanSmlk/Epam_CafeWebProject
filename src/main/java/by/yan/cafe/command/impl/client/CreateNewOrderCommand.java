package by.yan.cafe.command.impl.client;

import by.yan.cafe.action.client.CreateNewOrderAction;
import by.yan.cafe.action.main.MenuShowAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.entity.Order;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import by.yan.cafe.validation.OrderInfoValidator;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class CreateNewOrderCommand implements Command
{
    private static final String SESSION_BASKET = "basket";
    private static final String SESSION_LOGIN = "login";
    private static final String PARAM_RES_TIME = "resTime";
    private static final String PARAM_PAY_TYPE = "payType";
    private static final String PARAM_BAD_TIME = "badTime";
    private static final String PARAM_BAD_PAY_TYPE = "badPayType";
    private static final String PARAM_NOT_ENOUGHT = "notEnought";
    private static final String PARAM_MEALS="mealArr";
    private static final String PARAM_DESSERTS="dessertArr";
    private static final String PARAM_DRINKS="drinkArr";
    private CreateNewOrderAction createNewOrderAction;

    public CreateNewOrderCommand(CreateNewOrderAction createNewOrderAction)
    {
        this.createNewOrderAction=createNewOrderAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();

        String gettingTime=request.getParameter(PARAM_RES_TIME);
        String payType=request.getParameter(PARAM_PAY_TYPE);

        request.setAttribute(PARAM_BAD_PAY_TYPE,null);
        request.setAttribute(PARAM_BAD_TIME,null);
        request.setAttribute(PARAM_NOT_ENOUGHT,null);
        try
        {
            if (OrderInfoValidator.timeCheck(gettingTime))
            {
                if(payType!=null)
                {
                    HttpSession session = request.getSession();
                    String login=(String) session.getAttribute(SESSION_LOGIN);
                    Order basket=(Order)session.getAttribute(SESSION_BASKET);

                    if (createNewOrderAction.checkPaymentPossibility(payType,login,basket.getTotalPrice()))
                    {
                        createNewOrderAction.saveNewOrder(basket,gettingTime,login);
                        createNewOrderAction.doPayment(payType,basket.getTotalPrice());
                        session.removeAttribute(SESSION_BASKET);
                        router.setPagePath(PagePath.CLIENT_PROFILE_PAGE);
                        router.setRouteType(Router.RouteType.REDIRECT);
                        return router;
                    }
                    else
                    {
                        request.setAttribute(PARAM_NOT_ENOUGHT,"bad");
                    }
                }
                else
                {
                    request.setAttribute(PARAM_BAD_PAY_TYPE,"bad");
                }
            }
            else
            {
                request.setAttribute(PARAM_BAD_TIME,"bad");
            }
            MenuShowAction menuShowAction=new MenuShowAction();
            List<BaseFood> mealArr=menuShowAction.loadMeal();
            List<BaseFood> dessertArr=menuShowAction.loadDessert();
            List<BaseFood> drinkArr=menuShowAction.loadDrink();
            request.setAttribute(PARAM_MEALS,mealArr);
            request.setAttribute(PARAM_DESSERTS,dessertArr);
            request.setAttribute(PARAM_DRINKS,drinkArr);
        }
        catch (RepositoryException | ActionException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        request.setAttribute(PARAM_RES_TIME,gettingTime);

        router.setPagePath(PagePath.FOOD_MENU_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }

}

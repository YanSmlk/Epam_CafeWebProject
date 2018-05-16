package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.client.ClientInfoLoadAction;
import by.yan.cafe.action.main.MenuShowAction;
import by.yan.cafe.action.manager.ManagerInfoLoadAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.entity.Order;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class RedirectGetCommand implements Command
{
    private static final String PARAM_ORDERS_FINISHED = "ordersFinished";
    private static final String PARAM_ORDERS_CURRENT = "ordersCurrent";
    private static final String PARAM_ORDERS_CANCELED = "ordersCanceled";
    private static final String PARAM_PAGE_PATH = "pagePath";
    private static final String PARAM_POINTS="points";
    private static final String PARAM_MONEY ="money";
    private static final String PARAM_SAVED="saved";
    private static final String PARAM_ORDERS="orders";
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_IS_SAVED="isSaved";
    private static final String PARAM_MEALS="mealArr";
    private static final String PARAM_DESSERTS="dessertArr";
    private static final String PARAM_DRINKS="drinkArr";
    private static final String PARAM_OUT_OF_STOCK_MEAL="outOfStockMealArr";
    private static final String PARAM_OUT_OF_STOCK_DESSERT="outOfStockDessertArr";
    private static final String PARAM_OUT_OF_STOCK_DRINK="outOfStockDrinkArr";
    private static final int POINTS_ID=0;
    private static final int MONEY_ID=1;

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);

        String pagePath=request.getParameter(PARAM_PAGE_PATH);

        switch (pagePath)
        {
            case PagePath.CLIENT_PROFILE_PAGE:
                try
                {
                    HttpSession session = request.getSession();
                    String login = (String) session.getAttribute(SESSION_LOGIN);
                    ClientInfoLoadAction clientInfoLoadAction = new ClientInfoLoadAction();
                    List<Order> orderArr =clientInfoLoadAction.getOrders(login);
                    int[] balance=clientInfoLoadAction.getClientInfo(login);

                    request.setAttribute(PARAM_POINTS,balance[POINTS_ID]);
                    request.setAttribute(PARAM_MONEY,balance[MONEY_ID]);
                    request.setAttribute(PARAM_ORDERS,orderArr);
                }
                catch (RepositoryException ex)
                {
                    LOGGER.log(Level.ERROR,""+ex);
                    throw new RuntimeException(ex);
                }
                break;
            case PagePath.CONTROL_MENU_PAGE:
                outOfStockFoodLoad(request);
                infoSavedAlert(request);
            case PagePath.DEMO_FOOD_MENU_PAGE:
            case PagePath.FOOD_MENU_PAGE:
                try
                {
                    MenuShowAction menuShowAction=new MenuShowAction();
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
                break;
            case PagePath.MANAGER_PROFILE_PAGE:
                ManagerInfoLoadAction managerInfoLoadAction=new ManagerInfoLoadAction();
                try
                {
                    List<Order> currentOrderArr = managerInfoLoadAction.OrdersLoadByStatus(OrderStatus.Current);
                    List<Order> finishedOrderArr = managerInfoLoadAction.OrdersLoadByStatus(OrderStatus.Finished);
                    List<Order> canceledOrderArr = managerInfoLoadAction.OrdersLoadByStatus(OrderStatus.Canceled);
                    request.setAttribute(PARAM_ORDERS_CURRENT,currentOrderArr);
                    request.setAttribute(PARAM_ORDERS_FINISHED,finishedOrderArr);
                    request.setAttribute(PARAM_ORDERS_CANCELED,canceledOrderArr);
                }
                catch (RepositoryException ex)
                {
                    LOGGER.log(Level.ERROR,""+ex);
                    throw new RuntimeException(ex);
                }
                break;
         }
        router.setPagePath(pagePath);
        return router;
    }

    private void outOfStockFoodLoad(HttpServletRequest request)
    {
        MenuShowAction menuShowActionForManager=new MenuShowAction();
        try
        {
            List<BaseFood> outOfStockMealArr=menuShowActionForManager.loadOutOfStockMeal();
            List<BaseFood> outOfStockDessertArr=menuShowActionForManager.loadOutOfStockDessert();
            List<BaseFood> outOfStockDrinkArr=menuShowActionForManager.loadOutOfStockDrink();
            request.setAttribute(PARAM_OUT_OF_STOCK_MEAL,outOfStockMealArr);
            request.setAttribute(PARAM_OUT_OF_STOCK_DESSERT,outOfStockDessertArr);
            request.setAttribute(PARAM_OUT_OF_STOCK_DRINK,outOfStockDrinkArr);
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
    }

    private void infoSavedAlert(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if((((String) session.getAttribute(SESSION_LAST_PAGE)).equals(PagePath.CONTROL_MENU_PAGE.toString()))
                &&((session.getAttribute(SESSION_IS_SAVED)))!=null)
        {
            session.removeAttribute(SESSION_IS_SAVED);
            request.setAttribute(PARAM_SAVED,"saved");
        }
    }

}

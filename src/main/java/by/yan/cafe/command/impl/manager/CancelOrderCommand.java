package by.yan.cafe.command.impl.manager;

import by.yan.cafe.action.manager.OrderControlAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import static by.yan.cafe.servlet.FrontController.LOGGER;

public class CancelOrderCommand implements Command
{
    private static final String PARAM_ORDER_INFO="orderInfo";
    private static final int INDEX_ORDER_ID = 0;
    private static final int INDEX_ORDER_PRICE = 1;
    private static final int INDEX_CLIENT_NAME = 2;
    private OrderControlAction orderControlAction;

    public CancelOrderCommand(OrderControlAction orderControlAction)
    {
        this.orderControlAction=orderControlAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(PagePath.MANAGER_PROFILE_PAGE);

        String orderInfo=request.getParameter(PARAM_ORDER_INFO);
        String[] parts =orderInfo.split(Pattern.quote("|"));
        String orderId=parts[INDEX_ORDER_ID];
        String orderPrice=(parts[INDEX_ORDER_PRICE]);
        String clientName=(parts[INDEX_CLIENT_NAME]);
        try
        {
            orderControlAction.cancelOrder(OrderStatus.Canceled,Integer.parseInt(orderId),orderPrice,clientName);
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        return router;
    }

}

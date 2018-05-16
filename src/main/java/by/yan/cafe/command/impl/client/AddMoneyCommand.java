package by.yan.cafe.command.impl.client;

import by.yan.cafe.action.client.AddMoneyAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class AddMoneyCommand implements Command
{
    private static final String SESSION_LOGIN = "login";
    private AddMoneyAction addMoneyAction;

    public AddMoneyCommand(AddMoneyAction addMoneyAction)
    {
        this.addMoneyAction=addMoneyAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(SESSION_LOGIN);
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(PagePath.CLIENT_PROFILE_PAGE);
        try
        {
            addMoneyAction.updateMoney(login);
            return router;
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
    }
}

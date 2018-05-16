package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.main.RegistrClientAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class MailConfirmationCommand implements Command
{
    private static final String PARAM_MAIL_CODE="mailCode";
    private static final String PARAM_BAD_CODE="badCode";
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_PASSWORD = "password";
    private static final String SESSION_EMAIL = "email";
    private static final String SESSION_MAIL_CODE = "mailCode";
    private static final String SESSION_IS_LOGIN = "isLogin";
    private static final String SESSION_ROLE = "role";

    private RegistrClientAction registrClientAction;

    public MailConfirmationCommand(RegistrClientAction registrClientAction)
    {
        this.registrClientAction=registrClientAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);

        HttpSession session = request.getSession();
        String mailCode=request.getParameter(PARAM_MAIL_CODE);
        String mailCodeTrue=(String)session.getAttribute(SESSION_MAIL_CODE);

        if(mailCodeTrue.equals(mailCode))
        {
            String login=(String)session.getAttribute(SESSION_LOGIN);
            String password=(String)session.getAttribute(SESSION_PASSWORD);
            String email=(String)session.getAttribute(SESSION_EMAIL);
            try
            {
                registrClientAction.tryRegistr(login, password, email);
                session.setAttribute(SESSION_IS_LOGIN , "true");
                session.setAttribute(SESSION_ROLE, RoleType.CLIENT);

                router.setRouteType(Router.RouteType.REDIRECT);
                router.setPagePath(PagePath.CLIENT_PROFILE_PAGE);
            }
            catch (RepositoryException | ActionException ex)
            {
                LOGGER.log(Level.ERROR,""+ex);
                throw new RuntimeException(ex);
            }
            finally
            {
                session.removeAttribute(SESSION_PASSWORD);
                session.removeAttribute(SESSION_EMAIL);
                session.removeAttribute(SESSION_MAIL_CODE);
            }
        }
        else
        {
            request.setAttribute(PARAM_BAD_CODE,"badCode");
            router.setPagePath(PagePath.MAIL_CONFIRMATION_PAGE);
        }
        return router;
    }

}

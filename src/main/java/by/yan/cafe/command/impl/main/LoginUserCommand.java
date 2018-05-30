package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.main.LoginUserAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import by.yan.cafe.validation.LoginDataValidator;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class LoginUserCommand implements Command
{
    private static final String PARAM_LOGIN="login";
    private static final String PARAM_PASSWORD="password";
    private static final String PARAM_BAD_LOGIN_DATA="badLoginData";
    private static final String PARAM_NO_USER="noUser";
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_IS_LOGIN = "isLogin";
    private static final String SESSION_ROLE = "role";
    private LoginUserAction loginUserAction;

    public LoginUserCommand(LoginUserAction loginUserAction)
    {
        this.loginUserAction=loginUserAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        String login=request.getParameter(PARAM_LOGIN);
        String password=request.getParameter(PARAM_PASSWORD);

        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.LOGIN_PAGE);

        request.setAttribute(PARAM_NO_USER,null);
        request.setAttribute(PARAM_BAD_LOGIN_DATA,null);

        if(LoginDataValidator.loginCheck(login)&&LoginDataValidator.passwordCheck(password))
        {
            try
            {
                RoleType roleType=loginUserAction.tryLogin(login, password);
                if(roleType!=null)
                {
                    HttpSession session = request.getSession();
                    session.setAttribute(SESSION_LOGIN, login);
                    session.setAttribute(SESSION_IS_LOGIN, "true");
                    session.setAttribute(SESSION_ROLE, roleType);

                    switch (roleType)
                    {
                        case CLIENT:
                            router.setRouteType(Router.RouteType.REDIRECT);
                            router.setPagePath(PagePath.CLIENT_PROFILE_PAGE);
                            break;
                        case MANAGER:
                            router.setRouteType(Router.RouteType.REDIRECT);
                            router.setPagePath(PagePath.MANAGER_PROFILE_PAGE);
                            break;
                    }
                }
                else
                {
                    request.setAttribute(PARAM_NO_USER,"No such user found");
                }
            }
            catch (RepositoryException | ActionException ex)
            {
                LOGGER.log(Level.ERROR,""+ex);
                throw new RuntimeException(ex);
            }
        }
        else
        {
            request.setAttribute(PARAM_BAD_LOGIN_DATA,"badLoginData");
        }
        return router;
    }

}

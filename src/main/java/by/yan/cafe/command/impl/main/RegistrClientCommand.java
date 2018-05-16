package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.main.RegistrClientAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import by.yan.cafe.validation.RegistrationDataValidator;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class RegistrClientCommand implements Command
{
    private static final String PARAM_LOGIN="login";
    private static final String PARAM_PASSWORD="password";
    private static final String PARAM_PASSWORD_CONFIRM="passwordConfirm";
    private static final String PARAM_EMAIL="email";
    private static final String PARAM_BAD_LOGIN="badLogin";
    private static final String PARAM_BAD_PASSWORD="badPassword";
    private static final String PARAM_NOT_EQ_PASS="notEqualsPass";
    private static final String PARAM_BAD_EMAIL="badEmail";
    private static final String PARAM__LOGIN_USED="loginUsed";
    private static final String SESSION_LOGIN = "login";
    private static final String SESSION_PASSWORD = "password";
    private static final String SESSION_EMAIL = "email";
    private static final String SESSION_MAIL_CODE = "mailCode";
    private RegistrClientAction registrClientAction;

    public RegistrClientCommand(RegistrClientAction registrClientAction)
    {this.registrClientAction=registrClientAction;}

    @Override
    public Router execute(HttpServletRequest request)
    {
        String login=request.getParameter(PARAM_LOGIN);
        String password=request.getParameter(PARAM_PASSWORD);
        String passwordConfirm=request.getParameter(PARAM_PASSWORD_CONFIRM);
        String email=request.getParameter(PARAM_EMAIL);

        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.REGISTRATION_PAGE);

        request.setAttribute(PARAM_BAD_LOGIN,null);
        request.setAttribute(PARAM_BAD_PASSWORD,null);
        request.setAttribute(PARAM_NOT_EQ_PASS,null);
        request.setAttribute(PARAM_BAD_EMAIL,null);
        request.setAttribute(PARAM__LOGIN_USED,null);

        if (RegistrationDataValidator.loginCheck(login))
        {
            if (RegistrationDataValidator.passwordCheck(password))
            {
                if (RegistrationDataValidator.passwordEqualityCheck(password,passwordConfirm))
                {
                    if(RegistrationDataValidator.emailCheck(email))
                    {
                        try
                        {
                            if (registrClientAction.checkLoginAvailable(login))
                            {
                                String mailCode=registrClientAction.codeSend(email);

                                HttpSession session = request.getSession();

                                session.setAttribute(SESSION_LOGIN, login);
                                session.setAttribute(SESSION_PASSWORD, password);
                                session.setAttribute(SESSION_EMAIL, email);
                                session.setAttribute(SESSION_MAIL_CODE, mailCode);

                                router.setRouteType(Router.RouteType.REDIRECT);
                                router.setPagePath(PagePath.MAIL_CONFIRMATION_PAGE);
                            }
                            else
                            {
                                request.setAttribute(PARAM__LOGIN_USED,"LoginUsed");
                                request.setAttribute(PARAM_PASSWORD,password);
                                request.setAttribute(PARAM_PASSWORD_CONFIRM,passwordConfirm);
                                request.setAttribute(PARAM_EMAIL,email);
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
                        request.setAttribute(PARAM_LOGIN,login);
                        request.setAttribute(PARAM_PASSWORD,password);
                        request.setAttribute(PARAM_PASSWORD_CONFIRM,passwordConfirm);
                        request.setAttribute(PARAM_BAD_EMAIL,"badEmail");
                    }
                }
                else
                {
                    request.setAttribute(PARAM_LOGIN,login);
                    request.setAttribute(PARAM_EMAIL,email);
                    request.setAttribute(PARAM_NOT_EQ_PASS,"notEq");
                }
            }
            else
            {
                request.setAttribute(PARAM_LOGIN,login);
                request.setAttribute(PARAM_EMAIL,email);
                request.setAttribute(PARAM_BAD_PASSWORD,"badPassword");
            }
        }
        else
        {
            request.setAttribute(PARAM_EMAIL,email);
            request.setAttribute(PARAM_BAD_LOGIN,"badLogin");
        }
        return router;
    }

}

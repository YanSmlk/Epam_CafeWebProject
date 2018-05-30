package by.yan.cafe.servlet;

import by.yan.cafe.action.main.OrderController;
import by.yan.cafe.command.ActionFactory;
import by.yan.cafe.command.Command;
import by.yan.cafe.command.impl.main.EmptyCommand;
import by.yan.cafe.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class FrontController extends javax.servlet.http.HttpServlet
{
    private static final String PARAM_COMMAND = "command";
    public static final Logger LOGGER = LogManager.getLogger();
    OrderController orderController;

    @Override
    public void init() throws ServletException
    {
        ConnectionPool.getInstance();
        orderController=new OrderController();
        orderController.start();
    }

    @Override
    public void destroy()
    {
        ConnectionPool.getInstance().destroyConnections();
        orderController.interrupt();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Optional<Command> commandOptional= ActionFactory.defineCommand(request.getParameter(PARAM_COMMAND));
        Command command = commandOptional.orElse(new EmptyCommand());
        Router router = command.execute(request);
        String pagePath=router.getPagePath();
        if (router.getRoute() == Router.RouteType.FORWARD)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
            dispatcher.forward(request, response);
        }
        else
        {
            response.sendRedirect("FrontController?command=REDIRECT_GET&pagePath="+pagePath);
        }
    }

}

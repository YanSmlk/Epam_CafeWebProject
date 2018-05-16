package by.yan.cafe.command.impl.manager;

import by.yan.cafe.action.manager.ChangeFoodInfoAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.command.impl.main.FoodInfoLoader;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import by.yan.cafe.validation.FoodInfoValidator;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class ChangeFoodInfoCommand implements Command
{
    private static final String SESSION_IS_SAVED="isSaved";
    private static final String PARAM_FOOD = "foodElem";
    private static final String PARAM_BAD_PIC = "badPic";
    private static final String PARAM_BAD_INFO = "badInfo";

    private static final int INDEX_FOOD_TYPE = 0;
    private static final int INDEX_FOOD_ID = 1;
    private ChangeFoodInfoAction changeFoodInfoAction;

    public ChangeFoodInfoCommand(ChangeFoodInfoAction changeFoodInfoAction)
    {
        this.changeFoodInfoAction=changeFoodInfoAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(PagePath.CONTROL_MENU_PAGE);

        String selectedFood=request.getParameter(PARAM_FOOD);
        String[] parts =selectedFood.split(Pattern.quote("|"));
        String foodType=parts[INDEX_FOOD_TYPE];
        String foodId=(parts[INDEX_FOOD_ID]);

        String name=request.getParameter("name"+foodType+foodId);
        String descr=request.getParameter("descr"+foodType+foodId);
        String price=request.getParameter("price"+foodType+foodId);
        String picName=request.getParameter("pic"+foodType+foodId);

        FoodInfoLoader foodInfoLoader;
        if(!picName.isEmpty())
        {
            String picPath = (request.getServletContext().getRealPath("/images")+"/"+picName);
            if(!FoodInfoValidator.picNameCheck(picName,picPath))
            {
                foodInfoLoader=new FoodInfoLoader();
                foodInfoLoader.loadMenuData(request);
                router.setRouteType(Router.RouteType.FORWARD);
                request.setAttribute(PARAM_BAD_PIC,"bad");
                return router;
            }
        }
        if(FoodInfoValidator.nameCheck(name)&&FoodInfoValidator.descrCheck(descr)&&FoodInfoValidator.priceCheck(price))
        {
            if(picName.isEmpty())
            {
                try
                {
                    changeFoodInfoAction.rewriteInfo(name, descr, price, foodType, Integer.parseInt(foodId));
                }
                catch (RepositoryException ex)
                {
                    LOGGER.log(Level.ERROR,""+ex);
                    throw new RuntimeException(ex);
                }
            }
            else
            {
                try
                {
                    changeFoodInfoAction.rewriteInfoWithPic(name, descr, price, foodType, Integer.parseInt(foodId),picName);
                }
                catch (RepositoryException ex)
                {
                    LOGGER.log(Level.ERROR,""+ex);
                    throw new RuntimeException(ex);
                }
            }
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_IS_SAVED, true);
            return router;
        }
        else
        {
            foodInfoLoader=new FoodInfoLoader();
            foodInfoLoader.loadMenuData(request);
            router.setRouteType(Router.RouteType.FORWARD);
            request.setAttribute(PARAM_BAD_INFO,"badd");
            return router;
        }
    }

}

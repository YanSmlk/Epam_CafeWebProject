package by.yan.cafe.command.impl.manager;

import by.yan.cafe.action.manager.FoodControlAction;
import by.yan.cafe.command.Command;
import by.yan.cafe.command.impl.main.FoodInfoLoader;
import by.yan.cafe.constant.PagePath;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.servlet.Router;
import by.yan.cafe.validation.FoodInfoValidator;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class AddNewFoodCommand implements Command
{
    private static final String SESSION_IS_SAVED="isSaved";
    private static final String PARAM_NEW_FOOD_TYPE = "newFoodType";
    private static final String CONST_NEW = "new";
    private static final String CONST_NAME = "Name";
    private static final String CONST_DESCR = "Descr";
    private static final String CONST_PRICE = "Price";
    private static final String CONST_PIC_INFO = "PicInfoHidd";
    private static final String PARAM_BAD_PIC = "badPic";
    private static final String PARAM_BAD_INFO = "badInfo";
    private FoodControlAction foodControlAction;

    public AddNewFoodCommand(FoodControlAction foodControlAction)
    {
        this.foodControlAction = foodControlAction;
    }

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(PagePath.CONTROL_MENU_PAGE);

        String newFoodType=request.getParameter(PARAM_NEW_FOOD_TYPE);
        String newFoodName=request.getParameter(CONST_NEW+newFoodType+CONST_NAME);
        String newFoodDescr=request.getParameter(CONST_NEW+newFoodType+CONST_DESCR);
        String newFoodPrice=request.getParameter(CONST_NEW+newFoodType+CONST_PRICE);
        String newFoodPicName=request.getParameter(CONST_NEW+newFoodType+CONST_PIC_INFO);

        FoodInfoLoader foodInfoLoader=new FoodInfoLoader();
        if(!FoodInfoValidator.nameCheck(newFoodName))
        {
            foodInfoLoader.loadMenuData(request);
            request.setAttribute(CONST_NEW+newFoodType+CONST_DESCR,newFoodDescr);
            request.setAttribute(CONST_NEW+newFoodType+CONST_PRICE,newFoodPrice);
            request.setAttribute(PARAM_BAD_INFO,"bad");
            return router;
        }
        if(!FoodInfoValidator.descrCheck(newFoodDescr))
        {
            foodInfoLoader.loadMenuData(request);
            request.setAttribute(CONST_NEW+newFoodType+CONST_NAME,newFoodName);
            request.setAttribute(CONST_NEW+newFoodType+CONST_PRICE,newFoodPrice);
            request.setAttribute(PARAM_BAD_INFO,"bd");
            return router;
        }
        if(!FoodInfoValidator.priceCheck(newFoodPrice))
        {
            foodInfoLoader.loadMenuData(request);
            request.setAttribute(CONST_NEW+newFoodType+CONST_NAME,newFoodName);
            request.setAttribute(CONST_NEW+newFoodType+CONST_DESCR,newFoodDescr);
            request.setAttribute(PARAM_BAD_INFO,"d");
            return router;
        }
        String picPath = (request.getServletContext().getRealPath("/images")+"/"+newFoodPicName);
        if(newFoodPicName.isEmpty() ||(!FoodInfoValidator.picNameCheck(newFoodPicName,picPath)))
        {
            foodInfoLoader.loadMenuData(request);
            request.setAttribute(PARAM_BAD_PIC,"bad");
            request.setAttribute(CONST_NEW+newFoodType+CONST_NAME,newFoodName);
            request.setAttribute(CONST_NEW+newFoodType+CONST_DESCR,newFoodDescr);
            request.setAttribute(CONST_NEW+newFoodType+CONST_PRICE,newFoodPrice);
            return router;
        }
        try
        {
            foodControlAction.addNewFood(newFoodType, newFoodName, newFoodDescr, newFoodPrice, newFoodPicName);
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_IS_SAVED, true);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

}

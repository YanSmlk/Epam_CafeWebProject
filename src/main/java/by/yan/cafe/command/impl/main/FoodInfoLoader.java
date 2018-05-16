package by.yan.cafe.command.impl.main;

import by.yan.cafe.action.main.MenuShowAction;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.RepositoryException;
import org.apache.logging.log4j.Level;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class FoodInfoLoader
{

    private static final String PARAM_MEALS="mealArr";
    private static final String PARAM_DESSERTS="dessertArr";
    private static final String PARAM_DRINKS="drinkArr";
    private static final String PARAM_OUT_OF_STOCK_MEAL="outOfStockMealArr";
    private static final String PARAM_OUT_OF_STOCK_DESSERT="outOfStockDessertArr";
    private static final String PARAM_OUT_OF_STOCK_DRINK="outOfStockDrinkArr";

    public void loadMenuData(HttpServletRequest request)
    {
        List<BaseFood> mealArr;
        List<BaseFood> dessertArr;
        List<BaseFood> drinkArr;
        List<BaseFood> outOfStockMealArr;
        List<BaseFood> outOfStockDessertArr;
        List<BaseFood> outOfStockDrinkArr;
        try
        {
            MenuShowAction menuShowAction = new MenuShowAction();
            mealArr = menuShowAction.loadMeal();
            dessertArr = menuShowAction.loadDessert();
            drinkArr = menuShowAction.loadDrink();
            outOfStockMealArr=menuShowAction.loadOutOfStockMeal();
            outOfStockDessertArr=menuShowAction.loadOutOfStockDessert();
            outOfStockDrinkArr=menuShowAction.loadOutOfStockDrink();
        }
        catch (RepositoryException ex)
        {
            LOGGER.log(Level.ERROR,""+ex);
            throw new RuntimeException(ex);
        }
        request.setAttribute(PARAM_MEALS,mealArr);
        request.setAttribute(PARAM_DESSERTS,dessertArr);
        request.setAttribute(PARAM_DRINKS,drinkArr);
        request.setAttribute(PARAM_OUT_OF_STOCK_MEAL,outOfStockMealArr);
        request.setAttribute(PARAM_OUT_OF_STOCK_DESSERT,outOfStockDessertArr);
        request.setAttribute(PARAM_OUT_OF_STOCK_DRINK,outOfStockDrinkArr);
    }

}

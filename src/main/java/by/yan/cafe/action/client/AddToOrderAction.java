package by.yan.cafe.action.client;

import by.yan.cafe.entity.Order;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.entity.food.impl.Dessert;
import by.yan.cafe.entity.food.impl.Drink;
import by.yan.cafe.entity.food.impl.Meal;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.food.FoodRepository;
import by.yan.cafe.repository.food.specification.SelectDessertByIdSpec;
import by.yan.cafe.repository.food.specification.SelectDrinkByIdSpec;
import by.yan.cafe.repository.food.specification.SelectMealByIdSpec;
import java.util.List;

public class AddToOrderAction
{
    private static final String MEAL_TYPE="meal";
    private static final String DESSERT_TYPE="dessert";
    private static final String DRINK_TYPE="drink";

    public void addFoodToOrder(String foodType, int foodId, int foodAmount, Order basket) throws RepositoryException
    {
        FoodRepository foodRepository=new FoodRepository();
        List<BaseFood> foodArr;
        switch (foodType)
        {
            case MEAL_TYPE:
                SqlSpecification mealSpecification=new SelectMealByIdSpec(foodId);
                foodArr=foodRepository.query(mealSpecification);
                Meal meal=(Meal)foodArr.get(0);
                meal.setId(foodId);
                for(int i=0;i<foodAmount;i++)
                {
                    basket.getMealArr().add(meal);
                }
                break;
            case DESSERT_TYPE:
                SqlSpecification dessertSpecification=new SelectDessertByIdSpec(foodId);
                foodArr=foodRepository.query(dessertSpecification);
                Dessert dessert=(Dessert)foodArr.get(0);
                dessert.setId(foodId);
                for(int i=0;i<foodAmount;i++)
                {
                    basket.getDessertArr().add(dessert);
                }
                break;
            case DRINK_TYPE:
                SqlSpecification drinkSpecification=new SelectDrinkByIdSpec(foodId);
                foodArr=foodRepository.query(drinkSpecification);
                Drink drink=(Drink)foodArr.get(0);
                drink.setId(foodId);
                for(int i=0;i<foodAmount;i++)
                {
                    basket.getDrinkArr().add(drink);
                }
                break;
        }
    }

}

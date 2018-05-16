package by.yan.cafe.action.main;

import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.food.FoodRepository;
import by.yan.cafe.repository.food.specification.*;

import java.util.List;

public class MenuShowAction
{
    private FoodRepository foodRepository;

    public MenuShowAction()
    {
        foodRepository=new FoodRepository();
    }

    public List<BaseFood> loadMeal() throws RepositoryException
    {
        SqlSpecification specification=new SelectMealsSpec();
        return foodRepository.query(specification);
    }

    public List<BaseFood> loadDessert() throws RepositoryException
    {
        SqlSpecification specification=new SelectDessertsSpec();
        return foodRepository.query(specification);
    }

    public List<BaseFood> loadDrink() throws RepositoryException
    {
        SqlSpecification specification=new SelectDrinksSpec();
        return foodRepository.query(specification);
    }

    public List<BaseFood> loadOutOfStockMeal() throws RepositoryException
    {
        SqlSpecification specification=new SelectOutOfStockMealSpec();
        return foodRepository.query(specification);
    }

    public List<BaseFood> loadOutOfStockDessert() throws RepositoryException
    {
        SqlSpecification specification=new SelectOutOfStockDessertSpec();
        return foodRepository.query(specification);
    }

    public List<BaseFood> loadOutOfStockDrink() throws RepositoryException
    {
        SqlSpecification specification=new SelectOutOfStockDrinkSpec();
        return foodRepository.query(specification);
    }

}

package by.yan.cafe.action.manager;

import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.food.FoodRepository;
import by.yan.cafe.repository.food.specification.InsertNewFoodSpec;

public class FoodControlAction
{
    private static final String PIC_PATH_CONST="images/";

    public void addNewFood(String newFoodType, String newFoodName,String newFoodDescr,String newFoodPrice,String newFoodPicName) throws RepositoryException
    {
        String picPath=PIC_PATH_CONST+newFoodPicName;
        FoodRepository foodRepository=new FoodRepository();
        SqlSpecification specification=new InsertNewFoodSpec
                (newFoodType,newFoodName,newFoodDescr,Byte.valueOf(newFoodPrice),picPath);
        foodRepository.insert(specification);
    }

}

package by.yan.cafe.action.manager;

import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.food.FoodRepository;
import by.yan.cafe.repository.food.specification.UpdateFoodInfoByIdSpec;
import by.yan.cafe.repository.food.specification.UpdateFoodPriceToNegativeSpec;
import by.yan.cafe.repository.food.specification.UpdateFullFoodInfoByIdSpec;

public class ChangeFoodInfoAction
{
    private static final String PIC_PATH_CONST="images/";

    public void rewriteInfo(String name, String descr, String price, String foodType, int foodId) throws RepositoryException
    {
        FoodRepository foodRepository=new FoodRepository();
        SqlSpecification specification=new UpdateFoodInfoByIdSpec(name,descr,Byte.valueOf(price),foodType,foodId);
        foodRepository.insert(specification);
    }

    public void rewriteInfoWithPic(String name, String descr, String price, String foodType, int foodId, String picName) throws RepositoryException
    {
        String picPath=PIC_PATH_CONST+picName;

        FoodRepository foodRepository=new FoodRepository();
        SqlSpecification specification=new UpdateFullFoodInfoByIdSpec(name,descr,Byte.valueOf(price),foodType,foodId,picPath);
        foodRepository.insert(specification);
    }

    public void changeAvailabilityStatus(String foodType, int foodId) throws RepositoryException
    {
        FoodRepository foodRepository=new FoodRepository();
        SqlSpecification specification=new UpdateFoodPriceToNegativeSpec(foodId,foodType);
        foodRepository.insert(specification);
    }

}

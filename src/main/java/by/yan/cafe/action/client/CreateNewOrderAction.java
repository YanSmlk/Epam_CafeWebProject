package by.yan.cafe.action.client;

import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.entity.Order;
import by.yan.cafe.entity.food.impl.Dessert;
import by.yan.cafe.entity.food.impl.Drink;
import by.yan.cafe.entity.food.impl.Meal;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.order.OrderRepository;
import by.yan.cafe.repository.order.specification.InsertFoodInOrderSpec;
import by.yan.cafe.repository.order.specification.InsertNewOrderSpec;
import by.yan.cafe.repository.order.specification.SelectLastIdOrderByIdClientSpec;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.*;
import java.util.*;

public class CreateNewOrderAction
{
    private static final String PERSONAL_ACCOUNT_PAY_TYPE="personalAcc";
    private static final String BONUS_POINTS_PAY_TYPE="bonus";
    private static final String WHEN_RECEIVING_PAY_TYPE="whenRec";
    private static final int POINTS_ID=0;
    private static final int MONEY_ID=1;
    private static final int BONUS_POINTS_COEFFICIENT=4;
    private HashMap<Integer,Integer> drinkMap;
    private HashMap<Integer,Integer> dessertMap;
    private HashMap<Integer,Integer> mealMap;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    int idClient;

    public boolean checkPaymentPossibility(String payType,String login, byte orderPrice) throws RepositoryException
    {
        SqlSpecification specification=new SelectPointsMoneyFromUserByLoginSpec(login);
        UserRepository userRepository=new UserRepository();
        int[] balance=userRepository.getBalanceQuery(specification);
        switch (payType)
        {
            case PERSONAL_ACCOUNT_PAY_TYPE:
                return (balance[MONEY_ID]>=orderPrice);
            case BONUS_POINTS_PAY_TYPE:
                return (balance[POINTS_ID]>=orderPrice*BONUS_POINTS_COEFFICIENT);
            case WHEN_RECEIVING_PAY_TYPE:
                return true;
            default:
                return false;
        }
    }

    public void saveNewOrder(Order basket,String gettingTime, String login) throws RepositoryException
    {
        boolean flag=true;
        orderRepository=new OrderRepository();
        userRepository=new UserRepository();
        List<Drink> drinkArr=basket.getDrinkArr();
        List<Dessert> dessertArr=basket.getDessertArr();
        List<Meal> mealArr=basket.getMealArr();

        drinkMap=new HashMap<>();
        for (Drink drink:drinkArr)
        {
            drinkMap.put(drink.getId(), Collections.frequency(drinkArr,drink));
        }

        dessertMap=new HashMap<>();
        for (Dessert dessert:dessertArr)
        {
            dessertMap.put(dessert.getId(),Collections.frequency(dessertArr,dessert));
        }

        mealMap=new HashMap<>();
        for (Meal meal:mealArr)
        {
            mealMap.put(meal.getId(),Collections.frequency(mealArr,meal));
        }

        SqlSpecification idClientSpec=new SelectIdByLoginSpec(login);
        List<String> idClientStr=userRepository.stringQuery(idClientSpec);
        idClient=Integer.parseInt(idClientStr.get(0));

        SqlSpecification orderSpecification=new InsertNewOrderSpec
                (idClient,gettingTime, OrderStatus.Current, basket.getTotalPrice());
        orderRepository.insert(orderSpecification);

        SqlSpecification idOrderSpec=new SelectLastIdOrderByIdClientSpec(Integer.parseInt(idClientStr.get(0)));
        List<String> idOrder=userRepository.stringQuery(idOrderSpec);

        while (flag)
        {
            flag=insertToFoodInOrderTable(Integer.parseInt(idOrder.get(0)));
        }
    }

    public void doPayment(String payType,byte totalPrice) throws RepositoryException
    {
        switch (payType)
        {
            case PERSONAL_ACCOUNT_PAY_TYPE:
                SqlSpecification decMoneySpec=new UpdateMoneyByIdWhenPaySpec(idClient,totalPrice);
                userRepository.insert(decMoneySpec);
                break;
            case BONUS_POINTS_PAY_TYPE:
                SqlSpecification decPointsSpec=new UpdatePointsByIdWhenPaySpec
                        (idClient,totalPrice*BONUS_POINTS_COEFFICIENT);
                userRepository.insert(decPointsSpec);
                break;
            case WHEN_RECEIVING_PAY_TYPE:
                break;
        }
    }

    private boolean insertToFoodInOrderTable(int idOrder) throws RepositoryException
    {
        Map.Entry<Integer, Integer> drinkEntry=new AbstractMap.SimpleEntry<>(null, null);
        Iterator<Map.Entry<Integer, Integer>> drinkIter = drinkMap.entrySet().iterator();
        if (drinkIter.hasNext())
        {
            drinkEntry = drinkIter.next();
            drinkIter.remove();
        }

        Map.Entry<Integer,Integer> dessertEntry=new AbstractMap.SimpleEntry<>(null, null);
        Iterator<Map.Entry<Integer, Integer>> dessertIter = dessertMap.entrySet().iterator();
        if (dessertIter.hasNext())
        {
            dessertEntry=dessertIter.next();
            dessertIter.remove();
        }

        Map.Entry<Integer,Integer> mealEntry=new AbstractMap.SimpleEntry<>(null, null);
        Iterator<Map.Entry<Integer, Integer>> mealIter = mealMap.entrySet().iterator();
        if(mealIter.hasNext())
        {
           mealEntry=mealIter.next();
            mealIter.remove();
        }

        if(drinkEntry.getKey()!=null || dessertEntry.getKey()!=null || mealEntry.getKey()!=null)
        {
            SqlSpecification sqlSpecification = new InsertFoodInOrderSpec(idOrder, drinkEntry.getKey(), drinkEntry.getValue(),
                    dessertEntry.getKey(), dessertEntry.getValue(), mealEntry.getKey(), mealEntry.getValue());
            orderRepository.insert(sqlSpecification);
            return true;
        }
        return false;
    }

}

package by.yan.cafe.action.client;

import by.yan.cafe.entity.Order;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.order.OrderRepository;
import by.yan.cafe.repository.order.specification.SelectFoodOfOrderByOrderIdSpec;
import by.yan.cafe.repository.order.specification.SelectOrdersByClientLoginSpec;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.SelectPointsMoneyFromUserByLoginSpec;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientInfoLoadAction
{

    public int[] getClientInfo(String login) throws RepositoryException
    {
        SqlSpecification sqlSpecification=new SelectPointsMoneyFromUserByLoginSpec(login);
        UserRepository userRepository=new UserRepository();
        return (userRepository.getBalanceQuery(sqlSpecification));
    }

    public List<Order> getOrders(String login) throws RepositoryException
    {
        SqlSpecification sqlSpecification=new SelectOrdersByClientLoginSpec(login);
        OrderRepository orderRepository=new OrderRepository();
        List<Order> orderArr=orderRepository.query(sqlSpecification);
        for (Order order: orderArr)
        {
            SqlSpecification sqlSpecification1=new SelectFoodOfOrderByOrderIdSpec(order.getid());
            Order foodInOrder=orderRepository.query(sqlSpecification1).get(0);
            if (!foodInOrder.getDrinkArr().isEmpty())
            {
                order.getDrinkArr().addAll(foodInOrder.getDrinkArr());
            }
            if (!foodInOrder.getDessertArr().isEmpty())
            {
                order.getDessertArr().addAll(foodInOrder.getDessertArr());
            }
            if (!foodInOrder.getMealArr().isEmpty())
            {
                order.getMealArr().addAll(foodInOrder.getMealArr());
            }
        }

        Collections.sort(orderArr, new Comparator<Order>()
        {
            @Override
            public int compare(Order o1, Order o2)
            {
                if(o1.getid()>o2.getid())
                {return -1;}
                if (o1.getid()<o2.getid())
                {  return 1;}
                return 0;
            }
        });
        return orderArr;
    }

}

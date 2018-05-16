package by.yan.cafe.action.manager;

import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.entity.Order;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.order.OrderRepository;
import by.yan.cafe.repository.order.specification.SelectOrdersByStatusSpec;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagerInfoLoadAction
{
    private OrderRepository orderRepository=new OrderRepository();

    public List<Order> OrdersLoadByStatus(OrderStatus status) throws RepositoryException
    {
        SqlSpecification specification=new SelectOrdersByStatusSpec(status.toString());
        List<Order> orderArr=orderRepository.query(specification);

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

package by.yan.cafe.action.manager;

import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.order.OrderRepository;
import by.yan.cafe.repository.order.specification.UpdateOrderStatusById;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.UpdatePointsByLoginWhenIncSpec;

public class OrderControlAction
{
    private static final int BONUS_POINTS_COEFFICIENT=4;
    private OrderRepository orderRepository=new OrderRepository();
    private UserRepository userRepository=new UserRepository();

    public void confirmOrder(OrderStatus status, int id, String totalPrice, String clientName) throws RepositoryException
    {
        SqlSpecification specification=new UpdateOrderStatusById(status.toString(),id);
        orderRepository.insert(specification);

        double dPrice=Byte.valueOf(totalPrice);
        SqlSpecification addPointsSpec=new UpdatePointsByLoginWhenIncSpec(clientName,
                (int)Math.round(dPrice/BONUS_POINTS_COEFFICIENT));
        userRepository.insert(addPointsSpec);
    }

    public void cancelOrder(OrderStatus status,int id, String totalPrice,String clientName) throws RepositoryException
    {
        SqlSpecification specification=new UpdateOrderStatusById(status.toString(),id);
        orderRepository.insert(specification);
        double dPrice=Byte.valueOf(totalPrice);
        SqlSpecification decPointsSpec=new UpdatePointsByLoginWhenIncSpec(clientName,
                -((int)Math.round(dPrice/BONUS_POINTS_COEFFICIENT)));
        userRepository.insert(decPointsSpec);
    }

}

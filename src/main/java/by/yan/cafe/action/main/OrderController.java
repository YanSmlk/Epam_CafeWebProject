package by.yan.cafe.action.main;

import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.order.OrderRepository;
import by.yan.cafe.repository.order.specification.UpdateOldOrderSpec;
import org.apache.logging.log4j.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class OrderController extends Thread
{
    private final static int ORDER_CHECK_INTERVAL=3;
    private final static String maxTime="22:00";
    private final static String minTime="9:00";
    private OrderRepository orderRepository;

    public void run()
    {
        orderRepository=new OrderRepository();
        SqlSpecification specification=new UpdateOldOrderSpec();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        while (!isInterrupted())
        {
            String currTime = simpleDateFormat.format(new Date());
            try
            {
                Date currDate = simpleDateFormat.parse(currTime);
                Date maxDate = simpleDateFormat.parse(maxTime);
                Date minDate = simpleDateFormat.parse(minTime);

                if(currDate.after(maxDate)||currDate.before(minDate))
                {
                    try
                    {
                        orderRepository.insert(specification);
                    }
                    catch (RepositoryException ex)
                    {
                        LOGGER.log(Level.ERROR, "Failed to clear old orders: "+ex);
                    }
                }
                TimeUnit.HOURS.sleep(ORDER_CHECK_INTERVAL);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            catch (ParseException ex)
            {
                LOGGER.log(Level.FATAL, "Failed to create OrderController thread: "+ex);
                throw new RuntimeException(""+ex);
            }
        }
    }

}

package by.yan.cafe.validation;

import by.yan.cafe.exception.ActionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderInfoValidator
{
    private final static String maxTime="22:00";
    private final static String minTime="9:00";

    public static boolean timeCheck(String gettingTime) throws ActionException
    {
        if (!gettingTime.isEmpty())
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String currTime = simpleDateFormat.format(new Date());
            try
            {
                Date gettingDate = simpleDateFormat.parse(gettingTime);
                Date currDate = simpleDateFormat.parse(currTime);
                Date maxDate = simpleDateFormat.parse(maxTime);
                Date minDate = simpleDateFormat.parse(minTime);
               // return ((gettingDate.before(maxDate))&&(gettingDate.after(minDate)));
                return ((gettingDate.before(maxDate))&&(gettingDate.after(minDate))&&(gettingDate.after(currDate)));
            }
            catch (ParseException ex)
            {
                throw new ActionException("Failed to check time value "+ex);
            }
        }
        return false;
    }

}

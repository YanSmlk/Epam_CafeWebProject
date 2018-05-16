package by.yan.cafe.exception;


public class ActionException extends Exception
{
    public ActionException(Throwable cause)
    {
        super(cause);
    }

    public ActionException(String message)
    {
        super(message);
    }
}

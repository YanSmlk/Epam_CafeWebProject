package by.yan.cafe.exception;

public class RepositoryException extends Exception
{
    public RepositoryException(Throwable cause)
    {
        super(cause);
    }

    public RepositoryException(String message)
    {
        super(message);
    }
}

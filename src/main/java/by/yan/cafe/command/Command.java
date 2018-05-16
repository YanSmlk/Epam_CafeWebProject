package by.yan.cafe.command;

import by.yan.cafe.servlet.Router;
import javax.servlet.http.HttpServletRequest;

public interface Command
{
    Router execute(HttpServletRequest request);
}

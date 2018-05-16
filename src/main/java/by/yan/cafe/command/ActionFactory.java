package by.yan.cafe.command;

import java.util.Optional;

public class ActionFactory
{
    public static Optional<Command> defineCommand(String commandName)
    {
        CommandType commandType = CommandType.valueOf(commandName);
        return (Optional.of(commandType.getCommand()));
    }

}

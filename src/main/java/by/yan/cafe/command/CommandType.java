package by.yan.cafe.command;

import by.yan.cafe.action.client.AddMoneyAction;
import by.yan.cafe.action.client.AddToOrderAction;
import by.yan.cafe.action.client.CreateNewOrderAction;
import by.yan.cafe.action.main.LoginUserAction;
import by.yan.cafe.action.main.MainForwardAction;
import by.yan.cafe.action.main.MenuShowAction;
import by.yan.cafe.action.main.RegistrClientAction;
import by.yan.cafe.action.manager.FoodControlAction;
import by.yan.cafe.action.manager.ChangeFoodInfoAction;
import by.yan.cafe.action.manager.OrderControlAction;
import by.yan.cafe.command.impl.client.*;
import by.yan.cafe.command.impl.main.*;
import by.yan.cafe.command.impl.manager.*;

public enum CommandType
{
    MAIN_FORWARD(new MainForwardCommand(new MainForwardAction())),
    REDIRECT_GET(new RedirectGetCommand()),
    CHANGE_LANG(new ChangeLangCommand()),
    REGISTR_CLIENT(new RegistrClientCommand(new RegistrClientAction())),
    LOGIN_USER(new LoginUserCommand(new LoginUserAction())),
    MAIL_CONFIRM(new MailConfirmationCommand(new RegistrClientAction() )),
    LOGOUT(new LogoutCommand()),
    ADD_MONEY(new AddMoneyCommand(new AddMoneyAction())),
    MENU_SHOW(new MenuShowCommand(new MenuShowAction())),
    ADD_TO_ORDER(new AddToOrderCommand(new AddToOrderAction())),
    CREATE_NEW_ORDER(new CreateNewOrderCommand(new CreateNewOrderAction())),
    CONFIRM_ORDER(new ConfirmOrderCommand(new OrderControlAction())),
    CANCEL_ORDER(new CancelOrderCommand(new OrderControlAction())),
    CHANGE_FOOD_INFO(new ChangeFoodInfoCommand(new ChangeFoodInfoAction())),
    CHANGE_AVAILABLE_STATUS(new ChangeAvailableStatusCommand(new ChangeFoodInfoAction())),
    ADD_NEW_FOOD(new AddNewFoodCommand(new FoodControlAction())),
    CLEAR_BASKET(new ClearBasketCommand());

    private Command command;

    CommandType(Command command)
    {
        this.command = command;
    }

    public Command getCommand()
    {
        return command;
    }

}

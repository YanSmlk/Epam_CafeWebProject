package by.yan.cafe.action.main;

import by.yan.cafe.constant.PagePath;

public class MainForwardAction
{
    private static final String LOGIN_VALUE="logination";
    private static final String REGISTR_VALUE="registration";
    private static final String MAIN_VALUE="main";
    private static final String PROFILE_CLIENT_VALUE="profileClient";
    private static final String PROFILE_MANAGER_VALUE="profileManager";
    private static final String DEMO_FOOD_MENU_VALUE="demoFoodMenu";
    private static final String CONTROL_FOOD_MENU="controlFoodMenu";

    public String getPagePath(String actionValue)
    {
        switch (actionValue)
        {
            case LOGIN_VALUE:
                return PagePath.LOGIN_PAGE;
            case REGISTR_VALUE:
                return PagePath.REGISTRATION_PAGE;
            case MAIN_VALUE:
                return PagePath.MAIN_PAGE;
            case PROFILE_CLIENT_VALUE:
                return PagePath.CLIENT_PROFILE_PAGE;
            case DEMO_FOOD_MENU_VALUE:
                return PagePath.DEMO_FOOD_MENU_PAGE;
            case PROFILE_MANAGER_VALUE:
                return PagePath.MANAGER_PROFILE_PAGE;
            case CONTROL_FOOD_MENU:
                return PagePath.CONTROL_MENU_PAGE;
            default:
                return PagePath.MAIN_PAGE;
        }
    }

}

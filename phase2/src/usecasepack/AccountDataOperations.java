package usecasepack;

import entitypack.MetroArea;

public interface AccountDataOperations {

    boolean validateLogin(String username, String password);

    boolean isTradingUser(String username);

    boolean addNewLogin(String username, String password, boolean isTrading, MetroArea metro);
}

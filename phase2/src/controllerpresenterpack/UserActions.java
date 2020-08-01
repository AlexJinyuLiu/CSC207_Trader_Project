package controllerpresenterpack;

import usecasepack.AccountDataOperations;

public class UserActions implements ActionController{

    /**
     * Return true iff username and password are a valid user login.
     * @param username the username in question
     * @param password the password in question
     * @return a boolean determining whether or not the login info is valid.
     */
    public boolean validateLogin(AccountDataOperations userManager, String username, String password){
        return userManager.validateLogin(username, password);
    }

    /**
     * Return true iff the user with username is a TradingUser
     * @param username the username of the user.
     * @return a boolean determining whether or not the user is a TradingUser
     */
    public boolean isTradingUser(AccountDataOperations userManager, String username){
        return userManager.isTradingUser(username);
    }
}

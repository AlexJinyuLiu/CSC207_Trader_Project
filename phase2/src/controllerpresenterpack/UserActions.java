package controllerpresenterpack;

import entitypack.MetroArea;
import entitypack.User;
import usecasepack.AccountDataOperations;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.util.ArrayList;

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

    /**
     * Gets a list of all users in the specified MetroArea
     * @param metroArea the area to search for user in
     * @return an arraylist of all users in that area.
     */
    public ArrayList<User> getLocalUsers(UserManager userManager, MetroArea metroArea){
        return userManager.searchUsersByMetro(metroArea);
    }

    /**
     * fetch the MetroArea of the user with specified username
     * @param username the username of the user.
     * @return the metro area of the user.
     */
    public MetroArea getUsersMetroArea(UserManager userManager, String username){
        return userManager.searchUser(username).getMetro();
    }

    /**
     * Returns the user object associated with specified username
     * @param username the username of the user to search for.
     * @return the user object associated with username
     */
    public User searchUser(UserManager userManager, String username){
        return userManager.searchUser(username);
    }

}

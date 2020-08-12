package controllerpresenterpack;

import alertpack.MessageAlert;
import alertpack.UserAlert;
import entitypack.BrowsingUser;
import entitypack.*;
import usecasepack.*;

import java.util.ArrayList;


/**
 * A controller class that dictates what can be done on the Admin's menu.
 */
public class AdminActions implements UserBrowsing, ActionController{


    //TODO: the below method are to be called from input 6.
    public void viewAllUsers(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                             User userViewing, ItemManager itemManager){

    }

    public void viewUser(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                         User userToView, User userViewing,ItemManager itemManager){

    }

    /**
     * Return true iff username and password is a valid admin login.
     * @param username the username in question
     * @param password the password in question
     * @return a boolean determining the validity of the login info.
     */
    public boolean validateLogin(AdminUser adminUser, String username, String password){
        return adminUser.validateLogin(username, password);
    }

    /**
     *
     * @return all pending item validation requests in an arraylist.
     */
    public ArrayList<ItemValidationRequest> getItemValidationRequests(ItemManager itemManager){
        return itemManager.getValidationRequests();
    }

    /**
     * Approves the specified ItemValidationRequest, deleting it from the list of requests and creating a corresponding
     * item.
     * @param validationRequest the ItemValidationRequest to approve.
     */
    public void approveItemValidationRequest(ItemManager itemManager, ItemValidationRequest validationRequest){
        itemManager.approveValidationRequest(validationRequest);
    }


    /** Method that changes the threshold value (The necessary difference between the number of
     * items users have lent and borrowed before they can make another transaction)
     * @param adminUser UseCasePack.AdminUser logged in making changes
     * @param input the new threshold value
     * @param tradeCreator creating the trade
     */
    public void changeBorrowLendThreshold(AdminUser adminUser,
                                           TradeCreator tradeCreator, int input) {
        adminUser.changeBorrowLendThreshold(tradeCreator, input);
    }

    /** Method that changes the threshold of weekly trades a user can make
     *
     * @param input new threshold value
     * @param adminUser UseCasePack.AdminUser logged in making changes
     * @param tradeCreator TradeCreator object associated with the program
     */
    public void changeCompleteThreshold(int input, AdminUser adminUser,
                                         TradeCreator tradeCreator) {
        adminUser.changeCompleteThreshold(tradeCreator, input);
    }

    /** Method that changes the threshold of incomplete trades a user can have
     *
     * @param input new threshold value
     * @param adminUser UseCasePack.AdminUser logged in making changes
     * @param userManager UserManager object associated with the program
     */
    public void changeIncompleteThreshold(int input, AdminUser adminUser,
                                           UserManager userManager) {
        adminUser.changeIncompleteThreshold(userManager, input);

    }

    /** Method that creates additional logins for UseCasePack.AdminUser account
     *
     * @param username the username of the new login
     * @param password the password of the new login
     */
    public boolean addNewLogin(AdminUser loginData, String username, String password) {
        return loginData.addNewLogin(username, password);
    }

    /** Method which allows an admin to edit or undo a trade
     *
     * @param tradeID ID of the trade
     * @param input state of the trade
     * @param adminUser AdminUser object associated with program
     * @param tradeCreator TradeCreator object associated with program
     * @param itemManager ItemManager object associated with program
     * @param userManager UserManager object associated with program
     */
    public void editTrade(int tradeID, String input, AdminUser adminUser, TradeCreator tradeCreator,
                          ItemManager itemManager, UserManager userManager) {

            if (input.equals("Request")) {
                adminUser.unsendTradeRequest(tradeID, tradeCreator);
            } else if (input.equals("Confirmed")) {
                adminUser.cancelTradeRequest(tradeID, tradeCreator);
            } else if (input.equals("Completed")) {
                adminUser.undoTrade(tradeID, tradeCreator.getTradeHistories(), itemManager, userManager);
            }
        }

    /** Method which returns the list of all users in the program
     *
     * @param userManager UserManager
     * @return list of all users
     */
    public ArrayList<User> viewAllUsers(UserManager userManager){
        return userManager.getListUsers();
    }

}

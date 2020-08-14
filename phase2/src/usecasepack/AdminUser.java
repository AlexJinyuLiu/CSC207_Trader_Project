package usecasepack;

import entitypack.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A use case class describing the business rules for admin functionality.
 */
public class AdminUser implements Serializable{
    //author: Tingyu Liang, Riya Razdan in group 0110 for CSC207H1 summer 2020 project

    /**
     * Stores all login info for admins.
     */
    private ArrayList<AdminLogin> adminLogins = new ArrayList<AdminLogin>();

    /**
     * Stores all usernames of users who want to be unfrozen.
     */
    private ArrayList<String> adminMessages = new ArrayList<String>();

    private AdminValidateLoginStrategy strategy = new AdminValidateLoginStrategy();


    /**
     * Return true iff username and password are a valid admin login.
     * @param username the username in question
     * @param password the password being validated
     * @return a boolean determining whether or not the login is valid.
     */
    public boolean validateLogin(String username, String password) {
        return strategy.validateLogin(username, password, strategy.turnListIntoHashMap(adminLogins));
    }
    /**
     * Constructor for UseCasePack.AdminUser
     * @param username string for the admin's username
     * @param password string for the admin's password
     */
    public AdminUser(String username, String password) {

        addNewLogin(username, password);
    }


    /**
     * Returns whether or not <username> is taken by annother user on the system.
     * @param username the username in question.
     * @return a boolean determining whether or not the username is valid.
     */
    public boolean isValidUsername(String username)
    {
        return strategy.usernameAvailable(username, strategy.turnListIntoHashMap(adminLogins));
    }

    /**
     * Adds a login (username and password) for a user on the system.
     * @param username the inputed username
     * @param password the password associated with the account
     * @return a boolean describing whether or not the login was successfully added.
     */
    public boolean addNewLogin(String username, String password){
        if (!isValidUsername(username)){
            return false;
        }

        AdminLogin login = new AdminLogin(username, password);
        adminLogins.add(login);
        return true;
    }

    /**
     * Adds message to the messages list of AdminUser
     * @param message the message received by AdminUser
     */
    public void addAdminMessage(String message) {
        adminMessages.add(message);
    }

    /**
     * @return the messages list of the AdminUser
     */
    public ArrayList<String> getAdminMessages() {
        return adminMessages;
    }

    /**
     * Changes the threshold for the number of incomplete trades a user can have before they are frozen.
     * @param userManager the UseCasePack.UserManager initialized in the system.
     * @param incompleteThreshold the new threshold.
     */
    public void changeIncompleteThreshold(UserManager userManager, int incompleteThreshold) {
        userManager.setIncompleteThreshold(incompleteThreshold);
    }

    /**
     * Changes the threshold for the number of complete trades a user can perform in one week.
     * @param tradeCreator the UseCasePack.TradeCreator initialized in the system
     * @param completeThreshold the new threshold.
     */
    public void changeCompleteThreshold(TradeCreator tradeCreator, int completeThreshold) {
        tradeCreator.setCompleteThreshold(completeThreshold);
    }

    /**
     * Manages all startup functionality for the admin
     */
    public void onStartUp(){

    }

    /** Method which freezes the user account and sends a FrozenAlert to the user
     * author: tian
     * @param user user object to freeze
     */
    public void freezeUser(TradingUser user){
        user.setFrozen(true);
    }

    /**
     * Unfreezes user account
     * @param user account to unfreeze
     */
    public void unfreezeAccount(TradingUser user){
        user.setFrozen(false);
    }

    /**
     * Change the borrow/lend threshold value
     * @param newThreshold int variable for new threshold
     */
    public void changeBorrowLendThreshold(TradeCreator tradeCreator, int newThreshold) {
        tradeCreator.setBorrowLendThreshold(newThreshold);
    }

    /**
     * Removes the trade request with tradeID from the program
     * @param tradeID the trade ID of the trade request that is expected to be removed
     * @param tradeCreator the use case class TradeCreator
     */
    public void unsendTradeRequest(int tradeID, TradeCreator tradeCreator) {
        tradeCreator.getPendingTradeRequests().removeIf(trade -> trade.getTradeID() == tradeID);
    }

    /**
     * Modifies the confirmed trade request with tradeID to be a pending trade request
     * @param tradeID the trade ID of the trade request that is expected to be modified
     * @param tradeCreator the use case class TradeCreator
     */
    public void cancelTradeRequest(int tradeID, TradeCreator tradeCreator) {
        Trade tradeToModify = null;
        for (Trade pendingTrade : tradeCreator.getPendingTrades()) {
            if (pendingTrade.getTradeID() == tradeID) {
                tradeToModify = pendingTrade;
            }
        }
        if (tradeToModify != null) {
            tradeToModify.setUser1AcceptedRequest(false);
            tradeToModify.setUser2AcceptedRequest(false);
            tradeToModify.setUser1NumRequests(0);
            tradeToModify.setUser2NumRequests(0);
            tradeCreator.getPendingTradeRequests().add(tradeToModify);
            tradeCreator.getPendingTrades().remove(tradeToModify);
        }
    }

    /**
     * Removes the complete trade with tradeID and exchange the items back to
     * @param tradeID the trade ID of the trade request that is expected to be removed
     * @param tradeHistories the use case class TradeHistories
     * @param itemManager the use case class ItemManager
     * @param userManager the use case class UserManager
     */
    public void undoTrade(int tradeID, TradeHistories tradeHistories, ItemManager itemManager, UserManager userManager){
        Trade tradeToModify = null;
        for (Trade trade : tradeHistories.getCompletedTrades()) {
            if (trade.getTradeID() == tradeID) {
                tradeToModify = trade;
            }
        }
        if (tradeToModify != null) {
            tradeHistories.getCompletedTrades().remove(tradeToModify);
            tradeHistories.getDeadTrades().add(tradeToModify);
            User user1 = userManager.searchUser(tradeToModify.getUsername1());
            User user2 = userManager.searchUser(tradeToModify.getUsername2());
            itemManager.undoExchangeItems(tradeToModify, (TradingUser) user1, (TradingUser) user2 );
        }
    }

}

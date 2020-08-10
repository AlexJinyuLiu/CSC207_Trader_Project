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
    private ArrayList<String> unfreezeRequests = new ArrayList<String>();

    /**
     * Stores the list of alerts to be processed by the admin
     */
    private ArrayList<Prompt> adminAlerts;

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
        adminAlerts = new ArrayList<Prompt>();
        addNewLogin(username, password);
    }

    /**
     * Adds a username to the list of usernames of users who want to be unfrozen.
     * @param username the username of the user.
     */
    public void addUnfreezeRequest(String username){
        this.unfreezeRequests.add(username);
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
     * Getter for the admin alert list.
     * @return the list of admin alerts.
     */
    public ArrayList<Prompt> getAdminAlerts() {
        return adminAlerts;
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
     * @param userManager the UseCasePack.UserManager initialized in the system.
     * @param tradeCreator the UseCasePack.TradeCreator
     */
    public void onStartUp(UserManager userManager, TradeCreator tradeCreator){
        this.adminAlerts = userManager.fetchAdminAlerts();
        this.adminAlerts.addAll(tradeCreator.fetchAdminAlerts());
        userManager.clearAdminAlerts();
    }

    /*Temporarily abandoned by Tingyu
    public HashMap<String, String> getLogInInfo(){
        return this.loginInfo;
    }

    Callan made the function below to search for username in case we are using hashmap again
     public String getUsername(){
        return (String) getLogInInfo().keySet().toArray()[0];
     }
     */




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

    public void unsendTradeRequest(int tradeID, TradeCreator tradeCreator) {
        tradeCreator.getPendingTradeRequests().removeIf(trade -> trade.getTradeID() == tradeID);
    }

    public void cancelTradeRequest(int tradeID, TradeCreator tradeCreator) {
        tradeCreator.getPendingTrades().removeIf(trade -> trade.getTradeID() == tradeID);
    }

    public void undoTrade(int tradeID, TradeHistories tradeHistories, ItemManager itemManager, UserManager userManager){
        for (Trade trade : tradeHistories.getCompletedTrades()) {
            if (trade.getTradeID() == tradeID) {
                tradeHistories.getCompletedTrades().remove(trade);
                tradeHistories.getDeadTrades().add(trade);
                User user1 = userManager.searchUser(trade.getUsername1());
                User user2 = userManager.searchUser(trade.getUsername2());
                itemManager.exchangeItems(trade, (TradingUser) user2, (TradingUser) user1);
                ((TradingUser) user1).increaseNumBorrowed(-2);
                ((TradingUser) user2).increaseNumBorrowed(-1);
            }
        }
    }

}

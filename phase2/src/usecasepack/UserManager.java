package usecasepack;

import alertpack.*;
import entitypack.*;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;

/**
 * A use case class describing the business rules of the users in the trade system.
 */
public class UserManager implements Serializable{
    //Note: We are aware this is static. We are afraid to delete it because we don't remember who put it here and why,
    // also we have to submit our phase 1 in an hour so please ignore this its probably not nescesary. Ty <3
    private static final long serialVersionUID = 886852790530090694L;
    //author: Jinyu Liu, Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    //All methods written by Jinyu Liu except where noted

    //UseCasePack.UserManager
    private ArrayList<User> listUsers = new ArrayList<User>(); // List of all users - Jinyu

    //TradeManager AND UseCasePack.UserManager
    private ArrayList<AdminAlert> adminAlerts = new ArrayList<AdminAlert>();

    //UseCasePack.UserManager
    private HashMap<String, ArrayList<UserAlert>> alertSystem = new HashMap<String, ArrayList<UserAlert>>();

    //UseCasePack.UserManager -- all thresholds are admin things really, rethink this?
    private int incompleteThreshold = 3; // # of incomplete trades allowed

    private int itemIDGenerator = 0;

    /**
     * Method called on program Start up. Currently fetches all userAlerts from other places in the program.
     * @param tradeCreator the UseCasePack.TradeCreator used in this program.
     */
    public void onStartUp(TradeCreator tradeCreator){
        HashMap<String, ArrayList<UserAlert>> alertsToAdd = tradeCreator.fetchUserAlerts();

        for (String key : alertsToAdd.keySet()){
            if (alertSystem.containsKey(key)){
                ArrayList<UserAlert> alertsForUser = alertSystem.get(key);
                alertsForUser.addAll(alertsToAdd.get(key));
                alertSystem.put(key, alertsForUser);
            } else {
                alertSystem.put(key, alertsToAdd.get(key));
            }
        }
    }

    /**
     * Method for a EntityPack.User to send a reportAlert to Administrator
     * @param sender the username of the USer sending the report
     * @param reportedUser  the username of the EntityPack.User to whom the sender wishes to report
     * @param message the message the sender would like to include
     * @param isTradeComplete whether the trade between the user is complete
     */
    public void reportUser(String sender, String reportedUser, String message, boolean isTradeComplete){
        AdminAlert alert = new ReportAlert(sender, reportedUser, isTradeComplete, message);
        this.alertAdmin(alert);
    }

    /**
     * Return a list of all users registered in the program.
     * @return an arraylist of all users.
     */
    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    /**
     * clears all admin alerts.
     */
    public void clearAdminAlerts(){
        adminAlerts = new ArrayList<AdminAlert>();
    }

    /**
     * Returns the user alerts for the user with the given username.
     * @param username the username in question
     * @return an arraylist containing all alerts for that user.
     */
    public ArrayList<UserAlert> getUserAlerts(String username){
        return alertSystem.get(username);
    }

    /**
     * Alerts the admin.
     * @param alert alert
     */ //TradeManager and UseCasePack.UserManager
    public void alertAdmin(AdminAlert alert){
        this.adminAlerts.add(alert);
    }


    /** 3-arg method which creates and instantiates an ItemvalidationRequest.
     * Author: Jinyu Liu
     * @param name name of the item
     * @param description description of the item
     * @param owner username of the user who will own the item
     * @return The ItemValidationRequestAlert in question.
     */ //TradeManager for cohesion reasons with alerting admin.
    public ItemValidationRequestAlert sendValidationRequest(String name, String description, String owner) {
        // reworked by Tingyu since the itemValidationRequestQueue has been moved to UseCasePack.UserManager
        ItemValidationRequestAlert alert = new ItemValidationRequestAlert(itemIDGenerator, owner, name, description);
        itemIDGenerator++;
        alertAdmin(alert);
        return alert;
    }

    /** Method which creates a user and adds it to the list of users
     * Author: Jinyu Liu
     * @param username username of user
     * @param password password of user
     * @return the created user
     */ //UseCasePack.UserManager
    public TradingUser createTradingUser(String username, String password) throws UserNameTakenException {
        for (User user : listUsers) {
            if (user.getUsername().equals(username)) {
                throw new UserNameTakenException();
            }
        }
        TradingUser newUser = new TradingUser(username);
        newUser.setPassword(password);
        listUsers.add(newUser);
        alertSystem.put(username, new ArrayList<UserAlert>());
        return newUser;
    }

    /** Method which creates a user and adds it to the list of users
     * Author: Jinyu Liu
     * @param username username of user
     * @param password password of user
     * @return the created user
     */ //UseCasePack.UserManager
    public BrowsingUser createBrowsingUser(String username, String password) throws UserNameTakenException {
        for (User user : listUsers) {
            if (user.getUsername().equals(username)) {
                throw new UserNameTakenException();
            }
        }
        BrowsingUser newUser = new BrowsingUser(username);
        newUser.setPassword(password);
        listUsers.add(newUser);
        alertSystem.put(username, new ArrayList<UserAlert>());
        return newUser;
    }

    /**
     * Adds an item to the user's wishlist
     * @param user user
     * @param itemName name of item
     */ //UseCasePack.UserManager
    public void addToWishlist(TradingUser user, String itemName){
        user.addItemToWishList(itemName);
    }

    /**
     * removes an item from the user's wishlist
     * @param user user
     * @param itemName name of item
     */ //UseCasePack.UserManager
    public void removeFromWishList(TradingUser user, String itemName){
        user.removeItemFromWishList(itemName);
    }

    public void reExchangeItems(){}

    /**
     * @param user the trading user that the items in their wishlist are being inspected
     * @param itemName the name of the item that is being searched for
     * @return true iff user contains itemName in their wishlist
     */
    public boolean checkIfUserContain(TradingUser user, String itemName) { return user.containItemInWishlist(itemName); }

    /**
     * @param user the trading user that the items in their inventory are being inspected
     * @param itemID the ID of the item that is being searched for
     * @return true iff user contains the item with itemID in their inventory
     */
    public boolean checkIfUserContain(TradingUser user, int itemID) { return user.containItemInInventory(itemID); }

    /**
     * Return a list of all adminAlerts from this class. Also empties the adminAlerts member.
     * @return the list of adminAlerts
     */
    public ArrayList<AdminAlert> fetchAdminAlerts(){
        ArrayList<AdminAlert> alerts = this.adminAlerts;
        this.adminAlerts = new ArrayList<AdminAlert>();
        return alerts;
    }

    /** Method which returns a user when given their username
     * Author: Louis Scheffer V
     * @param username username of the user
     * @return user object
     */ //UseCasePack.UserManager
    public User searchUser(String username){
        for(User user: listUsers){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    /** Overloaded method to send an alert to a user. This one uses a user object.
     * Author: Louis Scheffer V
     * @param user object of the user who will be receiving the alert
     * @param alert alert object to send to the user
     */ //UseCasePack.UserManager AND TradeManager
    public void alertUser(User user, UserAlert alert){
        String username = user.getUsername();
        alertUser(username, alert);
    }

    /** Overloaded method to send an alert to a user. This one uses a username.
     * Author: Louis Scheffer V
     * @param username username of the user receiving the alert
     * @param alert alert object to send to the user
     */ //UseCasePack.UserManager AND TradeManager
    public void alertUser(String username, UserAlert alert){
        alertSystem.get(username).add(alert);
    }

    /** Method which allows a user to send a message to another user, using the alert system.
     * Author: Louis Scheffer V
     * @param sender user object who is sending the message.
     * @param recipient user object who is receiving the message.
     * @param message message text.
     */ //UseCasePack.UserManager
    public void sendMessageToUser(String sender, User recipient, String message){
        UserAlert alert = new MessageAlert(sender, message);
        alertUser(recipient, alert);
    }

    /**
     *
     * @return the threshold of lent - borrowed which all users should be at or above.
     */
    public int getIncompleteThreshold() {
        return incompleteThreshold;
    }

    /**
     *
     * @param incompleteThreshold the threshold of lent - borrowed which all users should be at or above.
     */
    public void setIncompleteThreshold(int incompleteThreshold) {
        this.incompleteThreshold = incompleteThreshold;
    }

    /**
     *
     * @param user the number by which to increase the number of incomplete trades attributed to a user by.
     */
    public void increaseUserIncompleteTrades(TradingUser user){
        user.increaseNumIncompleteTrades(1);
    }

    /**
     *
     * @param user the user which is the number of incomplete trades is being queried.
     * @return the number of incomplete trades attributed to the user.
     */
    public int getUserIncompleteTrades(TradingUser user){
        return user.getNumIncompleteTrades();
    }

    /** Method to set which metropolitan area a user is based in.
     *
     * @param username the username of the user.
     * @param metroArea the metro area the user will be based in.
     */
    public void setUserMetro(String username, MetroArea metroArea){
        User user = searchUser(username);
        user.setMetro(metroArea);
    }

    /** Method to return a list of users by the metropolitan area they are located in.
     *
     * @param metroArea metro area you are searching
     * @return the list of active users in that metro.
     */
    public ArrayList<User> searchUsersByMetro(MetroArea metroArea){
        ArrayList<User> usersInMetro = new ArrayList<User>();
        for(User user: listUsers){
            if (user.getMetro().equals(metroArea)){
                usersInMetro.add(user);
            }
        }
        return usersInMetro;
    }

    /** Method to get the metro area of a user
     *
     * @param user the user whose metro area you are checking.
     * @return the user
     */
    public MetroArea getUsersMetro(User user){
        return user.getMetro();
    }
}
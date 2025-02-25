import alertpack.*;
import entitypack.Item;
import entitypack.TemporaryTrade;
import entitypack.Trade;
import entitypack.User;

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

    /** Method which exchanges the items in the trade system after a trade has been marked as completed
     * Author: Louis Scheffer V
     * @param trade trade object
     */ //TradeManager
    public void exchangeItems(Trade trade){
        User user1 = searchUser(trade.getUsername1());
        User user2 = searchUser(trade.getUsername2());
        for(int itemID : trade.getItemIDsSentToUser1()){

            Item item = searchItem(user2, itemID);
            for (String string: user1.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    user1.removeItemFromWishList(string);
                }
            }
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user1.increaseNumBorrowed(1);
            user2.increaseNumLent(1);
            user2.removeAvailableItem(item);
            if (trade instanceof TemporaryTrade){
                user1.addBorrowedItem(item);
            }
            else {
                user1.addAvailableItem(item);
            }
        }
        for(int itemID : trade.getItemIDsSentToUser2()){
            Item item = searchItem(user1, itemID);
            for (String string: user2.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    user2.removeItemFromWishList(string);
                }
            }
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user2.increaseNumBorrowed(1);
            user1.increaseNumLent(1);
            user1.removeAvailableItem(item);
            if (trade instanceof TemporaryTrade){
                user2.addBorrowedItem(item);
            }
            else{
                user2.addAvailableItem(item);
            }

        }
    }

    /** Method which returns items to their owners after the expiration of a temporary trade
     * Author: Louis Scheffer V
     * @param trade Temporary EntityPack.Trade Object
     */ //TradeManager???
    public void reExchangeItems(TemporaryTrade trade){
        User user1 = searchUser(trade.getUsername1());
        User user2 = searchUser(trade.getUsername2());
        for(int itemID : trade.getItemIDsSentToUser1()) {
            Item item = searchItem(user2, itemID);
            user1.removeBorrowedItem(item);
            user2.addAvailableItem(item);
        }
        for(int itemID : trade.getItemIDsSentToUser2()) {
            Item item = searchItem(user1, itemID);
            user2.removeBorrowedItem(item);
            user2.addAvailableItem(item);
        }
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
    public User createUser(String username, String password) throws UserNameTakenException {
        for (User user : listUsers) {
            if (user.getUsername().equals(username)) {
                throw new UserNameTakenException();
            }
        }
        User newUser = new User(username);
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
    public void addToWishlist(User user, String itemName){
        user.addItemToWishList(itemName);
    }

    /**
     * removes an item from the user's wishlist
     * @param user user
     * @param itemName name of item
     */ //UseCasePack.UserManager
    public void removeFromWishList(User user, String itemName){
        user.removeItemFromWishList(itemName);
    }


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

    /** Method which returns a item (that is in a user's available items) when given its ID number and the user who it
     * belongs to. Returns null if the ID is invalid.
     * Author: Louis Scheffer V
     * @param user who owns the item
     * @param itemID ID number of the item
     * @return item
     */ //Wherever is most convenient
    public Item searchItem(User user, int itemID){
        for(Item item: user.getAvailableItems()){
            if(itemID == item.getId()){
                return item;
            }
        }
        return null;
    }

    /** Method which returns a item (that is in a user's available items) when given its ID number.
     * Returns null if an invalid ID is given
     * Author: Louis Scheffer V
     * @param itemID ID number of the item
     * @return item
     *///Ditto from above
    public Item searchItem(int itemID) {
        for (User user : listUsers) {
            for (Item item : user.getAvailableItems()) {
                if (itemID == item.getId()) {
                    return item;
                }
            }
            for (Item item: user.getBorrowedItems()){
                if (itemID == item.getId()) {
                    return item;
                }
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
    public void sendMessageToUser(User sender, User recipient, String message){
        UserAlert alert = new MessageAlert(sender.getUsername(), message);
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
    public void increaseUserIncompleteTrades(User user){
        user.increaseNumIncompleteTrades(1);
    }

    /**
     *
     * @param user the user which is the number of incomplete trades is being queried.
     * @return the number of incomplete trades attributed to the user.
     */
    public int getUserIncompleteTrades(User user){
        return user.getNumIncompleteTrades();
    }
}
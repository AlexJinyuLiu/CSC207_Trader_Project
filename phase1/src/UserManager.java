import AlertPack.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Serializable{
    //author: Jinyu Liu, Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    //All methods written by Jinyu Liu except where noted

    //protected ArrayList<Trade> transactions = new ArrayList<Trade>(); // list of all transactions this User has completed since creation - Mel
    //I believe this is identical to completed trades

    protected ArrayList<Trade> completedTrades = new ArrayList<Trade>(); // list of all trades which have been completed - Louis

    protected ArrayList<User> listUsers = new ArrayList<User>(); // List of all users - Jinyu

    protected ArrayList<Trade> pendingTradeRequests = new ArrayList<Trade>(); // list of all trade requests which have not been accepted
    // by both parties - Louis

    protected ArrayList<AdminAlert> adminAlerts = new ArrayList<AdminAlert>();

    protected ArrayList<Trade> pendingTrades = new ArrayList<Trade>(); // list of all trades which have been accepted but not completed - Louis

    protected ArrayList<TemporaryTrade> currentTemporaryTrades = new ArrayList<TemporaryTrade>(); //list of all temporary trades where items have
    // been exchanged but not returned -Louis

    protected HashMap<String, ArrayList<UserAlert>> alertSystem = new HashMap<String, ArrayList<UserAlert>>();

    public ArrayList<User> getListUsers() {
        return listUsers;}

    public ArrayList<Trade> getCompletedTrades() {
        return completedTrades;
    }

    public ArrayList<Trade> getPendingTrades() {
        return pendingTrades;
    }

    public ArrayList<Trade> getPendingTradeRequests() {
        return pendingTradeRequests;
    }

    public static ArrayList<ItemValidationRequestAlert> itemValidationRequestQueue = new ArrayList<ItemValidationRequestAlert>();

    private int incompleteThreshold; // # of incomplete trades allowed
    private int completeThreshold; // # of complete trades allowed per week

    private int borrowLendThreshold = 1;


    public ArrayList<UserAlert> getAlerts(String username){
        return alertSystem.get(username);
    }
    public void onStartUp(){
        //Calls all initialization stuff for UserManager.
    }
    /**
     * Alerts the admin.
     */
    public void alertAdmin(AdminAlert alert){
        this.adminAlerts.add(alert);
    }


    /** Method which creates a user and adds it to the list of users
     * Author: Jinyu Liu
     * @param username username of user
     * @param password password of user
     */
    public User createUser(String username, String password) throws UserNameTakenException {
        System.out.println("Entered:" + username);
        for (User user : listUsers) {
            System.out.println("Iterated on " + user.getUsername());
            if (user.getUsername().equals(username)) {

                throw new UserNameTakenException("That username is taken.");
            }

        }
        User newUser = new User(username);
        newUser.setPassword(password);
        listUsers.add(newUser);
        alertSystem.put(username, new ArrayList<UserAlert>());
        return newUser;
    }

    public void addToWishlist(User user, String itemName){
        user.wishlistItemNames.add(itemName);
    }


    /** Method which creates a trade request and adds it to the list of pending trade requests.
     * Author: Jinyu Liu
     * Slight rework by Louis Scheffer V 6/27/2020
     * @param user1 user1
     * @param user2 user2
     * @param itemIDsSentToUser1 list of IDs of items which will be sent to user1
     * @param itemIDsSentToUser2 list of IDs of items which will be sent to user2
     * @param timeOfTrade time & date of the trade
     * @param meetingPlace location of the trade
     */
    public void sendTradeRequest(User user1, User user2, //user1 is the one sending the request to user2.
                                 ArrayList<Integer> itemIDsSentToUser1, ArrayList<Integer> itemIDsSentToUser2,
                                 LocalDateTime timeOfTrade, String meetingPlace) {
        if (!beforeTrade(user1, user2)){
            return;
        }
        int tradeCapacity = 1;
        ArrayList<Integer> list1;
        if (itemIDsSentToUser1.size() == 0){
            list1 = new ArrayList<Integer>();
        } else {
            list1 = (ArrayList<Integer>) itemIDsSentToUser1.subList(0, tradeCapacity);
        }
        ArrayList<Integer> list2;
        if (itemIDsSentToUser2.size() == 0){
            list2 = new ArrayList<Integer>();
        }else {
            list2 = (ArrayList<Integer>) itemIDsSentToUser2.subList(0, tradeCapacity);
        }

        Trade trade = new Trade(user1.username, user2.username, list1, list2);
        pendingTradeRequests.add(trade);
        trade.setTimeOfTrade(timeOfTrade);
        trade.setMeetingPlace(meetingPlace);
        trade.user1TradeConfirmed = true;

        //Creating and adding an alert for user2
        TradeRequestAlert alert = createTradeRequestAlert(trade, user1);
        alertUser(user2, alert);

    } //Does not remove item from user1 availableItems or user2 availableItems


    /**
     * Creates a TradeRequstAlert for <trade> object sent by <user1>. This method does not add said TradeRequestAlert
     *  to the alertSystem.
     * @param trade the Trade object for the Alert.
     * @param user1 the User who is sending the Trade request.
     * @return A TradeRequestAlert corresponding to <trade>
     */
    public TradeRequestAlert createTradeRequestAlert(Trade trade, User user1){

        String tradeString = tradeToString(trade);

        return new TradeRequestAlert(user1.getUsername(), trade.getTradeID(), tradeString);

    }


    /** Method which allows a user to accept a trade request
     * Author: Jinyu Liu
     * Reworked by Tingyu Liang 7/2/2020
     * @param trade trade object
     * @param user user which is accepting the trade request
     */
    public void acceptTradeRequest(Trade trade, User user) {
        String otherUserName;

        if (trade.getUsername1().equals(user.getUsername())) {
            trade.setUser1AcceptedRequest(true);
            otherUserName = trade.getUsername2();
        }
        else {
            // I am not sure if we have function to check if user is in the trade, commented by Tingyu
            assert trade.getUsername2().equals(user.getUsername());
            trade.setUser2AcceptedRequest(true);
            otherUserName = trade.getUsername1();
        }
        pendingTradeRequests.remove(trade);
        pendingTrades.add(trade);

        String tradeString = tradeToString(trade);
        TradeAcceptedAlert alert = new TradeAcceptedAlert(user.getUsername(), tradeString);
        alertUser(otherUserName, alert);
    }

    /**
     * Decline trade request <trade> sent to User <user>. Also sends a TradeDeclinedAlert to send to the requesting
     * User.
     * @param trade The trade request to be declined.
     * @param decliningUser The User declining the request.
     */
    public void declineTradeRequest(Trade trade, User decliningUser){

        pendingTradeRequests.remove(trade);


        String tradeString = tradeToString(trade);

        TradeDeclinedAlert alert = new TradeDeclinedAlert(tradeString, decliningUser.getUsername());

        String otherUserName;

        if (trade.getUsername1().equals(decliningUser.getUsername())){
            otherUserName = trade.getUsername2();
        }else{
            otherUserName = trade.getUsername1();
        }
        alertUser(otherUserName, alert);
    }

    /** Method which allows a user to counter-offer by changing the details of a trade request
     * Author: Jinyu Liu
     * slight rework by Louis Scheffer V 6/27/2020
     * @param trade trade object
     * @param timeOfTrade time & date of trade
     * @param meetingPlace location of trade
     * @param userEditing user who is editing the trade
     */
    public void editTradeRequest(Trade trade, LocalDateTime timeOfTrade, String meetingPlace, User userEditing) {
        trade.timeOfTrade = timeOfTrade;
        trade.meetingPlace = meetingPlace;
        if (userEditing.username.equals(trade.getUsername1())) {
            trade.user1AcceptedRequest = true;
            trade.user2AcceptedRequest = false;
            trade.incrementUser1NumRequests();
        }
        else if (userEditing.username.equals(trade.getUsername2())) {
            trade.user2AcceptedRequest = true;
            trade.user1AcceptedRequest = false;
            trade.incrementUser2NumRequests();
        }

        TradeRequestAlert alert = createTradeRequestAlert(trade, userEditing);

        String otherUserName;

        if (trade.getUsername1().equals(userEditing.getUsername())){
            otherUserName = trade.getUsername2();
        } else{
            otherUserName = trade.getUsername1();
        }

        alertUser(otherUserName, alert);
    }

    /** 3-arg method which creates and instantiates an ItemvalidationRequest.
     * Author: Jinyu Liu
     * @param name name of the item
     * @param description description of the item
     * @param owner username of the user who will own the item
     */
    public ItemValidationRequestAlert sendValidationRequest(String name, String description, String owner) {
        // reworked by Tingyu since the itemValidationRequestQueue has been moved to UserManager
        ItemValidationRequestAlert alert = new ItemValidationRequestAlert(owner, name, description);
        itemValidationRequestQueue.add(alert);
        return alert;
    }

    /** 2-arg method which creates and instantiates an ItemvalidationRequest.
     * Author: Jinyu Liu
     * @param name name of the item
     * @param owner username of the user who will own the item
     * @return The ItemValidationRequestAlert in question.
     */
    public ItemValidationRequestAlert sendValidationRequest(String name, String owner) {
        ItemValidationRequestAlert alert = new ItemValidationRequestAlert(name, owner);
        itemValidationRequestQueue.add(alert);
        return alert;
    }

    /* No longer neccessary - Louis
    public void AddTransaction(Trade trade){
        transactions.add(trade);
    } // This is for adding completed transactions to the stored list - Mel
     */

    public void requestUnfreeze(String username){
        // user can request to unfreeze account whether it should be unfrozen or not
        // moved by Riya from User
        // unfreezeRequestList.add(username);
    }

    /** Helper function that returns a list of all the trades that user participated in and traded an item. The list is
     * order by the date that the item left the user's possession.
     * @param user User being evaluated
     * @return ArrayList</Trade> (sorted by LocalTimeDate)
     */
    private ArrayList<Trade> getOrderedTrades(User user) {
        ArrayList<Trade> completed = new ArrayList<Trade>();
        ArrayList<TemporaryTrade> incompleted = new ArrayList<TemporaryTrade>();
        ArrayList<Trade> recent = new ArrayList<Trade>();

        for (Trade trade : completedTrades) {
            if (trade.getUsername1().equals(user.getUsername()) & !trade.getItemIDsSentToUser2().isEmpty()) {
                completed.add(trade);
            } else if (trade.getUsername2().equals(user.getUsername()) & !trade.getItemIDsSentToUser1().isEmpty()) {
                completed.add(trade);
            }
        }

        for (TemporaryTrade tTrade : currentTemporaryTrades) {
            if (tTrade.getUsername1().equals(user.getUsername()) & !tTrade.getItemIDsSentToUser2().isEmpty()) {
                incompleted.add(tTrade);
            } else if (tTrade.getUsername2().equals(user.getUsername()) & !tTrade.getItemIDsSentToUser1().isEmpty()) {
                incompleted.add(tTrade);
            }
        }

        while (!completed.isEmpty() | !incompleted.isEmpty()) {
            if (!completed.isEmpty() & incompleted.isEmpty()) {
                recent.addAll(completed);
                completed.clear();
            } else if (completed.isEmpty() & !incompleted.isEmpty()) {
                recent.addAll(incompleted);
                incompleted.clear();
            } else {
                if (completed.get(0).getTimeOfTrade().isBefore(incompleted.get(0).getTimeOfTrade())) {
                    recent.add(completed.get(0));
                    completed.remove(0);
                } else {
                    recent.add(incompleted.get(0));
                    incompleted.remove(0);
                }
            }
        }
    return recent;
    }

    /** Helper function that returns an ordered list of all item's ID that the user traded away. The list is ordered by
     * the date that the user traded the item away.
     * @param tradeHistory Ordered list of all trades that user participated in and traded an item away
     * @param user User being evaluated
     * @return ArrayList</int> (sorted by LocalTimeDate)
     */
    private ArrayList<Integer> getOrderedItemsID(ArrayList<Trade> tradeHistory, User user) {
        ArrayList<Integer> orderedItemsID = new ArrayList<Integer>();
        for (Trade trade : tradeHistory) {
            if (trade.getUsername1().equals(user.getUsername())) {
                orderedItemsID.addAll(trade.getItemIDsSentToUser2());
            } else if (trade.getUsername2().equals(user.getUsername())) {
                orderedItemsID.addAll(trade.getItemIDsSentToUser1());
            }
        }
        return orderedItemsID;
    }

    public ArrayList<Item> getNOrderedItems(User user, ArrayList<Integer> orderedItemsID, int n) {
        ArrayList<Integer> orderedItemsIDClone = (ArrayList<Integer>) orderedItemsID.clone();
        ArrayList<Item> nOrderedItems = new ArrayList<Item>();
        while (nOrderedItems.size() < n & !orderedItemsIDClone.isEmpty()) {
            nOrderedItems.add(searchItem(orderedItemsIDClone.get(orderedItemsIDClone.size() - 1)));
            orderedItemsIDClone.remove(orderedItemsIDClone.size() - 1);
        }
        return nOrderedItems;
    }

    public ArrayList<AdminAlert> getAdminAlerts(){
        return this.adminAlerts;
    }


//    public ArrayList<Item> RecentTransactionItems(User user) {
//        ArrayList<Trade> potentialRecentCompleted = new ArrayList<Trade>();
//        ArrayList<TemporaryTrade> potentialRecentIncompleted = new ArrayList<TemporaryTrade>();
//        ArrayList<Item> recents = new ArrayList<Item>();
//        for (Trade trade : completedTrades) {
//            if (trade.getUsername1().equals(user.username) & !trade.getItemIDsSentToUser2().isEmpty()) {
//                potentialRecentCompleted.add(trade);
//            } else if (trade.getUsername2().equals(user.username) & !trade.getItemIDsSentToUser1().isEmpty()) {
//                potentialRecentCompleted.add(trade);
//            }
//        }
//        for (TemporaryTrade tTrade: currentTemporaryTrades) {
//            if (tTrade.getUsername1().equals(user.username) & !tTrade.getItemIDsSentToUser2().isEmpty()) {
//                potentialRecentIncompleted.add(tTrade);
//            } else if (tTrade.getUsername2().equals(user.username) & !tTrade.getItemIDsSentToUser1().isEmpty()) {
//                potentialRecentIncompleted.add(tTrade);
//            }
//        }
//        while (recents.size() < 3 & (!potentialRecentCompleted.isEmpty() | !potentialRecentIncompleted.isEmpty())) {
//            if (!potentialRecentCompleted.isEmpty() & potentialRecentIncompleted.isEmpty()) {
//                Trade mostRecentComp = potentialRecentCompleted.get(potentialRecentCompleted.size() - 1);
//                if (mostRecentComp.getUsername1().equals(user.username)) {
//                    recents.add(searchItem(mostRecentComp.getItemIDsSentToUser2().get(0)));
//                    potentialRecentCompleted.remove(mostRecentComp);
//                } else if (mostRecentComp.getUsername2().equals(user.username)) {
//                    recents.add(searchItem(mostRecentComp.getItemIDsSentToUser1().get(0)));
//                    potentialRecentCompleted.remove(mostRecentComp);
//                }
//            } else if (!potentialRecentIncompleted.isEmpty() & potentialRecentCompleted.isEmpty()) {
//                Trade mostRecentIncomp = potentialRecentIncompleted.get(potentialRecentCompleted.size() - 1);
//                if (mostRecentIncomp.getUsername1().equals(user.username)) {
//                    recents.add(searchItem(mostRecentIncomp.getItemIDsSentToUser2().get(0)));
//                    potentialRecentIncompleted.remove(mostRecentIncomp);
//                } else if (mostRecentIncomp.getUsername2().equals(user.username)) {
//                    recents.add(searchItem(mostRecentIncomp.getItemIDsSentToUser1().get(0)));
//                    potentialRecentIncompleted.remove(mostRecentIncomp);
//                }
//            } else {
//                Trade mostRecentComp = potentialRecentCompleted.get(potentialRecentCompleted.size() - 1);
//                Trade mostRecentIncomp = potentialRecentIncompleted.get(potentialRecentIncompleted.size() - 1);
//                if (mostRecentComp.getTimeOfTrade().isAfter(mostRecentIncomp.getTimeOfTrade())) {
//                    if (mostRecentComp.getUsername1().equals(user.username)) {
//                        recents.add(searchItem(mostRecentComp.getItemIDsSentToUser2().get(0)));
//                        potentialRecentCompleted.remove(mostRecentComp);
//                    } else if (mostRecentComp.getUsername2().equals(user.username)) {
//                        recents.add(searchItem(mostRecentComp.getItemIDsSentToUser1().get(0)));
//                        potentialRecentCompleted.remove(mostRecentComp);
//                    }
//                } else if (mostRecentIncomp.getTimeOfTrade().isAfter(mostRecentComp.getTimeOfTrade())) {
//                    if (mostRecentIncomp.getUsername1().equals(user.username)) {
//                        recents.add(searchItem(mostRecentIncomp.getItemIDsSentToUser2().get(0)));
//                        potentialRecentIncompleted.remove(mostRecentIncomp);
//                    } else if (mostRecentIncomp.getUsername2().equals(user.username)) {
//                        recents.add(searchItem(mostRecentIncomp.getItemIDsSentToUser1().get(0)));
//                        potentialRecentIncompleted.remove(mostRecentIncomp);
//                    }
//                }
//            }
//        }
//        return recents;
//    }
//As ugly as this is, please don't delete this yet until I am sure the newer code works - Jinyu

    public int getNumTradesThisWeek(User user) {
        int numTransactions = 0;
        LocalDateTime timeNow = LocalDateTime.now(); //gets the current time
        LocalDateTime timeNowBeginning = timeNow.withHour(0).withMinute(0).withSecond(0).withNano(0); //set time 00:00
        LocalDateTime startOfWeek = timeNowBeginning.minusDays(timeNowBeginning.getDayOfWeek().getValue()); //get Sunday
        for (Trade trade : completedTrades) {
            if (trade.getUsername1().equals(user.username) & trade.getTimeOfTrade().isAfter(startOfWeek)) {
                numTransactions++;
            } else if (trade.getUsername2().equals(user.username) & trade.getTimeOfTrade().isAfter(startOfWeek)) {
                numTransactions++;
            }
        }
        for (TemporaryTrade tTrade: currentTemporaryTrades) {
            if (tTrade.getUsername1().equals(user.username) & tTrade.getTimeOfTrade().isAfter(startOfWeek)) {
                numTransactions++;
            } else if (tTrade.getUsername2().equals(user.username) & tTrade.getTimeOfTrade().isAfter(startOfWeek)) {
                numTransactions++;
            }
        }
        return numTransactions;
    }


    private ArrayList<Trade> getIncompleteTrades() {
        ArrayList<Trade> incompleteTrades = new ArrayList<Trade>();
        for (Trade trade : pendingTrades) {
            if (trade.getTimeOfTrade().isAfter(LocalDateTime.now())) {
                incompleteTrades.add(trade);
            }
        }
        return incompleteTrades;
    }

    public int getNumIncompTrades(User user, ArrayList<Trade> incompleteTrades) {
        int count = 0;
        for (Trade trade : incompleteTrades) {
            if (trade.getUsername1().equals(user.getUsername()) | trade.getUsername2().equals(user.getUsername())) {
                count++;
            }
        }
        return count;
    }

    private HashMap<String, Integer> getNumTradesPerUser(User user) {
        HashMap<String, Integer> numTradesPerUser = new HashMap<String, Integer>();
        for (Trade trade: completedTrades) {
            if (trade.getUsername1().equals(user.getUsername())) {
                String partnerUsername = trade.getUsername2();
                numTradesPerUser.putIfAbsent(trade.getUsername2(), 0);
                numTradesPerUser.replace(trade.getUsername2(), numTradesPerUser.get(trade.getUsername2()) + 1);
                } else if (trade.getUsername2().equals(user.getUsername())) {
                String partnerUsername = trade.getUsername1();
                numTradesPerUser.putIfAbsent(trade.getUsername1(), 0);
                numTradesPerUser.replace(trade.getUsername1(), numTradesPerUser.get(trade.getUsername1()) + 1);
            }
        }
        return numTradesPerUser;
    }

    public ArrayList<String> getTopNTradingPartners(User user, int n, HashMap<String, Integer> numTradesPerUser) {
        HashMap<String, Integer> numTradesPerUserClone = (HashMap<String, Integer>) numTradesPerUser.clone();
        ArrayList<String> topPartnersUsername = new ArrayList<String>();
        while (topPartnersUsername.size() < n & !numTradesPerUserClone.isEmpty()) {
            int maxInt = 0;
            StringBuilder favouritePartner = new StringBuilder();
            for (Map.Entry<String, Integer> mapping : numTradesPerUserClone.entrySet()) {
                if (mapping.getValue() > maxInt) {
                    maxInt = (mapping.getValue());
                    StringBuilder string = new StringBuilder();
                    favouritePartner = string.append(mapping.getKey());
                    //Code fragment based off of code in https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
                }
            }
            topPartnersUsername.add(favouritePartner.toString());
            numTradesPerUserClone.remove(favouritePartner.toString());
        }
        return topPartnersUsername;
    }




    /* i don't think this is neccessary anymore - Louis
    public Trade dequeueTradeRequest(){
        return this.tradeRequestQueue.remove(0);
    }
     */

    /** Method which returns a user when given their username
     * Author: Louis Scheffer V
     * @param username username of the user
     * @return user object
     */
    public User searchUser(String username){
        for(User user: listUsers){
            if (user.username.equals(username)){
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
     */
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
     */
    public Item searchItem(int itemID) {
        for (User user : listUsers) {
            for (Item item : user.getAvailableItems()) {
                if (itemID == item.getId()) {
                    return item;
                }
            }
        }
        return null;
    }

    public TemporaryTrade serachTemporaryTrade(int tradeID) {
        for (TemporaryTrade tempTrade : currentTemporaryTrades){
            if (tempTrade.getTradeID() == tradeID){
                return tempTrade;
            }
        }
        return null;
    }

    public Trade searchPendingTradeRequest(int tradeID){
        for (Trade trade : pendingTradeRequests){
            if (trade.getTradeID() == tradeID){
                return trade;
            }
        }
        return null;
    }

    /** Method which ensures that neither user account is frozen.
     * Author: Louis Scheffer V
     * @param u1 user1
     * @param u2 user2
     * @return Boolean
     */
    public Boolean beforeTrade(User u1, User u2){
        return !(u1.getFrozen() || u1.getFrozen());
    }
    /** FYI: variable has been changed to use method checkPermission.
     * Author: Melody Yang
     */

    /** Method which executes all item swaps and checks all pending trades and pending trade requests after a trade has
     * been completed. Trade is moved to completed trades if it is a
     * Author: Louis Scheffer V
     * @param trade trade object
      */
    public void afterTrade(Trade trade){
        exchangeItems(trade);
        checkPendingTradeRequests();
        checkPendingTrades();
        pendingTrades.remove(trade);
        if (trade instanceof TemporaryTrade){
            currentTemporaryTrades.add((TemporaryTrade) trade);

        }
        else{
            completedTrades.add(trade);
        }

        User user1 = searchUser(trade.getUsername1());
        User user2 = searchUser(trade.getUsername2());

        assert user1 != null;
        //This might be > instead of >= idk lol
        if (user1.getNumBorrowed() + borrowLendThreshold >= user1.getNumLent()){
            FreezeUserAlert alert = new FreezeUserAlert(user1.getUsername(),user1.getNumBorrowed(),
                    user1.getNumLent(), borrowLendThreshold);
            adminAlerts.add(alert);
        }
        assert user2 != null;
        if (user2.getNumBorrowed() + borrowLendThreshold >= user2.getNumLent()){
            FreezeUserAlert alert = new FreezeUserAlert(user2.getUsername(), user2.getNumBorrowed(),
                    user2.getNumLent(), borrowLendThreshold);
            adminAlerts.add(alert);
        }
    }

    /** Method which exchanges the items in the trade system after a trade has been marked as completed
     * Author: Louis Scheffer V
     * @param trade trade object
     */
    public void exchangeItems(Trade trade){
        User user1 = searchUser(trade.getUsername1());
        User user2 = searchUser(trade.getUsername2());
        for(int itemID : trade.getItemIDsSentToUser1()){
            Item item = searchItem(user2, itemID);
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user1.increaseStat("borrowed", 1);
            user2.increaseStat("lent", 1);
            user2.removeItemFromList(item, user2.availableItems);
            if (trade instanceof TemporaryTrade){
                user1.addItemToList(item, user1.borrowedItems);
            }
            else {
                user1.addItemToList(item, user1.availableItems);
            }
        }
        for(int itemID : trade.getItemIDsSentToUser2()){
            Item item = searchItem(user1, itemID);
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user2.increaseStat("borrowed", 1);
            user1.increaseStat("lent", 1);
            user1.removeItemFromList(item, user1.availableItems);
            if (trade instanceof TemporaryTrade){
                user2.addItemToList(item, user2.borrowedItems);
            }
            else{
                user2.addItemToList(item, user2.availableItems);
            }

        }
    }
    /**
     * Check to see if any TemporaryTrades have expired and if so, add an alert to the User's alertQueue.
     * Author: Murray Smith
     * Rework by Louis Scheffer V 6/30/20 // modifications made to work with the alert system.
     * Rework by Tian Yue Dong 7/1/2020 ; made it so it only sends alert to user who didnt confirm the reexchange
     */
    public void checkForExpiredTempTrades(){
        for (TemporaryTrade tempTrade : currentTemporaryTrades) {
            if (LocalDateTime.now().isAfter(tempTrade.getDueDate())) {
                String tradeString = tradeToString(tempTrade);
                LocalDateTime dueDate = tempTrade.getDueDate();
                int tradeID = tempTrade.getTradeID();

                if (!tempTrade.getUser1ItemReturnRequestAccepted()){
                    UserAlert alert = new ExpirationAlert(dueDate, tradeString, tempTrade.getUsername1(), tradeID);
                    alertUser(tempTrade.getUsername1(), alert);
                }
                if (!tempTrade.getUser2ItemReturnRequestAccepted()){
                    UserAlert alert = new ExpirationAlert(dueDate, tradeString, tempTrade.getUsername2(), tradeID);
                    alertUser(tempTrade.getUsername2(), alert);
                }
                
            }
        }
    }



    /** Method which allows a user to confirm the re-exchange of items has occured in the real world. If the other
     * user has already confirmed, then the reExchangeItems method will be called to reExahange the items within the
     * trade system.
     * Author: Louis Scheffer V
     * @param user user who is confirming the re-exchange of items.
     * @param temporaryTrade the temporary trade object.
     */
    public void confirmReExchange(User user, TemporaryTrade temporaryTrade){
        if(user.getUsername().equals(temporaryTrade.getUsername1())){
            temporaryTrade.setUser1ItemReturnRequestAccepted(true);
        }
        else if (user.getUsername().equals(temporaryTrade.getUsername2())){
            temporaryTrade.setUser2ItemReturnRequestAccepted(true);
        }
        if (temporaryTrade.getUser1ItemReturnRequestAccepted() && temporaryTrade.getUser2ItemReturnRequestAccepted()){
            reExchangeItems(temporaryTrade);
        }
    }

    public void confirmTrade(User user, Trade trade){
        // TODO
    }

    /** Method which returns items to their owners after the expiration of a temporary trade
     * Author: Louis Scheffer V
     * @param trade Temporary Trade Object
     */
    public void reExchangeItems(TemporaryTrade trade){
            User user1 = searchUser(trade.getUsername1());
            User user2 = searchUser(trade.getUsername2());
            for(int itemID : trade.getItemIDsSentToUser1()) {
                Item item = searchItem(user2, itemID);
                user1.removeItemFromList(item, user1.borrowedItems);
                user2.addItemToList(item, user2.availableItems);
            }
            for(int itemID : trade.getItemIDsSentToUser2()) {
                Item item = searchItem(user1, itemID);
                user2.removeItemFromList(item, user2.borrowedItems);
                user2.addItemToList(item, user2.availableItems);
            }
    }


    /** Method which checks all pending trades to see if the items are still available.If they are not then the trade
     * request is deleted.
     * Author: Louis Scheffer V
     */

    public void checkPendingTrades(){
        for(Trade trade: pendingTrades){
            User user1 = searchUser(trade.getUsername1());
            User user2 = searchUser(trade.getUsername2());
            for(int itemID : trade.getItemIDsSentToUser1()){
                Item item = searchItem(user2, itemID);
                if (item == null){
                    pendingTrades.remove(trade);
                    String tradeString = tradeToString(trade);
                    UserAlert alert = new TradeCancelledAlert(tradeString);
                    alertUser(user1, alert);
                    alertUser(user2, alert);
                }
            }
            for(int itemID : trade.getItemIDsSentToUser2()) {
                Item item = searchItem(user1, itemID);
                if (item == null) {
                    pendingTrades.remove(trade);
                    String tradeString = tradeToString(trade);
                    UserAlert alert = new TradeCancelledAlert(tradeString);
                    alertUser(user1, alert);
                    alertUser(user2, alert);
                }
            }
        }
    }
    /** Method which checks all pending trades to see if the items are still available. If they are not then the trade
     * request is deleted.
     * Author: Louis Scheffer V
     */
    public void checkPendingTradeRequests(){
        for(Trade trade: pendingTradeRequests){
            User user1 = searchUser(trade.getUsername1());
            User user2 = searchUser(trade.getUsername2());
            for(int itemID : trade.getItemIDsSentToUser1()){
                Item item = searchItem(user2, itemID);
                if (item == null){
                    pendingTradeRequests.remove(trade);
                    String tradeString = tradeToString(trade);
                    UserAlert alert = new TradeRequestCancelledAlert(tradeString);
                    alertUser(user1, alert);
                    alertUser(user2, alert);
                }
            }
            for(int itemID : trade.getItemIDsSentToUser2()) {
                Item item = searchItem(user1, itemID);
                if (item == null) {
                    pendingTradeRequests.remove(trade);
                    String tradeString = tradeToString(trade);
                    UserAlert alert = new TradeRequestCancelledAlert(tradeString);
                    alertUser(user1, alert);
                    alertUser(user2, alert);
                }
            }
        }
    }

    /**
     *
     * @param trade a trade object
     * @return a string which describes the two users involved in the trade and the Time & date of the trade.
     */
    public String tradeToString(Trade trade){
        return "User 1: " + trade.getUsername1() + "\nUser 2: " + trade.getUsername2() +
                "\nItems being traded from user 1 to user 2: " + GetItemNamesFromUser1ToUser2(trade) +
                "\nItems being traded from user 2 to user 1: " + GetItemNamesFromUser1ToUser2(trade) +
                "\nTime & Date of item exchange: " + trade.getTimeOfTrade().toString() +
                "\nLocation of Trade: " + trade.getMeetingPlace() + "\nTradeID: " + trade.getTradeID();
    }
    // helper method which lists the names of the items going from user 1 to user 2 - Louis
    private String GetItemNamesFromUser1ToUser2(Trade trade){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser2()){
            Item item = searchItem(searchUser(trade.getUsername1()), itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }
    // helper method which lists the names of the items going from user 2 to user 1 - Louis
    private String GetItemNamesFromUser2ToUser1(Trade trade){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser1()){
            Item item = searchItem(searchUser(trade.getUsername2()), itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }

    /** Overloaded method to send an alert to a user. This one uses a user object.
     * Author: Louis Scheffer V
     * @param user object of the user who will be receiving the alert
     * @param alert alert object to send to the user
     */
    public void alertUser(User user, UserAlert alert){
        String username = user.getUsername();
        alertUser(username, alert);
    }

    /** Overloaded method to send an alert to a user. This one uses a username.
     * Author: Louis Scheffer V
     * @param username username of the user receiving the alert
     * @param alert alert object to send to the user
     */
    public void alertUser(String username, UserAlert alert){
        ArrayList<UserAlert> alerts = alertSystem.get(username);
        alerts.add(alert);
        alertSystem.put(username, alerts);
    }

    /** Method which allows a user to send a message to another user, using the alert system.
     * Author: Louis Scheffer V
     * @param sender user object who is sending the message.
     * @param recipient user object who is receiving the message.
     * @param message message text.
     */
    public void sendMessageToUser(User sender, User recipient, String message){
        UserAlert alert = new MessageAlert(sender.getUsername(), message);
        alertUser(recipient, alert);
    }
    public ArrayList<Trade> searchPendingTradesByUser(User user){
        ArrayList<Trade> userTrades = new ArrayList<Trade>();
        for(Trade trade : pendingTrades){
            if (trade.getUsername1().equals(user.getUsername()) || trade.getUsername2().equals(user.getUsername())){
                userTrades.add(trade);
            }
        }
        return userTrades;
    }
    public ArrayList<TemporaryTrade> searchActiveTempTradesByUser(User user) {
        ArrayList<TemporaryTrade> userTrades = new ArrayList<TemporaryTrade>();
        for (TemporaryTrade trade: currentTemporaryTrades) {
            if (trade.getUsername1().equals(user.getUsername()) || trade.getUsername2().equals(user.getUsername())) {
                userTrades.add(trade);
            }
        }
        return userTrades;
    }

    public int getBorrowLendThreshold() {
        return borrowLendThreshold;
    }

    public void setBorrowLendThreshold(int borrowLendThreshold) {
        this.borrowLendThreshold = borrowLendThreshold;
    }

    public int getCompleteThreshold() {
        return completeThreshold;
    }

    public void setCompleteThreshold(int completeThreshold) {
        this.completeThreshold = completeThreshold;
    }

    public int getIncompleteThreshold() {
        return incompleteThreshold;
    }

    public void setIncompleteThreshold(int incompleteThreshold) {
        this.incompleteThreshold = incompleteThreshold;
    }
}

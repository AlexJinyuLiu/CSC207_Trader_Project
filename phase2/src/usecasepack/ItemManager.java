package usecasepack;

import entitypack.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which handles all of the items that are within the trading system.
 */
public class ItemManager implements Serializable {

    private ArrayList<Item> items = new ArrayList<Item>();

    private ArrayList<ItemValidationRequest> validationRequests = new ArrayList<ItemValidationRequest>();

    private int itemIDGenerator = 0;

    /**
     * Creates a new item with specified name, ID, username of owner, and description.
     * @param itemName the name of the item
     * @param itemID the ID of the item.
     * @param usernameOfOwner the username of the item's owner.
     * @param description a short description of the item.
     */
    public void createItem(String itemName, int itemID, String usernameOfOwner, String description) {
        Item newItem = new Item(itemName, description, itemID, usernameOfOwner);
        items.add(newItem);
    }
    //TODO: delete the function below after debug
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Creates and adds a new item validation request to the validationRequests arraylist.
     * @param usernameOfOwner the username of the user sending the validationrequest.
     * @param itemName the name of the item to be validated.
     * @param description a short description of the item.
     */
    public void createItemValidationRequest(String usernameOfOwner, String itemName, String description){

        ItemValidationRequest validationRequest = new ItemValidationRequest(usernameOfOwner, itemName, description);
        validationRequests.add(validationRequest);
    }

    /**
     * Return and pop a single itemValidationRequest from the list of validation requests.
     * @return the item validation request at the end of the list, or null if there are none left.
     */
    public ItemValidationRequest pollValidationRequest() {
        if (validationRequests.size() > 0) {
            return validationRequests.remove(validationRequests.size() - 1);
        } else{
            return null;
        }
    }


    /**
     *
     * @return A new ID for an item.
     */
    public int getNewItemID() {
        int newID = itemIDGenerator;
        itemIDGenerator++;
        return newID;
    }

    /**
     * Get all available items for the user with specified username.
     * @param tradingUserUsername the username of the user from which to fetch the items
     * @return an arraylist of all items this user has available for trade
     */
    public ArrayList<Item> getAvailableItems(String tradingUserUsername){
        ArrayList<Item> availableItems = new ArrayList<Item>();
        for (Item item : items){
            if (item.getOwner().equals(tradingUserUsername) && item.getOwner().equals(item.getInPossessionOf())){
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    /** Method which returns a item (that is in a user's available items) when given its ID number.
     * Returns null if an invalid ID is given
     * Author: Louis Scheffer V
     * @param itemID ID number of the item
     * @return item
     */
    public Item searchItem(int itemID) {
        for (Item item: items){
            if (itemID == item.getId()){
                return item;
            }
        }
        return null;
    }

    /** Method which exchanges the items in the trade system after a trade has been marked as completed
     * Author: Louis Scheffer V
     * @param trade trade object
     */
    public void exchangeItems(Trade trade, TradingUser user1, TradingUser user2){
        //TODO: Find a better solution than casting like this.
        for(int itemID : trade.getItemIDsSentToUser1()){

            Item item = searchItem(itemID);
            for (String string: user1.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    user1.removeItemFromWishList(string);
                }
            }
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user1.increaseNumBorrowed(1);
            user2.increaseNumLent(1);

            if (trade instanceof TemporaryTrade){
                item.setInPossessionOf(user1.getUsername());
            }
            else {
                item.setOwner(user1.getUsername());
            }
        }
        for(int itemID : trade.getItemIDsSentToUser2()){
            Item item = searchItem(itemID);
            for (String string: user2.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    user2.removeItemFromWishList(string);
                }
            }
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user2.increaseNumBorrowed(1);
            user1.increaseNumLent(1);
            if (trade instanceof TemporaryTrade){
                item.setInPossessionOf(user2.getUsername());
            }
            else{
                item.setOwner(user2.getUsername());
            }

        }
    }

    /** Method which returns items to their owners after the expiration of a temporary trade
     * Author: Louis Scheffer V
     * @param trade Temporary EntityPack.Trade Object
     */
    public void reExchangeItems(TemporaryTrade trade){
        for(int itemID : trade.getItemIDsSentToUser1()) {
            Item item = searchItem(itemID);
            item.setInPossessionOf(null);

        }
        for(int itemID : trade.getItemIDsSentToUser2()) {
            Item item = searchItem(itemID);
            item.setInPossessionOf(null);
        }
    }

    /**
     * Removes the item with itemID from the trading system.
     * @param itemID the ID of the item requested to be removed
     */
    public void removeFromInventory(int itemID) {
        Item item = searchItem(itemID);
        assert item != null;
        items.remove(item);
    }

    /**
     * @param user the trading user that the items in their inventory are being inspected
     * @param itemID the ID of the item that is being searched for
     * @return true iff user contains itemName in their inventory
     */
    public boolean checkIfUserHas(TradingUser user, int itemID) {
        Item item = searchItem(itemID);
        if (item == null) {
            return false;
        }
        else {
            //Dunno if borrowed items will be shown in user's inventory - Tingyu
            return item.getInPossessionOf().equals(user.getUsername());
        }
    }

}

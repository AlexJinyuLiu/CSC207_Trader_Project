package usecasepack;

import entitypack.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemManager implements Serializable {
    /**
     * Class which handles all of the items that are within the trading system.
     */
    public ArrayList<Item> items = new ArrayList<Item>();



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
     * @param username the username of the user whose inventory is being searched for
     * @return the list of available items of the user
     */
    public ArrayList<Item> getAvailableItems(String username) {
        ArrayList<Item> usersItems = new ArrayList<Item>();
        for (Item item: items) {
            if (item.getName().equals(username)) {
                usersItems.add(item);
            }
        }
        return usersItems;
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
            return item.getInPossessionOf().equals(user.getUsername());
        }
    }

}

package usecasepack;

import alertpack.*;
import entitypack.Item;
import entitypack.TradingUser;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SuggestMatch{
    private Item item1; // item1 is what user is adding to wishlist
    private TradingUser user2; // takes care of checking ifthey're frozen? check that's implemented
    private int item1ID;
    private Item item2;
    private ArrayList<String> wishlistItems;
    public void setItem1(Item item){
        item1 = item;
        item1ID = item.getId();
    }
    public void getUser2(TradingUser user2){
       // item1.getOwner()
    }
    public void checkUser1(TradingUser user1){
        user1.getUsername();
        wishlistItems = user1.getWishlistItemNames();
        // for item in wishlist, write helper method checkMatch to see if there's a match with user2
        for (int i = 0; i <= wishlistItems.size() - 1; i++){
            // get user2 associated with wishlist item
            // checkMatch for user2
        }
    }
    public void checkMatch(TradingUser user1, TradingUser user2){

    }
    //check same metro area

}

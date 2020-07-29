package usecasepack;

import entitypack.Item;
import entitypack.TradingUser;
import entitypack.User;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SuggestMatch {
    private Item item1; // item1 is what user is adding to wishlist
    private TradingUser user2; // takes care of checking ifthey're frozen? check that's implemented
    private int item1ID;
    private Item item2;
    // ^bad
    //TODO: WHAT IS THE PURPOSE OF THIS CLASS? this can all be done in a helper method in TradingUserActions, also dont store item and user objects in this

    public SuggestMatch(String user1name, String user2nam){

    }

    public TradingUser getUser2(UserManager userManager) {
        for (int i = 0; i < userManager.getListUsers().size(); i++)
            if (userManager.getListUsers().get(i).toString().equals(item1.getOwner())) {
                user2 = (TradingUser) userManager.getListUsers().get(i); //needed to cast, not wrap
            }
        return user2;
    }

    public Item getItem2Match(TradingUser user1, TradingUser user2, ItemManager itemManager){
        for (int i = 0; i < user2.getWishlistItemNames().size(); i++){
            for (int n =0; n < itemManager.getAvailableItems(user1.getUsername()).size(); n++) {
                if (user2.getWishlistItemNames().get(i).equals(itemManager.getAvailableItems(user1.getUsername()).get(n))){
                    item2 = itemManager.getAvailableItems(user1.getUsername()).get(n);
                }
            }

        }
        return item2;
    }

    public void checkUserWishlist(TradingUser user1, TradingUser user2, Item wishlistItem) {
        ArrayList<String> user1WishlistItems = user1.getWishlistItemNames();
        // for item in wishlist, write helper method checkMatch to see if there's a match with user2
        for (int i = 0; i < user1WishlistItems.size(); i++) {
            // get user2 associated with wishlist item
            // checkMatch for user2
            if (wishlistItem.getName().equals(user1WishlistItems.get(i))) {
                // checkMatch(user1, user2);


            }
        }


    }

    private void checkMatch(TradingUser user1, TradingUser user2, Item wishlistItem) {
        if (wishlistItem.getInPossessionOf().equals(user2.getUsername())) {
            ArrayList<String> user2WishlistItems = user2.getWishlistItemNames();

        }
    }
}

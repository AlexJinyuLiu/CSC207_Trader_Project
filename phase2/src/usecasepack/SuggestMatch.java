package usecasepack;

import entitypack.Item;
import entitypack.TradingUser;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SuggestMatch {
    private Item item1; // item1 is what user is adding to wishlist
    private TradingUser user2; // takes care of checking ifthey're frozen? check that's implemented
    private int item1ID;
    private Item item2;

    public void setItem1(Item item) {
        item1 = item;
        item1ID = item.getId();
    }

    public void getUser2(TradingUser user2) {
        // item1.getOwner()
    }

    public void checkUserWishlist(TradingUser user1, TradingUser user2, Item wishlistItem) {
        ArrayList<String> user1WishlistItems = user1.getWishlistItemNames();
        // for item in wishlist, write helper method checkMatch to see if there's a match with user2
        for (int i = 0; i < user1WishlistItems.size(); i++) {
            // get user2 associated with wishlist item
            // checkMatch for user2
            if (wishlistItem.getName().equals(user1WishlistItems.get(i))) {
                // checkMatch(user1, user2);
                checkMatch(user1, user2, user1WishlistItems.get(i));

            }
        }


    }

    private void checkMatch(TradingUser user1, TradingUser user2, Item wishlistItem) {
        if (wishlistItem.getInPossessionOf().equals(user2.getUsername())) {
            ArrayList<String> user2WishlistItems = user2.getWishlistItemNames();

        }
    }
}

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
    public void setItem1(Item item){
        item1 = item;
        item1ID = item.getId();
    }
    public void getUser2(){

       // item1.getOwner()
    }

    //check same metro area

}

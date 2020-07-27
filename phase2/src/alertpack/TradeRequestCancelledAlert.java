package alertpack;

import controllerpresenterpack.MenuPresenter;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.Scanner;

/**
 * A user alert that alerts a user when their trade request has been cancelled because one of the items in that request
 * has been traded away to annother user.
 */
public class TradeRequestCancelledAlert extends UserAlert implements Serializable {
    //author: Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    protected int tradeID;

    /**
     * Constructs a new TradeRequestCancelledAlert, storing a unique TradeID
     * @param tradeID the unique ID of the trade that was cancelled.
     */
    public TradeRequestCancelledAlert(int tradeID){
        super(9);
        this.tradeID = tradeID;
    }

    /** Handles the TradeRequestCancelledAlert by prompting the user that their trade has been cancelled due to one or
     *  more items being no longer available.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager) {
        MenuPresenter menuPresenter = (MenuPresenter)menuPresenterObject;
        // "The following trade request has been cancelled as one of the users is no " +
        //         "longer in possession of an item in the proposed trade. EntityPack.Trade ID: " + a.getTradeID()
        menuPresenter.printMenu(28, 1);
        menuPresenter.printTradeToString(itemManager, tradeCreator.searchPendingTradeRequest(getTradeID()));
        boolean handled = false;

        int input = 0;

        while (!handled) {
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(28, 2);
            input = scan.nextInt();
            if (input == 1) {handled = true;}
        }
    }

    /**
     *
     * @return the ID of the trade request cancelled
     */
    public int getTradeID() {
        return tradeID;
    }

}

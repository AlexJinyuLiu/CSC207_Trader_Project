package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import controllerpresenterpack.MenuPresenter;
import entitypack.Frame;
import entitypack.Trade;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.Scanner;

public class TradeAcceptedAlert extends UserAlert implements Serializable, DismissableAlert {
    /** An alert which tells a user that a trade they have proposed has been accepted.
     *
     */

    private String acceptingUsername;
    private int tradeID;

    /** Called when a trade request has been accepted.
     *
     * @param acceptingUsername The username of the user which has accepted the trade.
     * @param tradeID The ID number of the trade.
     */
    public TradeAcceptedAlert(String acceptingUsername, int tradeID) {
        super(4);
        this.acceptingUsername = acceptingUsername;
        this.tradeID = tradeID;
    }

    /** Handles the TradeAcceptedAlert by informing the user that their pending trade request has been accepted.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        //a.getAcceptingUsername() +
        //        " has accepted the following trade request: \n" + tradeToString(userManager,
        Trade b = tradeCreator.searchTrades(getTradeID());
        /**menuPresenter.printMenu(26, 1);
        menuPresenter.printTradeToString(itemManager, b);

        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(26, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }

        String desc = menuPresenter.getText(Frame.TRADEACCEPTEDALERT, 1) +
                menuPresenter.printTradeToString(itemManager, b);

        DismissibleAlert tradeAcceptedAlert = new DismissibleAlert(desc, menuPresenter);**/

    }

    /**
     *
     * @return the username of the user who accepts the trade
     */
    public String getAcceptingUsername(){
        return this.acceptingUsername;
    }

    /**
     *
     * @return the ID of the trade accepted
     */
    public int getTradeID(){
        return tradeID;
    }

}
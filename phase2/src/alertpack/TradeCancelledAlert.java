package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

public class TradeCancelledAlert extends UserAlert implements Serializable {
    /** Alert which tells a user that a pending trade in which they were involved has been cancelled as one of the users
     * is no longer in possession of one of the items that was to be traded.
     */
    //author: group 0110 for CSC207H1 summer 2020 project
    protected int tradeID;

    /** Called when a pending trade is cancelled due to item unavailability.
     *
     * @param tradeID the id number of the trade.
     */
    public TradeCancelledAlert(int tradeID){
        super(5);
        System.out.println("EntityPack.Trade " + tradeID + " has been cancelled. Generating alerts...");
        this.tradeID = tradeID;
    }

    /** Handles the TradeCancelledAlert by prompting the user that their trade has been cancelled due to one or more
     * items being no longer available.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager) {
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        // "The following pending trade has been cancelled as one of the users is no longer in possession of " +
        //         "a item in the proposed trade. EntityPack.Trade ID: " + a.getTradeID()
        /**menuPresenter.printMenu(28, 1);
        menuPresenter.printTradeToString(itemManager, tradeCreator.searchTrades(getTradeID()));
        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(28, 2);
            input = scan.nextInt();
            if (input == 1) {handled = true;}
        }**/

        String desc = menuPresenter.getText(Frame.TRADECANCELLEDALERT, 1) +
                menuPresenter.printTradeToString(itemManager, tradeCreator.searchTrades(getTradeID()));

        DismissibleAlertPrompt dismissibleAlert = new DismissibleAlertPrompt(desc, menuPresenter);
    }

    /**
     *
     * @return the ID of the trade cancelled (item no longer available to be traded)
     */
    public int getTradeID() {
        return tradeID;
    }

}

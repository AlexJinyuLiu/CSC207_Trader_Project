package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

/**
 * An user alert sent to a user when a trade request they have sent has been declined by the recieving user.
 */
public class TradeDeclinedAlert extends UserAlert implements Serializable{//}, DismissableAlert {
    private String decliningUserName;
    private int tradeID;

    /**
     * Constructs a TradeDeclinedAlert, storing data about the declining user and the trade.
     * @param decliningUserName the username of the user that declined the trade request.
     * @param tradeID the unique ID of the trade that was declined.
     */
    public TradeDeclinedAlert(String decliningUserName, int tradeID){
        super(6);
        this.decliningUserName = decliningUserName;
        this.tradeID = tradeID;
    }

    /** Handles the TradeDeclinedAlert by informing the user receiving it that their pending trade request has been
     * declined.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        // a.getDecliningUserName() + " has declined your trade request with ID " + a.getTradeID())
        /**menuPresenter.printMenu(27, 1);
        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(27, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }**/

        String desc = menuPresenter.getText(Frame.TRADEDECLINEDALERT, 1);

        DismissibleAlertPrompt tradeDeclinedAlertPrompt = new DismissibleAlertPrompt(desc, menuPresenter);

    }

    /**
     *
     * @return the username of the user who declines the trade
     */
    public String getDecliningUserName(){
        return this.decliningUserName;
    }

    /**
     *
     * @return the ID of the trade declined by a user
     */
    public int getTradeID(){
        return this.tradeID;
    }
}

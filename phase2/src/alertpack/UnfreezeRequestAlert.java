package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

/**
 * An admin alert that is sent to an admin when a user requests to unfreeze their account.
 */
public class UnfreezeRequestAlert extends AdminAlert implements Serializable {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private int lent; // amount user has lent
    private int borrowed; // amount user has borrowed
    private int thresholdRequired; // difference needed between lent and borrowed
    private int incompleteT;
    private int incompThreshold;

    /**
     * Initializes a new unfreezeRequestAlert, storing the username of the user in question, the number of items they've
     * lent and borrowed, the threshold of items lent/items borrowed required, their number of incomplete trades, and
     * the threshold of incomplete trades they must remain under.
     * @param username the username of the user
     * @param lent the number of items that user has lent
     * @param borrowed the number of items that user has borrowed
     * @param thresholdRequired the threshold of items lent/borrowed required.
     * @param incompleteTrades the number of incomplete trades this user has.
     * @param incompleteThresholdRequired the threshold of incomplete trades they must stay under.
     */
    public UnfreezeRequestAlert(String username, int lent, int borrowed, int thresholdRequired, int incompleteTrades,
                                int incompleteThresholdRequired){
        super(3);
        this.username = username;
        this.lent = lent;
        this.borrowed = borrowed;
        this.thresholdRequired = thresholdRequired;
        this.incompleteT = incompleteTrades;
        this.incompThreshold = incompleteThresholdRequired;
    }


    /** Method that handles a UnfreezeUserRequestAlert by unfreezing the user that requested the unfreeze of dismissing
     * the alert
     *
     * @param menuPresenterObject menu presenter for the output statements
     * @param userManager user manager to find the user's username
     * @param adminUser admin user to unfreeze account if needed
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                        TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        /**menuPresenter.printMenu(14,0);
        // "Unfreeze EntityPack.User Request Alert" +
        //         "\n" + alert.getUsername() + " has lent: " + alert.getLent() + " items" +
        //         "\n" + alert.getUsername() + " has borrowed: " + alert.getBorrowed() + " items" +
        //         "\n" + "Required to lend " + alert.getThresholdRequired() + " more items than borrowed"
        menuPresenter.printMenu(13, 3, getUsername());
        menuPresenter.printMenu(13, 4, getLent());
        menuPresenter.printMenu(13, 5, getBorrowed());
        menuPresenter.printMenu(13, 6, getThresholdRequired());
        menuPresenter.printMenu(13, 7, getIncompleteT());
        menuPresenter.printMenu(13, 8, getIncompThreshold());
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "(1) Unfreeze EntityPack.User"
            menuPresenter.printMenu(14,1);
            // "(2) Dismiss"
            menuPresenter.printMenu(14,2);
            input = scan.nextInt();
            if (input == 1) {
                TradingUser user = (TradingUser)userManager.searchUser(getUsername());
                adminUser.unfreezeAccount(user);
                flag = false;
            }
            if (input == 2) flag = false;
        }**/

        String desc =
        // "Unfreeze EntityPack.User Request Alert" +
        //         "\n" + alert.getUsername() + " has lent: " + alert.getLent() + " items" +
        //         "\n" + alert.getUsername() + " has borrowed: " + alert.getBorrowed() + " items" +
        //         "\n" + "Required to lend " + alert.getThresholdRequired() + " more items than borrowed"
                menuPresenter.getText(Frame.FREEZEUSERALERT, 3, getUsername()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 4, getLent()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 5, getBorrowed()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 6, getThresholdRequired()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 7, getIncompleteT()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 8, getIncompThreshold());

        AcceptableAlertPrompt unfreezeRequestAlertPrompt = new AcceptableAlertPrompt(getType(), desc, getUsername(),
                menuPresenter, userManager, adminUser, tradeCreator);
    }
    /**
     *
     * @return the username of the user who sends the unfreeze request
     */
    public String getUsername(){ return username; }

    /**
     *
     * @return the number of the items lent by the user
     */
    public int getLent(){ return lent; }

    /**
     *
     * @return the number of items borrowed by the user
     */
    public int getBorrowed(){ return borrowed; }

    /**
     *
     * @return the number of items that a user is required to lend before borrowing
     */
    public int getThresholdRequired(){
        return this.thresholdRequired;
    }

    /**
     *
     * @return the number of incomplete trades that this user has made
     */
    public int getIncompleteT() {return this.incompleteT;}

    /**
     *
     * @return the allowed number of incomplete trades that a user can make
     */
    public int getIncompThreshold() {return this.incompThreshold;}
}

package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

public class FreezeUserAlert extends AdminAlert implements Serializable {
    /** Alert which is generated when a EntityPack.User crosses the threshold of borrowed minus lent that they are required to
     * maintain. The admin will then decide to freeze the user or leave the user unfrozen.
     */

    //group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private int lent; // amount user has lent
    private int borrowed; // amount user has borrowed
    private int thresholdRequired; // difference needed between lent and borrowed

    /** Constructor for an alert that tells the admin that the system has identified a user to freeze.
     *
     * @param username username of the user to be frozen.
     * @param lent the number of items the user has borrowed.
     * @param borrowed the number items the user has borrowed.
     * @param thresholdRequired the threshold the user is required to maintain.
     */
    public FreezeUserAlert(String username, int lent, int borrowed, int thresholdRequired){
        super(2);
        this.username = username;
        this.lent = lent;
        this.borrowed = borrowed;
        this.thresholdRequired = thresholdRequired;
    }

    /** Method that handles a FreezeUserAlert by freezing the user or dismissing the alert
     *
     * @param menuPresenterObject menu presenter for the output statements
     * @param adminUser for freezing the user
     * @param userManager for searching the username of the user to be frozen
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        /**menuPresenter.printMenu(13,0); // Freeze EntityPack.User Alert
        // "Freeze EntityPack.User Alert" +
        //         "\n" + alert.getUsername() + " has lent: " + alert.getLent() + " items" +
        //         "\n" + alert.getUsername() + " has borrowed: " + alert.getBorrowed() + " items" +
        //         "\n" + "Required to lend " + alert.getThresholdRequired() + " more items than borrowed"
        menuPresenter.printMenu(13, 3, getUsername());
        menuPresenter.printMenu(13, 4, getLent());
        menuPresenter.printMenu(13, 5, getBorrowed());
        menuPresenter.printMenu(13, 6, getThresholdRequired());
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "(1) Freeze EntityPack.User"
            menuPresenter.printMenu(13,1);
            // "(2) Dismiss"
            menuPresenter.printMenu(13,2);
            input = scan.nextInt();
            if (input == 1) {
                TradingUser user = (TradingUser)userManager.searchUser(getUsername());
                assert user != null;
                adminUser.freezeUser(user);
                int numBorrowed = user.getNumBorrowed();
                int numLent = user.getNumLent();
                int incompleteT = user.getNumIncompleteTrades();
                FrozenAlert frozenAlert = new FrozenAlert(numBorrowed, numLent, tradeCreator.getBorrowLendThreshold(),
                        incompleteT, userManager.getIncompleteThreshold());
                userManager.alertUser(user.getUsername(), frozenAlert);
                flag = false;
            }
            if (input == 2) flag = false;
        }**/
        String desc = menuPresenter.getText(Frame.FREEZEUSERALERT, 3, getUsername()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 4, getLent()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 5, getBorrowed()) +
                menuPresenter.getText(Frame.FREEZEUSERALERT, 6, getThresholdRequired());

        AcceptableAlertPrompt freezeUserAlertPrompt = new AcceptableAlertPrompt(getType(), desc, getUsername(),
                menuPresenter, userManager, adminUser, tradeCreator);
    }


    /**
     *
     * @return returns the username of the user who should be frozen.
     */
    public String getUsername(){ return username; }

    /**
     *
     * @return the number of items the user has lent.
     */
    public int getLent(){ return lent; }

    /**
     *
     * @return the number of items the user has borrowed.
     */
    public int getBorrowed(){ return borrowed; }

    /**
     *
     * @param username the username of the user who should be frozen.
     */
    public void setUsername(String username){ this.username = username; }

    /**
     *
     * @param lent the number of items the user has lent.
     */
    public void setLent(int lent){ this.lent = lent; }

    /**
     *
     * @param borrowed the number of items the user has borrowed.
     */
    public void setBorrowed(int borrowed){ this.borrowed = borrowed; }

    /**
     *
     * @return the threshold of lent - borrowed that the user should be above.
     */
    public int getThresholdRequired(){
        return this.thresholdRequired;
    }


}

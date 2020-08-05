package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

public class FrozenAlert extends UserAlert implements Serializable {
    /** Alert which tells a user that they have been frozen by an admin. Displays the number of items borrowed and lent.
     * Also displays the number on incomplete trades and the thresholds of incomplete trades and lent minus borrowed
     * the user is required to maintain.
     */

    private int numBorrowedofUser;
    private int numLentofUser;
    private int thresholdNumofUser;
    private int numIncomplete;
    private int thresholdIncomplete;

    /** Constructor which will be called when a user is frozen by an admin.
     *
     * @param numBorrowed the total amount of incoming items a user has traded.
     * @param numLent the total amount of outgoing items a user has traded.
     * @param threshholdNum the number of lent minus borrowed a user is required to maintain.
     * @param numIncomp the number of incomplete trades attributed to the user.
     * @param thresholdIncomp the threshold of incomplete trades before a user is frozen.
     */
    public FrozenAlert(int numBorrowed, int numLent, int threshholdNum, int numIncomp, int thresholdIncomp){
        super(0);
        numBorrowedofUser = numBorrowed;
        numLentofUser = numLent;
        thresholdNumofUser = threshholdNum;
        numIncomplete = numIncomp;
        thresholdIncomplete = thresholdIncomp;

    }


    /** Handles the frozen alert by informing the user receiving it.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                        TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        /**menuPresenter.printMenu(23, 1); // Your account has been frozen by the administrator.
        menuPresenter.printMenu(23, 2, getNumBorrowedofUser()); // Number of items you borrowed:
        menuPresenter.printMenu(23, 3, getNumLentofUser()); // Number of items you lent:
        menuPresenter.printMenu(23, 4, getThreshholdNumofUser()); // Number of items you need to lend before you can borrow:
        menuPresenter.printMenu(23, 6, getNumIncomplete());
        menuPresenter.printMenu(23, 7, getThresholdIncomplete());
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(23, 5);
            input = scan.nextInt();
            if (input == 1) flag = false;
        }**/

        String desc = menuPresenter.getText(Frame.FROZENALERT, 1) + // Your account has been frozen by the administrator.
                menuPresenter.getText(Frame.FROZENALERT, 2, getNumBorrowedofUser()) + // Number of items you borrowed:
                menuPresenter.getText(Frame.FROZENALERT, 3, getNumLentofUser()) + // Number of items you lent:
                menuPresenter.getText(Frame.FROZENALERT, 4, getThreshholdNumofUser()) +  // Number of items you need to lend before you can borrow:
                menuPresenter.getText(Frame.FROZENALERT, 6, getNumIncomplete()) +
                menuPresenter.getText(Frame.FROZENALERT, 7, getThresholdIncomplete());

        DismissibleAlertPrompt freezeUserAlert = new DismissibleAlertPrompt(desc, menuPresenter);
    }

    /**
     *
     * @return The number of items the user has borrowed.
     */
    public int getNumBorrowedofUser() {
        return numBorrowedofUser;
    }

    /**
     *
     * @return the number of items the user has lent.
     */
    public int getNumLentofUser() {
        return numLentofUser;
    }

    /**
     *
     * @return the threshold of lent - borrowed that each user should be greater than or equal to.
     */
    public int getThreshholdNumofUser() {
        return thresholdNumofUser;
    }

    /**
     *
     *  @return the number of incomplete transactions of this user
     */
    public int getNumIncomplete() { return numIncomplete; }

    /**
     *
     * @return the incompleteThreshold
     */
    public int getThresholdIncomplete() { return thresholdIncomplete; }
}

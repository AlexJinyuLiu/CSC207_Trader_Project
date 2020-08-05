package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import controllerpresenterpack.MenuPresenter;
import entitypack.TradingUser;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.awt.*;
import java.io.Serializable;
import java.util.Scanner;

public class ReportAlert extends AdminAlert implements Serializable {
    /** Alert in which a user has been reported by another user for not showing up to a trade.
     *
     */

    private String senderUserName; // username of the user who is sending the report
    private String reportedUserName; // username of the user who is being reported
    private boolean isTradeComplete;
    private String reportDescription; // provision to describe why the report is being sent

    /** Called when a user has been reported.
     *
     * @param senderUserName the username of the person sending the report.
     * @param reportedUserName the username of the user being reported.
     * @param isTradeComplete whether or not the trade has been completed.
     * @param reportDescription a description of why a user is being reported.
     */
    public ReportAlert(String senderUserName, String reportedUserName, boolean isTradeComplete,
                       String reportDescription) {
        super(1);
        this.senderUserName = senderUserName;
        this.reportedUserName = reportedUserName;
        this.isTradeComplete = isTradeComplete;
        this.reportDescription = reportDescription;
    }


    /** Method that handles a ReportAlert by accepting the report or dismissing it
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                                   TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        /**menuPresenter.printMenu(12,0); // Handle Report Alert
        // alert.getSenderUserName() + " has reported user " + alert.getReportedUserName() +
        //         " whose trade status is " + alert.getIsTradeComplete()
        //         + "\n" + "Details: " + alert.getReportDescription()
        menuPresenter.printMenu(12, 3, getSenderUserName(), getReportedUserName());
        menuPresenter.printMenu(12, 4, getIsTradeComplete());
        menuPresenter.printMenu(12, 5, getReportDescription());

        boolean flag = true;
        int input = 0;
        int numIncompTrades = 0;
        int threshold = 0; // threshold of incomplete trades
        while (flag){
            Scanner scan = new Scanner(System.in);
            // "(1) Accept report"
            menuPresenter.printMenu(12,1);
            // "(2) Dismiss"
            menuPresenter.printMenu(12,2);
            input = scan.nextInt();
            if (input == 1){
                userManager.increaseUserIncompleteTrades(
                        (TradingUser)userManager.searchUser(getReportedUserName()));
                int numIncompleteTrades =
                        ((TradingUser)userManager.searchUser(getReportedUserName())).getNumIncompleteTrades();
                threshold = userManager.getIncompleteThreshold();
                if (numIncompleteTrades > threshold){
                    TradingUser reportedUser = (TradingUser)userManager.searchUser(getReportedUserName());
                    adminUser.freezeUser(reportedUser);
                    int numBorrowed = reportedUser.getNumBorrowed();
                    int numLent = reportedUser.getNumLent();
                    int incompleteT = reportedUser.getNumIncompleteTrades();
                    FrozenAlert frozenAlert = new FrozenAlert(numBorrowed, numLent, tradeCreator.getBorrowLendThreshold(), incompleteT,
                            userManager.getIncompleteThreshold());
                    userManager.alertUser(reportedUser.getUsername(), frozenAlert);
                }
                flag = false;
            }
            if (input == 2){
                flag = false;
            }
        }**/

        String desc = menuPresenter.getText(Frame.REPORTALERT,0) + // Handle Report Alert
        // alert.getSenderUserName() + " has reported user " + alert.getReportedUserName() +
        //         " whose trade status is " + alert.getIsTradeComplete()
        //         + "\n" + "Details: " + alert.getReportDescription()
                menuPresenter.getText(Frame.REPORTALERT, 3, getSenderUserName(), getReportedUserName()) +
                menuPresenter.getText(Frame.REPORTALERT, 4, getIsTradeComplete()) +
                menuPresenter.getText(Frame.REPORTALERT, 5, getReportDescription());
        //TODO
        AcceptableAlert reportAlert = new AcceptableAlert();
    }
    /**
     *
     * @return the username of the user who is sending the report.
     */
    public String getSenderUserName() {
        return senderUserName;
    }

    /**
     *
     * @return the username of the user who is being reported.
     */
    public String getReportedUserName() {
        return reportedUserName;
    }

    /**
     *
     * @return whether the trade between users is incomplete.
     */
    public boolean getIsTradeComplete() {
        return isTradeComplete;
    }

    /**
     *
     * @return the description by the sending user as to why the reported user is being reported.
     */
    public String getReportDescription() {
        return reportDescription;
    }


}


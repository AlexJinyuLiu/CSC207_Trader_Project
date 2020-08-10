package alertpack;

import controllerpresenterpack.MenuPresenter;
import entitypack.Trade;
import entitypack.TradingUser;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A user alert that is sent to a user when the meetup date for one of their trades has passed.
 */
public class TradePastDateAlert extends UserAlert implements Serializable {

    private LocalDateTime dueDate;
    private String username;
    private int tradeId;

    /**
     * Constructs a new TradePastDateAlert, storing data about when the trade was due to happen and the other user
     * involved.
     * @param dueDate A LocalDateTimeObject
     * @param username The username of the user.
     * @param tradeId the unique ID of the trade.
     */
    public TradePastDateAlert(LocalDateTime dueDate, String username, int tradeId){
        super(7);
        this.dueDate = dueDate;
        this.username = username;
        this.tradeId = tradeId;
    }

    /** Handles the TradePastDateAlert by giving the user options as to what to do in response.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        MenuPresenter menuPresenter = (MenuPresenter) menuPresenterObject;
        // "The following trade expired at" + a.getDueDate()+ "\n" +
        //         tradeToString(userManager, tradeCreator.searchPendingTrade(a.getTradeId()))
        menuPresenter.printMenu(30, 1);
        boolean flag = false;
        int input = 0;
        int x = 0;
        while (!flag) {
            x++;
            Scanner scan = new Scanner(System.in);
            menuPresenter.printTradeToString(itemManager, tradeCreator.searchTrades(getTradeId()));

            // "(1) Confirm EntityPack.Trade\n(2) I didn't show up\n(3) The other person didn't show up"
            menuPresenter.printMenu(30, 2);
            menuPresenter.printMenu(30, 3);
            menuPresenter.printMenu(30, 4);
            input = scan.nextInt();
            if (input == 1) {
                flag = true;
                TradingUser user = (TradingUser)userManager.searchUser(getUsername());
                Trade trade = tradeCreator.searchTrades(getTradeId());
                TradingUser user1 = (TradingUser)userManager.searchUser(trade.getUsername1());
                TradingUser user2 = (TradingUser)userManager.searchUser(trade.getUsername2());
                ArrayList<ArrayList<Trade>> listsOfTradesToCancel =
                        tradeCreator.confirmTrade(userManager, user, trade, itemManager, user1, user2);

                //iterating through and alerting users to the fact that their trades that have been cancelled
                for (int i = 0; i < 2; i++) {
                    for (Trade alertTrade : listsOfTradesToCancel.get(i)) {
                        TradingUser firstUser = (TradingUser) userManager.searchUser(alertTrade.getUsername1());
                        TradingUser secondUser = (TradingUser) userManager.searchUser(alertTrade.getUsername2());
                        UserAlert alert = new TradeRequestCancelledAlert(alertTrade.getTradeID());
                        userManager.alertUser(firstUser, alert);
                        userManager.alertUser(secondUser, alert);
                    }
                }

                //removing the actual cancelled trades from storage in tradeCreator
                tradeCreator.removePendingTradeRequests(listsOfTradesToCancel.get(0));
                tradeCreator.removePendingTrades(listsOfTradesToCancel.get(1));

                //Alerting users that have been frozen as a result of this trade's completion
                assert user1 != null;
                if (user1.getNumBorrowed() + tradeCreator.getBorrowLendThreshold() > user1.getNumLent()) {
                    FreezeUserAlert alert = new FreezeUserAlert(user1.getUsername(), user1.getNumLent(),
                            user1.getNumBorrowed(), tradeCreator.getBorrowLendThreshold());
                    tradeCreator.alertAdmin(alert);
                }
                assert user2 != null;
                if (user2.getNumBorrowed() + tradeCreator.getBorrowLendThreshold() > user2.getNumLent()) {
                    FreezeUserAlert alert = new FreezeUserAlert(user2.getUsername(), user2.getNumLent(),
                            user2.getNumBorrowed(), tradeCreator.getBorrowLendThreshold());
                    tradeCreator.alertAdmin(alert);
                }


                // Trade confirmed. Your items have been exchanged on the system."
                menuPresenter.printMenu(30, 5);

            } else if (input == 2){
                flag = true;
            } else if (input == 3){
                Trade trade = tradeCreator.searchTrades(getTradeId());
                String reportingUser, reportedUser;
                if(getUsername().equals(trade.getUsername1())){
                    reportingUser = trade.getUsername1();
                    reportedUser = trade.getUsername2();
                } else{
                    reportingUser = trade.getUsername2();
                    reportedUser = trade.getUsername1();
                }

                AdminAlert alert = new ReportAlert(reportingUser, reportedUser, false,
                        reportedUser + " didn't show up");
                userManager.alertAdmin(alert);
                //"Report has been sent to the tribunal."
                menuPresenter.printMenu(24, 5);
                flag = true;
            }
        }
    }

    /**
     *
     * @return the due date of the trade.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }


    /**
     *
     * @return the username of the user involved in the trade.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return trade id.
     */
    public int getTradeId() {
        return tradeId;
    }

}

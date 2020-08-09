package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExpirationAlert extends AlertFactory implements Serializable {
    /** An alert which is tells a user that a temporary trade they are involved in has expired.
     *
     */

    private LocalDateTime dueDate;
    private String username;
    private int tradeId;

    /** Called when a temporary trade has expired.
     *
     * @param dueDate the time & date at which the temporary trade expired.
     * @param username the username with whom the user conducted the trade with.
     * @param tradeId the ID number of the trade.
     */
    public ExpirationAlert(LocalDateTime dueDate, String username, int tradeId){
        super(2);
        this.dueDate = dueDate;
        this.username = username;
        this.tradeId = tradeId;
    }

    /** Handles the ExpirationAlert by prompting the user to either confirm that they showed up or report the other
     *  user for not showing up.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){

        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        // "The following EntityPack.TemporaryTrade has expired at" + alert.getDueDate() + ":\n" +
        //        tradeToString(userManager, tradeCreator.getTradeHistories().searchTemporaryTrade(alert.getTradeId()))
        /**menuPresenter.printMenu(24, 1);
        boolean flag = true;
        int input = 0;

        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "(1) Report the other user \n (2) Confirm ReExchange"
            menuPresenter.printMenu(24, 2);
            menuPresenter.printMenu(24, 3);
            input = scan.nextInt();
            if (input == 1 || input == 2) flag = false;
        }
        if (input == 1) {
            Trade trade = tradeCreator.getTradeHistories().searchTemporaryTrade(getTradeId());
            Scanner scan = new Scanner(System.in);
            // "Reason for reporting: + \n"
            menuPresenter.printMenu(24, 4);
            String message = scan.nextLine();

            String user2 = getUsername();
            String user1;
            if (user2.equals(trade.getUsername2())){
                user1 = trade.getUsername1();
            } else user1 = trade.getUsername2();


            AdminAlert reportAlert = new ReportAlert(user1, user2, false, message);
            userManager.alertAdmin(reportAlert);
            // "Report has been sent to the tribunal"
            menuPresenter.printMenu(24, 5);

        } else {
            TradingUser user = (TradingUser)userManager.searchUser(getUsername());
            TemporaryTrade trade = tradeCreator.getTradeHistories().searchTemporaryTrade(getTradeId());
            tradeCreator.getTradeHistories().confirmReExchange(itemManager, user, trade);
            // "EntityPack.Trade ReExchange confirmed"
            menuPresenter.printMenu(24, 6);
        }**/

        ExpirationAlertPrompt expirationAlertPrompt = new ExpirationAlertPrompt(getDueDate(), getUsername(), getTradeId(),
                menuPresenter, tradeCreator, itemManager);
    }



    /**
     *
     * @return The date & time at which the temporary trade is over and the items should be returned.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     *
     * @return returns the username of the user this alert is being sent to.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the ID of the temporary trade.
     */
    public int getTradeId() {
        return tradeId;
    }


}

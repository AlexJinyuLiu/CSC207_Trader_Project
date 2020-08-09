package alertpack;

import controllerpresenterpack.MenuPresenter;
import entitypack.TemporaryTrade;
import entitypack.Trade;
import entitypack.TradingUser;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A user alert that alerts a user when they have recieved a trade request from annother user.
 */
public class TradeRequestAlert extends AlertFactory implements Serializable {
    private String senderUserName;
    private int tradeID;
    private boolean isTempTrade;

    /**
     * Constructs a new TradeRequestAlert, storing data about the sender and trade.
     * @param senderUserName the username of the user who sent the trade request
     * @param tradeID the ID of the trade
     * @param isTempTrade a boolean representing whether or not the trade was accepted.
     */
    public TradeRequestAlert(String senderUserName, int tradeID, boolean isTempTrade){
        super(8);
        this.senderUserName = senderUserName;
        this.tradeID = tradeID;
        this.isTempTrade = isTempTrade;
    }

    /** Handles the TradeRequestAlert by prompting the user as to what to do in response.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        MenuPresenter menuPresenter = (MenuPresenter)menuPresenterObject;
        if (getIsTempTrade()) {
            menuPresenter.printMenu(37, 0, getSenderUserName(), "");
            menuPresenter.printTradeToString(itemManager, tradeCreator.searchPendingTradeRequest(getTradeID()));
        } else {
            menuPresenter.printMenu(37, 1, getSenderUserName(), "");

            menuPresenter.printTradeToString(itemManager, tradeCreator.searchPendingTradeRequest(getTradeID()));
        }
        boolean canEditTrade = true;
        int input = 0;

        Scanner scan = new Scanner(System.in);
        Trade trade = tradeCreator.searchPendingTradeRequest(getTradeID());

        TradingUser thisUser;

        int numEditsRemaining;
        if (getSenderUserName().equals(trade.getUsername1())){
            numEditsRemaining = 3 - trade.getUser2NumRequests();
            thisUser = (TradingUser)userManager.searchUser(trade.getUsername2());
        }else{
            numEditsRemaining = 3 - trade.getUser1NumRequests();
            thisUser = (TradingUser)userManager.searchUser(trade.getUsername1());
        }

        if (numEditsRemaining == 0){
            canEditTrade = false;
        }
        // "(1) Accept \n (2) Decline \n (3) Edit time and Place (" + numEditsRemaining +
        //        " edits remaining)"
        menuPresenter.printMenu(25, 2);
        menuPresenter.printMenu(25, 3);
        menuPresenter.printMenu(25, 4);
        menuPresenter.printMenu(25, 5, numEditsRemaining);

        input = scan.nextInt();

        assert thisUser != null;

        if (input == 1){
            tradeCreator.acceptTradeRequest(trade, thisUser.getUsername());
            //Alerting the other user that the pending trade request has been accepted.
            TradeAcceptedAlert alert = new TradeAcceptedAlert(thisUser.getUsername(), trade.getTradeID());
            String otherUserName;

            if (trade.getUsername1().equals(thisUser.getUsername())) {
                otherUserName = trade.getUsername2();
            } else {
                otherUserName = trade.getUsername1();
            }
            userManager.alertUser(otherUserName, alert);
            // "EntityPack.Trade Request Accepted. Meet up with the person at the time and place specified above."+
            //         "Remember to login to confirm the trade afterwords!"
            menuPresenter.printMenu(25, 6);
            menuPresenter.printMenu(25, 7);
        } else if (input == 2){
            tradeCreator.declineTradeRequest(trade);
            TradeDeclinedAlert alert = new TradeDeclinedAlert(thisUser.getUsername(), trade.getTradeID());

            String otherUserName;

            if (trade.getUsername1().equals(thisUser.getUsername())) {
                otherUserName = trade.getUsername2();
            } else {
                otherUserName = trade.getUsername1();
            }
            userManager.alertUser(otherUserName, alert);
            // "EntityPack.Trade Request Declined."
            menuPresenter.printMenu(25, 8);
        } else if (input == 3){
            // "Editing EntityPack.Trade Request. \n Enter new meeting time (format: yyyy-MM-dd HH:mm: \n"
            menuPresenter.printMenu(25, 9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            boolean stringNotFound = true;
            LocalDateTime meetingTime = null;
            while(stringNotFound) {
                scan.nextLine();
                String inputDateTime = scan.nextLine();
                try {
                    meetingTime = LocalDateTime.parse(inputDateTime, formatter);
                } catch (DateTimeParseException e) {
                    // "Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm) :"
                    menuPresenter.printMenu(25, 10);
                    continue;
                }
                stringNotFound = false;
            }

            // "Enter new meeting place:"
            menuPresenter.printMenu(25, 11);
            String inputMeetingPlace = scan.nextLine();
            assert meetingTime != null;
            tradeCreator.editTradeRequest(userManager, trade, meetingTime, inputMeetingPlace, thisUser.getUsername());

            TradeRequestAlert alert = new TradeRequestAlert(thisUser.getUsername(), trade.getTradeID(),
                    trade instanceof TemporaryTrade);

            String otherUserName;

            if (trade.getUsername1().equals(thisUser.getUsername())) {
                otherUserName = trade.getUsername2();
            } else {
                otherUserName = trade.getUsername1();
            }

            tradeCreator.alertUser(otherUserName, alert);
            // "EntityPack.Trade successfully edited. Meeting at " + inputMeetingPlace + " at " + meetingTime +
            //         "."
            menuPresenter.printMenu(25, 12);
        }
    }

    /**
     *
     * @return username of the person who has proposed the trade.
     */
    public String getSenderUserName() {
        return senderUserName;
    }

    /**
     *
     * @return the ID of the trade request proposed
     */
    public int getTradeID(){ return tradeID; }

    public boolean getIsTempTrade(){
        return this.isTempTrade;
    }
}

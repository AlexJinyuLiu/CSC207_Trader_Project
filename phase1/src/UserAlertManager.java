import AlertPack.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class UserAlertManager {

    /**
     * Iterate through each alert in alerts, handle it and remove it from the list
     * @param alerts list of alert that are sent in
     */
    public void handleAlertQueue(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                                 ArrayList<UserAlert> alerts){
        while(!(alerts.size() == 0)){
            UserAlert alert = alerts.get(0);
                System.out.println(alerts);
                handleAlert(menuPresenter, userManager, tradeCreator, alert);
                alerts.remove(alert);
        }
    }

    /**
     * Handle alert based on its coresponding type
     * @param alert alert sent in
     */
    private void handleAlert(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                             UserAlert alert) {

        if (alert.getType() == 0) {
            handleFrozenAlert(menuPresenter, (FrozenAlert) alert);
        } else if (alert.getType() == 1){
            handleItemValidationDeclinedAlert(menuPresenter,userManager, (ItemValidationDeclinedAlert) alert);
        } else if (alert.getType() == 2) {
            handleExpirationAlert(menuPresenter, userManager, tradeCreator, (ExpirationAlert) alert);
        } else if (alert.getType() == 3) {
            handleMessageAlert(menuPresenter,(MessageAlert) alert);
        } else if (alert.getType() == 4) {
            handleTradeAcceptedAlert(menuPresenter, userManager, tradeCreator, (TradeAcceptedAlert) alert);
        } else if (alert.getType() == 5) {
            handleTradeCancelledAlert(menuPresenter, userManager, tradeCreator, (TradeCancelledAlert) alert);
        } else if (alert.getType() == 6){
            handleTradeDeclinedAlert(menuPresenter, (TradeDeclinedAlert) alert);
        } else if (alert.getType() == 7) {
            handleTradePastDateAlert(menuPresenter,userManager, tradeCreator, (TradePastDateAlert) alert);
        } else if (alert.getType() == 8){
            handleTradeRequestAlert(userManager, tradeCreator, (TradeRequestAlert) alert, menuPresenter);
        } else if (alert.getType() == 9) {
            handleTradeRequestCancelledAlert(menuPresenter, userManager, tradeCreator, (TradeRequestCancelledAlert) alert);
        }

            //Each alert needs a handle method for its type, which prints/takes input and calls corresponding functions to
            //  handle the alert on the end user side of things. See google document for specifics on alerts and their
            //  handling process.

    }


    private void handleFrozenAlert(MenuPresenter menuPresenter, FrozenAlert a){
        menuPresenter.printMenu(23, 1); // Your account has been frozen by the administrator.
        menuPresenter.printMenu(23, 2); // Number of items you borrowed:
        menuPresenter.printMenu(23, 3); // Number of items you lent:
        menuPresenter.printMenu(23, 4); // Number of items you need to lend before you can borrow:
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(23, 5);
            input = scan.nextInt();
            if (input == 1) flag = false;
        }
    }

    private void handleExpirationAlert(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                                       ExpirationAlert alert){

        // System.out.println("The following TemporaryTrade has expired at" + alert.getDueDate() + ":\n" +
        //        tradeToString(userManager, tradeCreator.tradeHistories.searchTemporaryTrade(alert.getTradeId())));
        menuPresenter.printMenu(24, 1);
        boolean flag = true;
        int input = 0;

        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Report the other user \n (2) Confirm ReExchange");
            menuPresenter.printMenu(24, 2);
            menuPresenter.printMenu(24, 3);
            input = scan.nextInt();
            if (input == 1 || input == 2) flag = false;
        }
        if (input == 1) {
            Trade trade = tradeCreator.tradeHistories.searchTemporaryTrade(alert.getTradeId());
            Scanner scan = new Scanner(System.in);
            // System.out.println("Reason for reporting: + \n");
            menuPresenter.printMenu(24, 4);
            String message = scan.nextLine();

            String user2 = alert.getUsername();
            String user1;
            if (user2.equals(trade.getUsername2())){
                user1 = trade.getUsername1();
            } else user1 = trade.getUsername2();

            userManager.reportUser(user1, user2, message,false);
            // System.out.println("Report has been sent to the tribunal");
            menuPresenter.printMenu(24, 5);

        } else {
            User user = userManager.searchUser(alert.getUsername());
            TemporaryTrade trade = tradeCreator.tradeHistories.searchTemporaryTrade(alert.getTradeId());
            tradeCreator.tradeHistories.confirmReExchange(userManager, user, trade);
            // System.out.println("Trade ReExchange confirmed");
            menuPresenter.printMenu(24, 6);
        }
    }

    private void handleTradeRequestAlert(UserManager userManager, TradeCreator tradeCreator, TradeRequestAlert a, MenuPresenter menuPresenter){
        if (a.getIsTempTrade()) {
            menuPresenter.printMenu(37, 0, a.getSenderUserName(), "");
            menuPresenter.printTradeToString(userManager, tradeCreator.searchPendingTradeRequest(a.getTradeID()));
        } else {
            menuPresenter.printMenu(37, 1, a.getSenderUserName(), "");

            menuPresenter.printTradeToString(userManager, tradeCreator.searchPendingTradeRequest(a.getTradeID()));
        }
        boolean canEditTrade = true;
        int input = 0;

        Scanner scan = new Scanner(System.in);
        Trade trade = tradeCreator.searchPendingTradeRequest(a.getTradeID());

        User thisUser;

        int numEditsRemaining;
        if (a.getSenderUserName().equals(trade.getUsername1())){
            numEditsRemaining = 3 - trade.getUser2NumRequests();
            thisUser = userManager.searchUser(trade.getUsername2());
        }else{
            numEditsRemaining = 3 - trade.getUser1NumRequests();
            thisUser = userManager.searchUser(trade.getUsername1());
        }

        if (numEditsRemaining == 0){
            canEditTrade = false;
        }
        // System.out.println("(1) Accept \n (2) Decline \n (3) Edit time and Place (" + numEditsRemaining +
        //        " edits remaining)");
        menuPresenter.printMenu(25, 2);
        menuPresenter.printMenu(25, 3);
        menuPresenter.printMenu(25, 4);
        menuPresenter.printMenu(25, 5, numEditsRemaining);

        input = scan.nextInt();

        assert thisUser != null;

        if (input == 1){
            tradeCreator.acceptTradeRequest(trade, thisUser.getUsername());
            // System.out.println("Trade Request Accepted. Meet up with the person at the time and place specified above."+
            //         "Remember to login to confirm the trade afterwords!");
            // TODO remove these debug statements
            System.out.println(trade.user1AcceptedRequest);
            System.out.println(trade.user2AcceptedRequest);
            menuPresenter.printMenu(25, 6);
            menuPresenter.printMenu(25, 7);
        } else if (input == 2){
            tradeCreator.declineTradeRequest(trade, thisUser.getUsername());
            // System.out.println("Trade Request Declined.");
            menuPresenter.printMenu(25, 8);
        } else if (input == 3){
            // System.out.println("Editing Trade Request. \n Enter new meeting time (format: yyyy-MM-dd HH:mm: \n");
            menuPresenter.printMenu(25, 9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            boolean stringNotFound = true;
            LocalDateTime meetingTime = null;
            while(stringNotFound) {

                String inputDateTime = scan.nextLine();
                try {
                    meetingTime = LocalDateTime.parse(inputDateTime, formatter);
                } catch (DateTimeParseException e) {
                    // System.out.println("Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm) :");
                    menuPresenter.printMenu(25, 10);
                    continue;
                }
                stringNotFound = false;
            }

            // System.out.println("Enter new meeting place:");
            menuPresenter.printMenu(25, 11);
            String inputMeetingPlace = scan.nextLine();

            //TODO: Ensure that this is not always null (the compiler complains that it is but I have my doubts).
            assert meetingTime != null;
            tradeCreator.editTradeRequest(userManager, trade, meetingTime, inputMeetingPlace, thisUser.getUsername());
            // System.out.println("Trade successfully edited. Meeting at " + inputMeetingPlace + " at " + meetingTime +
            //         ".");
            menuPresenter.printMenu(25, 12);
        }
    }


    private void handleTradeAcceptedAlert(MenuPresenter menuPresenter, UserManager userManager,
                                          TradeCreator tradeCreator, TradeAcceptedAlert a){

        //System.out.println(a.getAcceptingUsername() +
        //        " has accepted the following trade request: \n" + tradeToString(userManager,
            Trade b = tradeCreator.searchTrades(a.getTradeID());
            menuPresenter.printMenu(26, 1);
            menuPresenter.printTradeToString(userManager, b);

        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(26, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }

    }

    private void handleTradeDeclinedAlert(MenuPresenter menuPresenter, TradeDeclinedAlert a){
        // System.out.println(a.getDecliningUserName() + " has declined your trade request with ID " + a.getTradeID());
        menuPresenter.printMenu(27, 1);
        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(27, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }

    }

    private void handleTradeCancelledAlert(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator, TradeCancelledAlert a) {

        // System.out.println("The following pending trade has been cancelled as one of the users is no longer in possession of " +
        //         "a item in the proposed trade. Trade ID: " + a.getTradeID());
        menuPresenter.printMenu(28, 1);
        menuPresenter.printTradeToString(userManager, tradeCreator.searchTrades(a.getTradeID()));
        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(28, 2);
            input = scan.nextInt();
            if (input == 1) {handled = true;}
        }
    }

    private void handleTradeRequestCancelledAlert(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator, TradeRequestCancelledAlert a) {

        // System.out.println("The following trade request has been cancelled as one of the users is no " +
        //         "longer in possession of an item in the proposed trade. Trade ID: " + a.getTradeID() );
        menuPresenter.printMenu(28, 1);
        menuPresenter.printTradeToString(userManager, tradeCreator.searchPendingTradeRequest(a.getTradeID()));
        boolean handled = false;

        int input = 0;

        while (!handled) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(28, 2);
            input = scan.nextInt();
            if (input == 1) {handled = true;}
        }
    }

    private void handleItemValidationDeclinedAlert(MenuPresenter menuPresenter, UserManager userManager,
                                                   ItemValidationDeclinedAlert a){

        //System.out.println("Your item validation request has been declined for the following reason: \n" +
        //        a.getMessage()+ ".\nUser: " + a.getOwner() + "Item name: " + a.getName() + "\nItem description: " +
        //        a.getDescription() + "\nItem ID number: " + a.getItemID() );
        menuPresenter.printMenu(29, 1);
        menuPresenter.printMenu(29, 2);
        menuPresenter.printMenu(29, 3);
        menuPresenter.printMenu(29, 4);
        menuPresenter.printMenu(29, 5);
        // System.out.println("(1) Dismiss");
        menuPresenter.printMenu(29, 6);
        //System.out.println("(2) Send a new item validation request");
        menuPresenter.printMenu(29, 7);
        int choice = optionChoice(menuPresenter,2);
        if (choice == 2){
            Scanner scan = new Scanner(System.in);
            String name = null;
            // System.out.println("Please enter the name of your item");
            menuPresenter.printMenu(29, 8);
            scan.nextLine(); //This awfulness is needed to prevent it from skipping a line. - Louis
            name = scan.nextLine();
            // System.out.println("Please enter the item description");
            menuPresenter.printMenu(29, 9);
            String description = scan.nextLine();
            String username = a.getOwner();
            userManager.sendValidationRequest(name,description,username);
        }
    }

    private void handleTradePastDateAlert(MenuPresenter menuPresenter, UserManager userManager,
                                          TradeCreator tradeCreator,TradePastDateAlert a){

        // System.out.println("The following trade expired at" + a.getDueDate()+ "\n" +
        //         tradeToString(userManager, tradeCreator.searchPendingTrade(a.getTradeId())));
        menuPresenter.printMenu(30, 1);
        boolean flag = false;
        int input = 0;
        int x = 0;
        System.out.println("entering while loop");
        while (!flag) {
            x++;
            System.out.print("x: ");
            System.out.println(x);
            Scanner scan = new Scanner(System.in);
            System.out.print("TradeID: ");
            System.out.println(a.getTradeId());
            System.out.print("pending trades: ");
            System.out.println(tradeCreator.pendingTrades);
            System.out.print("flag: ");
            System.out.println(flag);
            menuPresenter.printTradeToString(userManager, tradeCreator.searchTrades(a.getTradeId()));

            // System.out.println("(1) Confirm Trade\n(2) I didn't show up\n(3) The other person didn't show up");
            menuPresenter.printMenu(30, 2);
            menuPresenter.printMenu(30, 3);
            menuPresenter.printMenu(30, 4);
            input = scan.nextInt();
            if (input == 1) {
                flag = true;
                System.out.print("flag: ");
                System.out.println(flag);
                User user = userManager.searchUser(a.getUsername());
                tradeCreator.confirmTrade(userManager, user, tradeCreator.searchTrades(a.getTradeId()));
                // System.out.println("Trade confirmed. Your items have been exchanged on the system.");
                menuPresenter.printMenu(30, 5);

            } else if (input == 2){
                flag = true;
            } else if (input == 3){
                Trade trade = tradeCreator.searchTrades(a.getTradeId());
                String reportingUser, reportedUser;
                if(a.getUsername().equals(trade.getUsername1())){
                    reportingUser = trade.getUsername1();
                    reportedUser = trade.getUsername2();
                } else{
                    reportingUser = trade.getUsername2();
                    reportedUser = trade.getUsername1();
                }

                AdminAlert alert = new ReportAlert(reportingUser, reportedUser, false, reportedUser +
                        " didn't show up");
                userManager.alertAdmin(alert);
                //"Report has been sent to the tribunal."
                menuPresenter.printMenu(24, 5);
                flag = true;
            }
        }
    }

    private void handleMessageAlert(MenuPresenter menuPresenter, MessageAlert a){
        // System.out.println("From: " + a.getSenderUsername() + "\n" + a.getMessage());
        menuPresenter.printMenu(31, 1);
        boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Dismiss");
            menuPresenter.printMenu(31, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }

    }

    //helper method to ensure the user picks a valid choice, options are between 1 and x - Louis
    private int optionChoice(MenuPresenter menuPresenter, int x){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter one of the numbers listed above");
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice >= x || choice < 0){
            // System.out.println("The number you entered was not listed above. Please enter a choice between 1 and " + x);
            menuPresenter.printMenu(5, 2);
        }
        return choice;


    }
    //helper method to ensure the user picks a valid choice, options are between 1 and x - Louis
    private int optionChoice(MenuPresenter menuPresenter, int x, int y){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter one of the numbers listed above");
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice >= y || choice < x){
            // System.out.println("The number you entered was not listed above. Please enter a choice between 1 and " + x);
            menuPresenter.printMenu(5, 2);
        }
        return choice;


    }



}

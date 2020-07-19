package controllerpresenterpack;

import alertpack.UnfreezeRequestAlert;
import entitypack.Item;
import entitypack.TemporaryTrade;
import entitypack.Trade;
import entitypack.User;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controller class describing the actions a user can take from the menu in the trade system.
 */
public class UserActions {

    /**
     * runs the usermenu as normal.
     * @param user the user currently logged in.
     */
    public void runUserMenu(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator, User user){
        mainMenu(menuPresenter,userManager, tradeCreator, user);
    }

    /**
     * Display the main menu to user, take user's input to implement the corresponding action
     * @param user user logged in making changes or viewing statuses
     */
    public void mainMenu(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator, User user){

        boolean running = true;
        while (running) {
            int input = -1;
            Scanner scan = new Scanner(System.in);
            for (int i = 0; i < 8; i++) {
                menuPresenter.printMenu(15, i);
            }
            menuPresenter.printMenu(15, 10);
            boolean valid_input = false;
            while(!valid_input){
                //"Please enter a number corresponding to a setting above:\n"
                menuPresenter.printMenu(15, 8);
                input = scan.nextInt();
                if (input > 7 || input < 0) {
                    //"Please enter a number from 0 to 6"
                    menuPresenter.printMenu(15, 9);
                } else if (input == 1) {
                    viewItemAndWishlist(menuPresenter,userManager, user);
                    valid_input = true;
                } else if (input == 2) {
                    runStats(menuPresenter,userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 3) {
                    sendUnfreezeRequest(menuPresenter, userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 4) {
                    viewAllUsers(menuPresenter, userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 5) {
                    viewPendingTrades(menuPresenter, userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 6) {
                    viewActiveTempTrades(menuPresenter, userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 7) {
                    setActive(menuPresenter, user);
                    valid_input = true;
                } else if (input == 0){
                    valid_input = true;
                    running = false;
                }
            }
        }
    }

    public void setActive(MenuPresenter menuPresenter, User user){
        String act = "";
        Scanner scan = new Scanner(System.in);
        if (user.isActive()){
            act = "active";
        } else act = "inactive";
        // Your account is active/inactive
        menuPresenter.printMenu(41, 0, act);

        if (act.equals("active")){
            // Would you like to set your account as inactive?
            menuPresenter.printMenu(41, 2);
            menuPresenter.printMenu(41, 5);
            menuPresenter.printMenu(41, 6);
            while(true){
                int input = scan.nextInt();
                if (input == 1){
                    user.setActive(false);
                    // Your account has been deactivated
                    menuPresenter.printMenu(41, 3);
                    break;
                } else if (input == 0){
                    user.setActive(true);
                    break;
                }
            }
        } else {
            // Would you like to activate your account?
            menuPresenter.printMenu(41, 1);
            menuPresenter.printMenu(41, 5);
            menuPresenter.printMenu(41, 6);
            while(true){
                int input = scan.nextInt();
                if (input == 1){
                    user.setActive(false);
                    // Your account has been activated
                    menuPresenter.printMenu(41, 4);
                    break;
                } else if (input == 0){
                    user.setActive(true);
                    break;
                }
            }
        }

    }

    /**
     * Allow user to view their available items (inventory) and wishlist
     * @param user user logged in viewing their items
     */
    public void viewItemAndWishlist(MenuPresenter menuPresenter, UserManager userManager, User user){
        boolean flag = true;
        int input = 0;
        Scanner scan = new Scanner(System.in);
        if (user.getAvailableItems().size() == 0){
            //"You have no items."
            menuPresenter.printMenu(16, 1);
        } else {
            //"Your available items:"
            menuPresenter.printMenu(16, 2);
            for (Item item : user.getAvailableItems()) {
                menuPresenter.printMenu(35, 0);
                menuPresenter.printItemToString(item);
            }
        }
        //"\n Your wishlist :"
        menuPresenter.printMenu(16, 3);
        for (String itemName : user.getWishlistItemNames()){
            menuPresenter.printMenu(35, 0, itemName);
        }

        while (flag){
            //"(1) Send EntityPack.Item Validation request \n (2) Remove an item from available items " +
            //"\n (3) Remove an item from your wishlist\n (0) Exit to main menu");
            menuPresenter.printMenu(16, 4);
            menuPresenter.printMenu(16, 5);
            menuPresenter.printMenu(16, 6);
            menuPresenter.printMenu(16, 14);
            input = scan.nextInt();
            if (input < 0 || input > 3){
                //"Please enter a number from 1 to 3"
                menuPresenter.printMenu(16, 7);
            } else flag = false;

         if (input == 1){
             String name = null;
             //"Please enter the name of your item"
             menuPresenter.printMenu(16, 8);
             scan.nextLine();
             name = scan.nextLine();
             //"Please enter the item description"
             menuPresenter.printMenu(16, 9);
             String description = scan.nextLine();
             String username = user.getUsername();
             userManager.sendValidationRequest(name,description,username);
         } else if (input == 2){
             //"Please enter the ID of the item you wish to remove"
             menuPresenter.printMenu(16, 10);
             int itemID = scan.nextInt();
             for (int i = 0; i < user.getAvailableItems().size(); i++){
                 if (user.getAvailableItems().get(i).getId() == itemID) {
                     user.getAvailableItems().remove(user.getAvailableItems().get(i));
                     //"EntityPack.Item deleted"
                     menuPresenter.printMenu(16, 11);
                 } else //"Invalid item id"
                     menuPresenter.printMenu(16, 12);
             }

         } else if (input == 3){
             //"Please enter the name of the item on your wishlist you wish to remove"
             menuPresenter.printMenu(16, 13);
             scan.nextLine();
             String wishlistItem = scan.nextLine();
             for (int i = 0; i < user.getWishlistItemNames().size(); i++) {
                 if (user.getWishlistItemNames().get(i).equals(wishlistItem)) {
                     user.getWishlistItemNames().remove(i);
                     //"The item has been removed"
                     menuPresenter.printMenu(16, 15);
                 }
             }
         } else if (input == 0){
             return;
         }

        }

    }

    /**
     * Allow user to view all the users in the trading system
     * @param userViewing user logged in viewing other users
     */
    public void viewAllUsers(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                             User userViewing){
        boolean handled = false;
        Scanner scan = new Scanner(System.in);
        // "--- View other users ---"
        menuPresenter.printMenu(17, 0);
        // "Enter a number to view a EntityPack.User's page:"
        menuPresenter.printMenu(17, 1);
        int page = 1;
        ArrayList<User> allUsers = userManager.getListUsers();
        boolean nextPageExists = true;
        while (!handled){
            int input = -1;
            StringBuilder usersString = new StringBuilder();
            for(int i = (9 * (page - 1)) + 1; i < (9 * page) + 1; i++){
                try {
                    usersString.append("(").append(i).append(") ").append(allUsers.get(i - 1).getUsername()).append("\n");
                } catch (IndexOutOfBoundsException e){
                    nextPageExists = false;
                    usersString.append("Back to Main Menu");
                    // menuPresenter.printMenu(18, 2);
                    break;
                }
            }
            if (nextPageExists) {
                usersString.append("(0) next page (current page: ").append(page).append(")").append("\n");
                // menuPresenter.printMenu(18, 3);
            }
            menuPresenter.printMenu(35, 0, usersString.toString());
            input = scan.nextInt();
            boolean valid_input = false;
            while(!valid_input) {
                if (input < 0 || input > allUsers.size() + 1 || (!nextPageExists && input == 0)){
                    // "Please Enter a valid input."
                    menuPresenter.printMenu(17, 4);
                    input = scan.nextInt();
                } else if (input == allUsers.size() + 1){
                    handled = true;
                    valid_input = true;
                }
                else if (input == 0) {
                    page += 1;
                    valid_input = true;
                } else {
                    handled = true;
                    valid_input = true;
                    menuPresenter.printMenu(35, 1, allUsers.get(input - 1).getUsername());
                    // menuPresenter.printMenu(18, 5);
                    viewUser(menuPresenter, userManager, tradeCreator, allUsers.get(input - 1), userViewing);
                }
            }
        }
    }

    /**
     * Allow userViewing to do the followings:
     * (1) send a message to userToView
     * (2) add one of userToView's items to the wishlist
     * (3) send a trade request to userToView
     * @param userToView user that is being viewed
     * @param userViewing user logged in that is viewing other user
     */
    private void viewUser(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                          User userToView, User userViewing) {
        Scanner scan = new Scanner(System.in);

        boolean handled = false;
        int input;
        while(!handled){
            //menuPresenter.printMenu(35, 0, userString.toString());
            menuPresenter.printUserToString(userToView);
            //StringBuilder userString = new StringBuilder(userToView.toString());
            //userString.append("(1) Send a message\n");
            menuPresenter.printMenu(18, 1);
            //userString.append("(2) Add one of their items to your wishlist\n");
            menuPresenter.printMenu(18, 2);
            if (!userToView.getFrozen()) {
                //userString.append("(3) Send a trade request\n");
                menuPresenter.printMenu(18, 3);
            }
            //userString.append("(0) Back to Main Menu");
            menuPresenter.printMenu(18, 4);

            input = scan.nextInt();

            if (input < 0 || input > 3 || (userToView.getFrozen() && input == 3)){
                // "Please enter a valid input"
                menuPresenter.printMenu(18, 5);
            } else if (input == 1) {
                // "Enter the contents of your message:\n"
                menuPresenter.printMenu(18, 6);
                scan.nextLine();
                String message = scan.nextLine();
                userManager.sendMessageToUser(userViewing, userToView, message);
                // "Sent message to " + userToView.getUsername() + ": \"" + message + "\""
                menuPresenter.printMenu(18, 7);
            } else if (input == 2){
                // "Enter the name of the item you would like added to your wishlist:\n"
                menuPresenter.printMenu(18, 8);
                scan.nextLine();
                String itemString = scan.nextLine();
                userManager.addToWishlist(userViewing, itemString);
                menuPresenter.printMenu(18, 9);
            } else if (input == 3 && !userToView.getFrozen()){
                formTradeRequest(menuPresenter, tradeCreator, userViewing, userToView);
            } else if (input == 0){
                handled = true;

            }
        }
    }

    /**
     * Allow userSending to send a trade request to userReceiving
     * @param userSending user logged in that sends the trade request
     * @param userReceiving user that will receive the trade request
     */
    private void formTradeRequest(MenuPresenter menuPresenter, TradeCreator tradeCreator, User userSending,
                                  User userReceiving) {
        Scanner scan = new Scanner(System.in);
        boolean finished = false;
        ArrayList<Integer> itemIDsRecieved = new ArrayList<Integer>();
        if (userReceiving.getAvailableItems().size() == 0){
            // "The other user has no available items. You will not receive anything in this trade."
            menuPresenter.printMenu(19, 1);
            finished = true;
        }
        while (!finished) {
            // "Enter the ID of an item want from this trade:\n"
            menuPresenter.printMenu(19, 2);
            int ID = scan.nextInt();
            boolean validID = false;
            for (Item item : userReceiving.getAvailableItems()) {
                if (item.getId() == ID) {
                    itemIDsRecieved.add(ID);
                    validID = true;
                } else if (ID == -1) {
                    validID = true;
                }
            }
            if (!validID) {
                // "Invalid ID. Please try again.\n"
                menuPresenter.printMenu(19, 3);
            } else {
                boolean validYNInput = false;
                while (!validYNInput) {
                    // "Would you like to add another item? (Y/N)"
                    menuPresenter.printMenu(19, 4);
                    scan.nextLine();
                    String anotherItem = scan.nextLine();
                    if (anotherItem.equals("Y")) {
                        validYNInput = true;
                    } else if (anotherItem.equals("N")) {
                        // "Moving on..."
                        menuPresenter.printMenu(19, 5);
                        validYNInput = true;
                        finished = true;
                    } else {
                        // "Please enter Y or N."
                        menuPresenter.printMenu(19, 6);
                    }
                }
            }
        }
        ArrayList<Integer> itemIDsSent = new ArrayList<Integer>();

        boolean finished2 = false;
        if (userSending.getAvailableItems().size() == 0){
            // "You will not be offering anything with this trade."
            menuPresenter.printMenu(19, 7);
            finished2 = true;
        }
        while (!finished2) {
            // "Enter the ID of an item you want to offer:"
            menuPresenter.printMenu(19, 8);
            // "Your available items to trade:"
            menuPresenter.printMenu(19, 9);
            StringBuilder availableItems = new StringBuilder();
            for (int i = 0; i < userSending.getAvailableItems().size() - 1; i++) {
                availableItems.append(userSending.getAvailableItems().get(i).getName() +
                        " (ID: " + userSending.getAvailableItems().get(i).getId() + "), ");
            }
            // menuPresenter.printMenu(20, 10);
            availableItems.append(userSending.getAvailableItems().get(userSending.getAvailableItems().size() -
                    1).getName() + " (ID: " +
                    userSending.getAvailableItems().get(userSending.getAvailableItems().size() - 1).getId() + ") ");
            menuPresenter.printMenu(35, 0, availableItems);
            // menuPresenter.printMenu(20, 10);

            int ID2 = scan.nextInt();
            boolean validID2 = false;
            for (Item item : userSending.getAvailableItems()) {
                if (item.getId() == ID2) {
                    itemIDsSent.add(ID2);
                    validID2 = true;
                } else if (ID2 == -1) {
                    validID2 = true;
                }
            }
            if (!validID2) {
                // "Invalid ID. Please try again."
                menuPresenter.printMenu(19, 3);
            } else {
                boolean validYNInput2 = false;
                while (!validYNInput2){
                    // "Would you like to add another item? (Y/N)"
                    menuPresenter.printMenu(19, 4);
                    scan.nextLine();
                    String anotherItem = scan.nextLine();
                    if (anotherItem.equals("Y")) {
                        validYNInput2 = true;
                    } else if (anotherItem.equals("N")) {
                        // "Moving on..."
                        menuPresenter.printMenu(19, 5);
                        validYNInput2 = true;
                        finished2 = true;
                    } else {
                        // "Please enter Y or N."
                        menuPresenter.printMenu(19, 6);
                    }
                }
            }
        }

        // "Enter a meeting time (format: yyyy-MM-dd HH:mm: "
        menuPresenter.printMenu(19, 11);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        boolean stringNotFound = true;
        LocalDateTime meetingTime = null;
        while (stringNotFound) {

            String inputDateTime = scan.nextLine();
            try {
                meetingTime = LocalDateTime.parse(inputDateTime, formatter);
            } catch (DateTimeParseException e) {
                // "Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm):"
                menuPresenter.printMenu(19, 12);
                continue;
            }
            stringNotFound = false;
        }

        // "Enter a meeting place: "
        menuPresenter.printMenu(19, 13);
        String meetingPlace = scan.nextLine();
        //"Should this be a temporary trade? (Y/N)"
        menuPresenter.printMenu(36, 0);
        boolean isTempTrade = false;
        boolean validYN = false;
        while(!validYN) {
            String tempYN = scan.nextLine();
            if (tempYN.equals("Y")){
                validYN = true;
                isTempTrade = true;
            } else if (tempYN.equals("N")){
                validYN = true;
                isTempTrade = false;
            } else{
                //"Please enter either Y or N:"
                menuPresenter.printMenu(36, 1);
            }
        }

        Boolean canBeProcessed;
        if (isTempTrade) {
            canBeProcessed = tradeCreator.sendTemporaryTradeRequest(userSending, userReceiving, itemIDsRecieved, itemIDsSent,
                    meetingTime, meetingPlace);
        } else {
            canBeProcessed = tradeCreator.sendTradeRequest(userSending, userReceiving, itemIDsRecieved, itemIDsSent,
                    meetingTime, meetingPlace);
        }
        if (!canBeProcessed){
            //"Your trade could not be processed. This could have happened if you have completed " +
             //       "too many trades this week or if one of the users was frozen"
            menuPresenter.printMenu(19, 14);
            menuPresenter.printMenu(19, 15);
        }else{
        // "Successfully created and sent trade request. You will be notified when they respond."
            menuPresenter.printMenu(19, 16);
        }
    }

    /**
     * Allow user to view the following statuses:
     * (1) Number of items they have borrowed
     * (2) Number of items lent
     * (3) Frozen status
     * (4) Number of pending trades that has not been completed
     * (5) Number of transactions in the week
     * (6) Items that they recently traded away
     * (7) Three most frequent trading partners
     * @param user user logged in viewing the statuses
     */
    public void runStats(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator, User user) {
        int input = 0;
        boolean handled = false;
        while (!handled) {
            while (true) {
                Scanner scan = new Scanner(System.in);
                for (int i = 0; i < 9; i++) {
                    menuPresenter.printMenu(20, i);
                }
                input = scan.nextInt();
                if (input > 8 || input < 1) {
                    // "Please enter a number from 1 to 8"
                    menuPresenter.printMenu(20, 9);
                } else break;
            }
            if (input == 1) {
                // "You have borrowed " + Integer.toString(user.getNumBorrowed()) + " items."
                menuPresenter.printMenu(20, 10, Integer.toString(user.getNumBorrowed()));
            } else if (input == 2) {
                // "You have lent " + Integer.toString(user.getNumLent()) + " items."
                menuPresenter.printMenu(20, 11, Integer.toString(user.getNumLent()));
            } else if (input == 3) {
                if (user.getFrozen()) {
                    // "Your account has been frozen"
                    menuPresenter.printMenu(20, 12);
                } else {
                    // "Your account is not frozen"
                    menuPresenter.printMenu(20, 13);
                }

            } else if (input == 4) { //Other methods need access to UseCasePack.UserManager methods
                //"Your account has made " + Integer.toString(incompleteTrades) +
                 //       " incomplete transactions"
                menuPresenter.printMenu(20, 14, user.getNumIncompleteTrades());

            } else if (input == 5) {
                int weeklyTransactions = tradeCreator.getTradeHistories().getNumTradesThisWeek(user.getUsername());
                // "Your account has made " + Integer.toString(weeklyTransactions) +
                //         " transactions this week"
                menuPresenter.printMenu(20, 15, Integer.toString(weeklyTransactions));

            } else if (input == 6) {
                ArrayList<Item> recentItems = tradeCreator.getTradeHistories().getNRecentItems(userManager, user.getUsername(), 3);
                for (Item item : recentItems) {
                    menuPresenter.printItemToString(item);
                }

            } else if (input == 7) {
                ArrayList<String> favouriteParnters = tradeCreator.getTradeHistories().getTopNTradingPartners(user.getUsername(), 3);
                menuPresenter.printMenu(35, 0, favouriteParnters);

            } else if (input == 8) {
                handled = true;
            }
        }
    }

    /**
     * Send a unfreeze request to admin
     * @param user user that sends the request
     */
    public void sendUnfreezeRequest(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                                    User user) {
        if (!user.getFrozen()) {
            // "Your account is not frozen"
            menuPresenter.printMenu(21, 1);
        }
        else {
            String username = user.getUsername();
            int numLent = user.getNumLent();
            int numBorrowed = user.getNumBorrowed();
            int borrowLendThreshold = tradeCreator.getBorrowLendThreshold();
            int incompleteTrades = user.getNumIncompleteTrades();
            int incompThreshold = userManager.getIncompleteThreshold();
            UnfreezeRequestAlert alert = new UnfreezeRequestAlert(username, numLent, numBorrowed, borrowLendThreshold,
                    incompleteTrades, incompThreshold);
            userManager.alertAdmin(alert);
            // "Your request has been sent"
            menuPresenter.printMenu(21, 2);
        }
    }

    /**
     * Allow user to view their pending trade history
     * @param user user logged in
     */
    public void viewPendingTrades(MenuPresenter menuPresenter, UserManager userManager,
                                  TradeCreator tradeCreator, User user){
        int choice = 0;
        while (choice == 0 ) {
            ArrayList<Trade> userTrades = tradeCreator.searchPendingTradesByUser(user);
            // "Options:"
            for (int i = 1; i < 5; i++) {
                menuPresenter.printMenu(22, i);
            }
            // "(1) Exit this menu"
            // "===================="
            // "Your pending trades:"
            for (Trade trade : userTrades) {
                menuPresenter.printTradeToString(userManager, trade);
            }
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            if (choice != 1){
                // "Your choice was not valid, please pick a valid choice."
                menuPresenter.printMenu(22, 6);
            }
        }
    }

    /**
     * Allow user to view their active temporary trade history
     * @param user user logged in
     */
    public void viewActiveTempTrades(MenuPresenter menuPresenter, UserManager userManager,
                                     TradeCreator tradeCreator, User user) {
        int input = -1;
        ArrayList<TemporaryTrade> userTrades = tradeCreator.getTradeHistories().searchActiveTempTradesByUser(user);
        for (int i = 1; i < 4; i++) {
            menuPresenter.printMenu(22, i);
        }
        // "Options:"
        // "(1) Exit this menu"
        // "===================="
        menuPresenter.printMenu(22, 7);
        // "Your active temporary trades:"
        for (Trade trade : userTrades) {
            menuPresenter.printTradeToString(userManager, trade);
        }
        while (input != 1) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
            if (input != 1) {
                // "Your choice was not valid, please pick a valid choice."
                menuPresenter.printMenu(22, 6);
            }
        }

    }

}

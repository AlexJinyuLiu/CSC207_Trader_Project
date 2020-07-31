package controllerpresenterpack;

import alertpack.MessageAlert;
import alertpack.UserAlert;
import entitypack.BrowsingUser;
import entitypack.*;
import usecasepack.*;

import java.util.Scanner;
import java.util.ArrayList;


/**
 * A controller class that dictates what can be done on the Admin's menu.
 */
public class AdminActions implements UserBrowsing{

    /**
     * Starts and calls the presenter class stuff to display the admin main menu and take user input.
     * @param menuPresenter the menuPresenter object to be used to print things.
     * @param adminUser the UseCasePack.AdminUser initialized in the program.
     * @param tradeCreator the UseCasePack.TradeCreator initialized in the program.
     * @param userManager the UseCasePack.UserManager initialized in the program.
     */
    public void runAdminMenu(MenuPresenter menuPresenter, AdminUser adminUser, TradeCreator tradeCreator,
                             UserManager userManager, ItemManager itemManager) {
        boolean running = true;
        while (running) {
            int input = -1;
            Scanner scan = new Scanner(System.in);
            menuPresenter.printMenu(4,0); // Admin Login Menu
            menuPresenter.printMenu(4,1); // (1) Set borrow/lend threshold
            menuPresenter.printMenu(4,2); // (2) Set complete trade threshold
            menuPresenter.printMenu(4,3); // (3) Set incomplete trade threshold
            menuPresenter.printMenu(4,4); // (4) Add new admin
            menuPresenter.printMenu(4, 5);// (5) View current threshold values
            menuPresenter.printMenu(4, 6);// (6) Edit or undo a trade
            menuPresenter.printMenu(4,9);
            menuPresenter.printMenu(4,10);
            menuPresenter.printMenu(4,7); // (0) Quit
            boolean valid_input = false;
            while (!valid_input) {
                input = scan.nextInt();
                if (input > 8 || input < 0) {
                    menuPresenter.printMenu(4,7);
                } else if (input == 1) {
                    changeBorrowLendThreshold(menuPresenter, adminUser, tradeCreator);
                    valid_input = true;
                } else if (input == 2) {
                    changeCompleteThreshold(menuPresenter, adminUser, tradeCreator);
                    valid_input = true;
                } else if (input == 3) {
                    changeIncompleteThreshold(menuPresenter, adminUser, userManager);
                    valid_input = true;
                } else if (input == 4) {
                    addNewAdmin(menuPresenter, adminUser);
                    valid_input = true;
                } else if (input == 5){
                    viewThresholdValues(menuPresenter, userManager, tradeCreator);
                    valid_input = true;
                } else if (input == 6) {
                    //TODO: Finish similar browsing system for trades.
                    editTrade(menuPresenter, userManager, tradeCreator, adminUser, tradeCreator.getTradeHistories(),
                            itemManager);
                    valid_input = true;
                } else if (input == 7) {
                    viewAllUsers(menuPresenter, userManager, itemManager);
                    valid_input = true;
                } else if (input == 8) {
                    searchUser(menuPresenter, userManager, itemManager);
                    valid_input = true;
                } else if (input == 0) {
                    valid_input = true;
                    running = false;
                }
            }
        }
    }


    //TODO: the below method are to be called from input 6.
    public void viewAllUsers(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                             User userViewing, ItemManager itemManager){

    }

    public void viewUser(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                         User userToView, User userViewing,ItemManager itemManager){

    }


    /** Method that takes user input and changes the threshold value (The necessary difference between the number of
     * items users have lent and borrowed before they can make another transaction)
     * @param adminUser UseCasePack.AdminUser logged in making changes
     * @param menuPresenter menu presenter
     * @param tradeCreator creating the trade
     */
    private void changeBorrowLendThreshold(MenuPresenter menuPresenter, AdminUser adminUser,
                                           TradeCreator tradeCreator) {
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "Please enter the new threshold value for lent vs borrowed: "
            menuPresenter.printMenu(6,0);
            menuPresenter.printMenu(6,1);
            input = scan.nextInt();
            if (input > 50 || input < 0) {
                // "Please enter a valid threshold number"
                menuPresenter.printMenu(6,2);
            } else {
                adminUser.changeBorrowLendThreshold(tradeCreator, input);
                flag = false;
            }
        }
    }

    private void changeCompleteThreshold(MenuPresenter menuPresenter, AdminUser adminUser,
                                         TradeCreator tradeCreator) {
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            menuPresenter.printMenu(9,0);
            menuPresenter.printMenu(9,1);
            input = scan.nextInt();
            if (input > 50 || input < 0) {
                menuPresenter.printMenu(9,2);
            } else {
                adminUser.changeCompleteThreshold(tradeCreator, input);
                flag = false;
            }
        }
    }

    private void changeIncompleteThreshold(MenuPresenter menuPresenter, AdminUser adminUser,
                                           UserManager userManager) {
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            menuPresenter.printMenu(34,0);
            menuPresenter.printMenu(34,1);
            input = scan.nextInt();
            if (input > 50 || input < 0) {
                menuPresenter.printMenu(34,2);
            } else {
                adminUser.changeIncompleteThreshold(userManager, input);
                flag = false;
            }
        }
    }

    /** Method that creates additional logins for UseCasePack.AdminUser account
     *
     * @param adminUser UseCasePack.AdminUser logged in making changes
     * @param menuPresenter menu presenter
     */
    private void addNewAdmin(MenuPresenter menuPresenter, AdminUser adminUser) {
        boolean flag = true;
        String inputUsername;
        String inputPassword;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // "Enter the username of the administrator you want to add: "
            menuPresenter.printMenu(7,0);
            menuPresenter.printMenu(7,1);
            inputUsername = scan.next();
            // "Enter the password of the administrator you want to add: "
            menuPresenter.printMenu(7,2);
            inputPassword = scan.next();
            if (adminUser.addLogin(inputUsername, inputPassword)){
                flag = false;
            } else{
                // "That username is taken."
                menuPresenter.printMenu(33,2);
            }
        }
    }

    private void editTrade(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                           AdminUser adminUser, TradeHistories tradeHistories, ItemManager itemManager) {
        boolean flag = true;
        int input = -1;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            menuPresenter.printMenu(44,0);
            menuPresenter.printMenu(44,1);
            menuPresenter.printMenu(44,2);
            menuPresenter.printMenu(44,3);
            input = scan.nextInt();
            if (input > 50 || input < -1) {
                // "Please enter a valid integer"
                menuPresenter.printMenu(44,4);
            } else if (input == 1) {
                // trade lookup by ID
                flag = false;
            } else if (input == 2) {
                ArrayList<Trade> listTrades = new ArrayList<Trade>();
                for (Trade pendingTradeRequest : tradeCreator.getPendingTradeRequests()) {
                    listTrades.add(pendingTradeRequest);
                }
                for (Trade pendingTrade : tradeCreator.getPendingTrades()) {
                    listTrades.add(pendingTrade);
                }
                for (Trade compTrade : tradeHistories.getCompletedTrades()) {
                    listTrades.add(compTrade);
                }
                for (Trade trade : listTrades) {
                    menuPresenter.printTradeToString(itemManager, trade);
                }

                // trade lookup by browsing
                // BrowsingUserActions browse = new BrowsingUserActions();
                // browse.runBrowsingUserMenu(menuPresenter, userManager, tradeCreator, adminUser);
                flag = false;
            } else if (input == 0) {
                // quit
                flag = false;
            }
        }
    }

    private void viewThresholdValues(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator){
        menuPresenter.printMenu(39, 0);
        menuPresenter.printMenu(39, 1, tradeCreator.getBorrowLendThreshold());
        menuPresenter.printMenu(39, 2, tradeCreator.getCompleteThreshold());
        menuPresenter.printMenu(39, 3, userManager.getIncompleteThreshold());
    }

    private void searchUser(MenuPresenter menuPresenter, UserManager userManager, ItemManager itemManager) {
        Scanner scan = new Scanner(System.in);
        menuPresenter.printMenu(45,13);
        String username = scan.nextLine();
        User user = userManager.searchUser(username);
        if (user != null) {
            viewUser(menuPresenter, userManager, user, itemManager);
        }
        else {
            menuPresenter.printMenu(45,14);
        }

    }

    private void viewAllUsers(MenuPresenter menuPresenter, UserManager userManager, ItemManager itemManager){
        boolean handled = false;
        Scanner scan = new Scanner(System.in);
        // "--- View other users ---"
        menuPresenter.printMenu(17, 0);
        // "Enter a number to view a EntityPack.User's page:"
        menuPresenter.printMenu(17, 1);
        int page = 1;
        // MetroArea usersMetro = userManager.getUsersMetro(userViewing);
        // Dunno if we wanna search user by metro in the future
        ArrayList<User> allUsers = userManager.getListUsers();
        boolean nextPageExists = true;
        while (!handled){
            nextPageExists = menuPresenter.printPageOfUsers(page, nextPageExists, allUsers);
            int input = -1;
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
                    viewUser(menuPresenter, userManager, allUsers.get(input - 1), itemManager);
                }
            }
        }
    }

    private void viewUser(MenuPresenter menuPresenter, UserManager userManager, User userToView, ItemManager itemManager) {
        Scanner scan = new Scanner(System.in);

        boolean handled = false;
        int input;
        while(!handled){
            //menuPresenter.printMenu(35, 0, userString.toString());
            if (userToView instanceof TradingUser) {
                //TODO: This might cause a bug if we enter an invalid number
                TradingUser tradingUser = (TradingUser) userToView;
                menuPresenter.printTradingUserToString((TradingUser)userToView, itemManager);
                //StringBuilder userString = new StringBuilder(userToView.toString());
                //userString.append("(1) Send a message\n");
                menuPresenter.printMenu(45, 1);
                //userString.append("(2) Add one of their items to your wishlist\n");
                menuPresenter.printMenu(45, 2);
                if (!tradingUser.getFrozen()) {
                    //userString.append("(3) Send a trade request\n");
                    menuPresenter.printMenu(45, 3);
                }
                //userString.append("(0) Back to Main Menu");
                menuPresenter.printMenu(45, 4);

                input = scan.nextInt();

                if (input < 0 || input > 3 || (tradingUser.getFrozen() && input == 3)){
                    // "Please enter a valid input"
                    menuPresenter.printMenu(45, 5);
                } else if (input == 1) {
                    createMessage(menuPresenter,userManager, userToView);
                } else if (input == 2){
                    menuPresenter.printMenu(45, 8);
                    scan.nextLine();
                    String itemString = scan.nextLine();
                    if (userManager.checkIfUserContain(tradingUser, itemString)) {
                        userManager.removeFromWishList(tradingUser, itemString);
                        menuPresenter.printMenu(45, 9);
                    }
                    else {
                        menuPresenter.printMenu(45, 12);
                    }
                } else if (input == 3){
                    menuPresenter.printMenu(45, 10);
                    scan.nextLine();
                    int itemID = scan.nextInt();
                    if (itemManager.checkIfUserHas(tradingUser, itemID)) {
                        itemManager.removeFromInventory(itemID);
                        menuPresenter.printMenu(45, 11);
                    }
                    else {
                        menuPresenter.printMenu(45, 12);
                    }
                } else if (input == 0){
                    handled = true;

                }
            } else if (userToView instanceof BrowsingUser){
                menuPresenter.printBorrowingOnlyUserToString((BrowsingUser)userToView);
                boolean validInput = false;
                while(!validInput) {
                    menuPresenter.printMenu(45, 1);
                    menuPresenter.printMenu(45, 4);
                    input = scan.nextInt();
                    if (input == 0) {
                        validInput = true;
                        handled = true;
                    } else if (input == 1){
                        createMessage(menuPresenter, userManager, userToView);
                    } else {
                        menuPresenter.printMenu(45,5);
                    }
                }
            }
        }
    }

    private void createMessage(MenuPresenter menuPresenter, UserManager userManager,
                               User userToView){
        Scanner scan = new Scanner(System.in);
        menuPresenter.printMenu(45, 6);
        String message = scan.nextLine();
        UserAlert alert = new MessageAlert("Admin", message);
        userManager.alertUser(userToView.getUsername(), alert);
        menuPresenter.printMenu(45, 7, userToView.getUsername());
    }
}

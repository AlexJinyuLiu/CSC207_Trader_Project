package controllerpresenterpack;

import entitypack.BrowsingUser;
import entitypack.MetroArea;
import entitypack.TradingUser;
import entitypack.User;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class BrowsingUserActions implements UserBrowsing {
    //TODO: Create the BrowsingOnlyUser menu from here.

    public void runBrowsingUserMenu(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
    BrowsingUser user){
        mainMenu(menuPresenter, userManager, tradeCreator, user);
    }

    /**
     * Display the main menu to user, take user's input to implement the corresponding action
     * @param user user logged in making changes or viewing statuses
     */
    private void mainMenu(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                          BrowsingUser user){
        int numOptions = 1;
        boolean running = true;
        while (running) {
            int input = -1;
            Scanner scan = new Scanner(System.in);
            for (int i = 0; i < 3; i++) {
                menuPresenter.printMenu(43, i);
            }
            boolean valid_input = false;
            while(!valid_input){
                //"Please enter a number corresponding to a setting above:\n"
                menuPresenter.printMenu(43, 3);
                input = scan.nextInt();
                if (input > 1 || input < 0) {
                    //"Please enter a number from 0 to "
                    menuPresenter.printMenu(43, 4, numOptions);
                } else if (input == 1) {
                    viewAllUsers(menuPresenter, userManager, tradeCreator, user);
                    valid_input = true;
                } else if (input == 0){
                    valid_input = true;
                    running = false;
                }
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
        MetroArea usersMetro = userManager.getUsersMetro(userViewing);
        ArrayList<User> allUsers = userManager.searchUsersByMetro(usersMetro);
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
    public void viewUser(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                          User userToView, User userViewing) {
        Scanner scan = new Scanner(System.in);

        boolean handled = false;
        int input;
        while(!handled){
            //menuPresenter.printMenu(35, 0, userString.toString());
            if (userToView instanceof TradingUser) {
                //TODO: This might cause a bug if we enter an invalid number
                TradingUser tradingUser = (TradingUser) userToView;
                menuPresenter.printTradingUserToString((TradingUser)userToView);
                //StringBuilder userString = new StringBuilder(userToView.toString());
                //userString.append("(1) Send a message\n");
                menuPresenter.printMenu(18, 1);
                //userString.append("(0) Back to Main Menu");
                menuPresenter.printMenu(18, 4);

                input = scan.nextInt();

                if (input < 0 || input > 1){
                    // "Please enter a valid input"
                    menuPresenter.printMenu(18, 5);
                } else if (input == 1) {
                    createMessage(menuPresenter, userManager, userToView, (BrowsingUser)userViewing);
                } else if (input == 0){
                    handled = true;

                }
            } else if (userToView instanceof BrowsingUser){
                menuPresenter.printBorrowingOnlyUserToString((BrowsingUser)userToView);
                boolean validInput = false;
                while(!validInput) {
                    menuPresenter.printMenu(18,4);
                    input = scan.nextInt();
                    if (input == 0){
                        validInput = true;
                        handled = true;
                    } else{
                        menuPresenter.printMenu(18,5);
                    }
                }
            }
        }
    }

    private void createMessage(MenuPresenter menuPresenter, UserManager userManager,
                               User userToView, BrowsingUser userViewing){
        Scanner scan = new Scanner(System.in);
        menuPresenter.printMenu(18, 6);
        String message = scan.nextLine();
        userManager.sendMessageToUser(userViewing.getUsername(), userToView, message);
        menuPresenter.printMenu(18, 7, userToView.getUsername());
    }
}

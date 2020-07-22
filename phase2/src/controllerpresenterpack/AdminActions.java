package controllerpresenterpack;

import usecasepack.AdminUser;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.util.Scanner;


/**
 * A controller class that dictates what can be done on the Admin's menu.
 */
public class AdminActions {

    /**
     * Starts and calls the presenter class stuff to display the admin main menu and take user input.
     * @param menuPresenter the menupresenter object to be used to print things.
     * @param adminUser the UseCasePack.AdminUser initialized in the program.
     * @param tradeCreator the UseCasePack.TradeCreator initialized in the program.
     * @param userManager the UseCasePack.UserManager initialized in the program.
     */
    public void runAdminMenu(MenuPresenter menuPresenter, AdminUser adminUser, TradeCreator tradeCreator,
                             UserManager userManager) {
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
            menuPresenter.printMenu(4,7); // (0) Quit
            boolean valid_input = false;
            while (!valid_input) {
                input = scan.nextInt();
                if (input > 6 || input < 0) {
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
                    editTrade(menuPresenter);
                    valid_input = true;
                } else if (input == 0) {
                    valid_input = true;
                    running = false;
                }
            }
        }
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

    private void editTrade(MenuPresenter menuPresenter) {
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
                // trade lookup by user
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
}

import java.util.Scanner;




public class AdminActions {

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
            menuPresenter.printMenu(4,5); // (0) Quit
            boolean valid_input = false;
            while (!valid_input) {
                input = scan.nextInt();
                if (input > 4 || input < 0) {
                    menuPresenter.printMenu(4,4);
                    // System.out.println("Please enter a number from 0 to 2");
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
                } else if (input == 0) {
                    valid_input = true;
                    running = false;
                }
            }
        }
    }

    /** Method that takes user input and changes the threshold value (The necessary difference between the number of
     * items users have lent and borrowed before they can make another transaction)
     * @param adminUser AdminUser logged in making changes
     * @param menuPresenter menu presenter
     * @param tradeCreator creating the trade
     */
    protected void changeBorrowLendThreshold( MenuPresenter menuPresenter, AdminUser adminUser,
                                              TradeCreator tradeCreator) {
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("Please enter the new threshold value for lent vs borrowed: ");
            menuPresenter.printMenu(6,0);
            menuPresenter.printMenu(6,1);
            input = scan.nextInt();
            if (input > 50 || input < 0) {
                // System.out.println("Please enter a valid threshold number");
                menuPresenter.printMenu(6,2);
            } else {
                adminUser.changeBorrowLendThreshold(tradeCreator, input);
                flag = false;
            }
        }
    }

    protected void changeCompleteThreshold(MenuPresenter menuPresenter, AdminUser adminUser,
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

    protected void changeIncompleteThreshold(MenuPresenter menuPresenter, AdminUser adminUser,
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

    /** Method that creates additional logins for AdminUser account
     *
     * @param adminUser AdminUser logged in making changes
     * @param menuPresenter menu presenter
     */
    protected void addNewAdmin(MenuPresenter menuPresenter, AdminUser adminUser) {
        boolean flag = true;
        String inputUsername;
        String inputPassword;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("Enter the username of the administrator you want to add: ");
            menuPresenter.printMenu(7,0);
            menuPresenter.printMenu(7,1);
            inputUsername = scan.next();
            // System.out.println("Enter the password of the administrator you want to add: ");
            menuPresenter.printMenu(7,2);
            inputPassword = scan.next();
            if (adminUser.addLogin(inputUsername, inputPassword)){
                flag = false;
            } else{
                // System.out.println("That username is taken.");
                menuPresenter.printMenu(33,2);
            }
        }
    }
}

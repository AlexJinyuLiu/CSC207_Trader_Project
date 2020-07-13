import AlertPack.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminAlertManager { //This class has a two way dependency with TradeSystem

    /** Method which iterates through each AdminAlert and handles it.
     *
     * @param alerts Array List of AdminAlerts that need to be processed.
     */
    public void handleAlertQueue(MenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager,
                                 TradeCreator tradeCreator, ArrayList<AdminAlert> alerts){

        while(!(alerts.size() == 0)){
            AdminAlert alert = alerts.get(0);
            handleAlert(menuPresenter, adminUser, userManager, tradeCreator, alert);
            alerts.remove(0);
        }

    }

    /** Method which sends each AdminAlert to the correct function to be handled based on its type
     *
     * @param a AdminAlert object to be handled
     */
    private void handleAlert(MenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager,
                             TradeCreator tradeCreator, AdminAlert a){
        if (a instanceof ItemValidationRequestAlert){
                handleItemValidationRequestAlert(menuPresenter, adminUser, userManager,
                        (ItemValidationRequestAlert) a);
        } else if (a instanceof ReportAlert){
            handleReportAlert(menuPresenter, adminUser, userManager, tradeCreator, (ReportAlert) a);
        } else if (a instanceof FreezeUserAlert){
            handleFreezeUserAlert(menuPresenter, adminUser, userManager,(FreezeUserAlert) a);
        }else if (a instanceof UnfreezeRequestAlert){
            handleUnfreezeRequestAlert(menuPresenter, userManager, adminUser, (UnfreezeRequestAlert) a);
        }
    }

        //Each alert needs a handle method for its type, which prints/takes input and calls corresponding functions to
        //  handle the alert on the enduser side of things. See google doccument for specifics on alerts and their
        //  handling process.

    /** Method that handles an ItemValidationRequestAlert by approving or denying the request
     *
     * @param alert AdminAlert that there is an ItemValidationRequestAlert to be handled
     */
    private void handleItemValidationRequestAlert(MenuPresenter menuPresenter, AdminUser adminUser,
                                                  UserManager userManager, ItemValidationRequestAlert alert){
        // System.out.println("Item validation request\nUser: " + alert.getOwner() + "\nItem name: " + alert.getName() +
        //         "\nItem description: " + alert.getDescription() + "\nItem ID number: " + alert.getItemID());
        // TODO add in the alerts
        menuPresenter.printMenu(0,0);
        menuPresenter.printMenu(0,1) ;
        menuPresenter.printMenu(0,2);
        menuPresenter.printMenu(0,3);
        menuPresenter.printMenu(0,4);

        Scanner scanner = new Scanner(System.in);
        String message;
        // System.out.println("(1) Approve this item");
        menuPresenter.printMenu(0,5);
        // System.out.println("(2) Deny this item");
        menuPresenter.printMenu(0,6);
        int choice = optionChoice(menuPresenter, 2);
        if (choice == 2){
            // System.out.println("Please enter a reason why this request was declined.");
            menuPresenter.printMenu(0,7);
            message = scanner.next();
        }else{
            message = "";
        }
        adminUser.pollValidationRequest(userManager,choice == 1, alert, message);
    }

    /** Method that handles a ReportAlert by accepting the report or dismissing it
     *
     * @param alert AdminAlert that there is a ReportAlert to be handled
     */
    private void handleReportAlert(MenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager,
                                   TradeCreator tradeCreator,
                                   ReportAlert alert){
        menuPresenter.printMenu(13,0); // Handle Report Alert
        // TODO change this
        System.out.println(alert.getSenderUserName() + " has reported user " + alert.getReportedUserName() +
                " whose trade status is " + alert.getIsTradeComplete()
                + "\n" + "Details: " + alert.getReportDescription());

        boolean flag = true;
        int input = 0;
        int numIncompTrades = 0;
        int threshold = 0; // threshold of incomplete trades
        while (flag){
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Accept report");
            menuPresenter.printMenu(13,1);
            // System.out.println("(2) Dismiss");
            menuPresenter.printMenu(13,2);
            input = scan.nextInt();
            if (input == 1){
                userManager.increaseUserIncompleteTrades(userManager.searchUser(alert.getReportedUserName()));
                int numIncompleteTrades = tradeCreator.getNumIncompTrades(alert.getReportedUserName());
                threshold = userManager.getIncompleteThreshold();
                if (numIncompleteTrades > threshold){
                    User reportedUser = userManager.searchUser(alert.getReportedUserName());
                    adminUser.freezeUser(userManager, reportedUser);
                }
                flag = false;
            }
            if (input == 2){
                flag = false;
            }
        }
    }

    /** Method that handles a FreezeUserAlert by freezing the user or dismissing the alert
     *
     * @param alert AdminAlert that there is a user that should be frozen
     * @param menuPresenter menu presenter for the output statements
     * @param adminUser for freezing the user
     * @param userManager for searching the username of the user to be frozen
     */
    private void handleFreezeUserAlert(MenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager,
                                       FreezeUserAlert alert){
        menuPresenter.printMenu(14,0); // Freeze User Alert
        // TODO change this
        System.out.println("Freeze User Alert" +
                "\n" + alert.getUsername() + " has lent: " + alert.getLent() + " items" +
                "\n" + alert.getUsername() + " has borrowed: " + alert.getBorrowed() + " items" +
                "\n" + "Required to lend " + alert.getThresholdRequired() + " more items than borrowed");
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Freeze User");
            menuPresenter.printMenu(14,1);
            // System.out.println("(2) Dismiss");
            menuPresenter.printMenu(14,2);
            input = scan.nextInt();
            if (input == 1) {
                User user = userManager.searchUser(alert.getUsername());
                assert user != null;
                adminUser.freezeUser(userManager, user);
                flag = false;
            }
            if (input == 2) flag = false;
        }
    }

    /** Method that handles a UnfreezeUserRequestAlert by unfreezing the user that requested the unfreeze of dismissing
     * the alert
     *
     * @param alert AdminAlert that there is a user who has requested that their account be unfrozen
     * @param menuPresenter menu presenter for the output statements
     * @param userManager user manager to find the user's username
     * @param adminUser admin user to unfreeze account if needed
     */
    private void handleUnfreezeRequestAlert(MenuPresenter menuPresenter, UserManager userManager, AdminUser adminUser,
                                            UnfreezeRequestAlert alert){
        menuPresenter.printMenu(15,0);
        // TODO change this
        System.out.println("Unfreeze User Request Alert" +
                "\n" + alert.getUsername() + " has lent: " + alert.getLent() + " items" +
                "\n" + alert.getUsername() + " has borrowed: " + alert.getBorrowed() + " items" +
                "\n" + "Required to lend " + alert.getThresholdRequired() + " more items than borrowed");
        // author: Callan Murphy
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            // System.out.println("(1) Unfreeze User");
            menuPresenter.printMenu(15,1);
            // System.out.println("(2) Dismiss");
            menuPresenter.printMenu(15,2);
            input = scan.nextInt();
            if (input == 1) {
                User user = userManager.searchUser(alert.getUsername());
                adminUser.unfreezeAccount(user);
                flag = false;
            }
            if (input == 2) flag = false;
        }
    }

    /** Helper method that checks user input to ensure that they made a valid choice (options are between 1 and x)
     *
     * @param x user can input any value from 1 to x (inclusive)
     * @param menuPresenter menu presenter for the output statements
     * @return int
     */
    //helper method to ensure the user picks a valid choice, options are between 1 and x - Louis
    private int optionChoice(MenuPresenter menuPresenter, int x){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter one of the numbers listed above");
        menuPresenter.printMenu(6,1);
        int choice = scanner.nextInt();
        while(choice >= x || choice < 0){
            // TODO change this to include +x
            // System.out.println("The number you entered was not listed above. Please enter a choice between 1 and " + x);
            menuPresenter.printMenu(6,2);
            choice = scanner.nextInt();
        }
        return choice;


    }
}



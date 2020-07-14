import AlertPack.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
// this should have a builder; now everything in here is private and you can just call the builder
public class TradeSystem {

    private AdminUser adminUser;
    private UserManager userManager;
    private TradeCreator tradeCreator;
    private MenuPresenter menuPresenter = new MenuPresenter();

    private UserAlertManager userAlertManager = new UserAlertManager();

    private AdminAlertManager adminAlertManager = new AdminAlertManager();
    private AdminActions adminActions = new AdminActions();
    private UserActions userActions = new UserActions();

    public TradeSystem(){}

    public void run() {
        File directory = new File("data");
        if (! directory.exists()) {
            directory.mkdir();
        }

        if (!((new File("data/adminUser.ser"))).exists()) {
            createAdminUser();
        }
        if (!((new File("data/userManager.ser"))).exists()) {
            createUserManager();
        }
        if (!((new File("data/tradeCreator.ser"))).exists()){
            createTradeCreator();
        }

        adminUser = FileManager.loadAdminUser();
        userManager = FileManager.loadUserManager();
        tradeCreator = FileManager.loadTradeCreator();

        onStartUp();

        Scanner scan = new Scanner(System.in);
        User loggedIn = null;
        boolean isAdmin = false;
        for (int i = 0; i < 5; i++){
            menuPresenter.printMenu(10, i);
        }

        int input = optionChoice(3);

        if (input == 1) {
            loggedIn = createAccount();
        } else if (input == 2) {
            User x = login();
            if (x == null) return;
            loggedIn = x;
        } else if (input == 3) {
            isAdmin = adminLogin();
            if (!isAdmin) return;
        } else if (input == 0) {
            return;
        }


//buildAdminUser method
        if (isAdmin) {
            ArrayList<AdminAlert> adminAlerts = adminUser.getAdminAlerts();
            adminAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, adminAlerts);
            //TODO: Ensure the alert queue is depleted after all are handled.
            adminActions.runAdminMenu(menuPresenter, adminUser, tradeCreator, userManager);
        } else {
            ArrayList<UserAlert> userAlerts = userManager.getUserAlerts(loggedIn.getUsername());
            userAlertManager.handleAlertQueue(menuPresenter, userManager, tradeCreator, userAlerts);
            userActions.runUserMenu(menuPresenter, userManager, tradeCreator, loggedIn);
        }

        FileManager.saveAdminToFile(adminUser);
        FileManager.saveTradeCreatorToFile(tradeCreator);
        FileManager.saveUserManagerToFile(userManager);

    }

//in main:
//
//    FileManager fm = new FileManager();
//    TradeSystemBuilder tsb = TradeSystemBuilder(fm);
//    tsb.buildUserManager();
//    ...
//    TradeSystem ts = tsb.getTradeSystem();
//    ts.run();

//   or
//   FileManager fm = new FileManager();
//   TradeSystem ts = TradeSystem(fm);
//   ts.build();
//   rs.run()

    public void createAdminUser(){
        AdminUser adminUser = new AdminUser("admin", "admin");
        FileManager.saveAdminToFile(adminUser);
    }

    public void createUserManager(){
        UserManager userManager = new UserManager();
        FileManager.saveUserManagerToFile(userManager);
    }

    public void createTradeCreator(){
        TradeCreator tradeCreator = new TradeCreator();
        FileManager.saveTradeCreatorToFile(tradeCreator);
    }

    protected void onStartUp(){
        adminUser.onStartUp(userManager, tradeCreator);
        userManager.onStartUp(tradeCreator);
        tradeCreator.tradeHistories.checkForExpiredTempTrades();
    }

    public User createAccount(){  // does not check that the username is taken
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                //"Enter your desired username"
                menuPresenter.printMenu(1, 1);
                String inputUsername = scan.nextLine();
                //"Enter your desired password"
                menuPresenter.printMenu(1, 2);
                String password = scan.nextLine();
                return userManager.createUser(menuPresenter, inputUsername, password);
            } catch (UserNameTakenException e) {
                //"Username taken, try again"
                menuPresenter.printMenu(1, 3);
            }
        }
    }

    /**
     *
     * @return method which allows the user to login to their account.
     */
    public User login(){
        User user = takeUsername();
        if (user != null && takePassword(user)){
            return user;
        }
        return null;
    }

    private boolean adminLogin(){
        Scanner scanner = new Scanner(System.in);
        menuPresenter.printMenu(2, 3);
        menuPresenter.printMenu(2, 1);
        String username = scanner.nextLine();
        menuPresenter.printMenu(3, 1);
        String password = scanner.nextLine();

        return adminUser.isValidUsername(username) && takeAdminPassword(password);
    }

    private User takeUsername(){
        Scanner scanner = new Scanner(System.in);
        User user = null;
        while(user == null) {
            //"Enter your username:"
            menuPresenter.printMenu(2, 1);
            String username = scanner.nextLine();
            user = userManager.searchUser(username);
            if (user == null){
                //"Username was not valid. Please try again or enter 0 to return to the main menu."
                menuPresenter.printMenu(2, 2);
            }
        }
        return user;
    }
    private boolean takePassword(User user){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //"Please enter your password:"
            menuPresenter.printMenu(3, 1);
            String pass = scanner.nextLine();
            if (pass.equals("0")){
                return false;
            }
            else if (user.checkPassword(pass)) {
                //"Login successful"
                menuPresenter.printMenu(3, 2);
                return true;
            }else{
                //"Invalid Password. Please try again or enter 0 to return to the main menu."
                menuPresenter.printMenu(3, 3);
            }
        }
    }
    private Boolean takeAdminPassword(String username){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //"Please enter your password:"
            menuPresenter.printMenu(3,1);
            String pass = scanner.nextLine();
            if (pass.equals("0")){
                return false;
            }
            else if (adminUser.checkPassword(username, pass)) {
                //"Logged in as Admin"
                menuPresenter.printMenu(3, 4);
                return true;
            }else{
                //"Invalid Password. Please try again or enter 0 to return to the main menu."
                menuPresenter.printMenu(3, 3);
            }
        }
    }

    protected int optionChoice(int x){
        Scanner scanner = new Scanner(System.in);
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice > x || choice < 0){
            menuPresenter.printMenu(5, 2);
            choice = scanner.nextInt();
        }
        return choice;


    }
}


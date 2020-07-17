import AlertPack.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main TradeSystem class that links everything together. Technically this is a controller class.
 */
public class TradeSystem {

    private AdminUser adminUser;
    private UserManager userManager;
    private TradeCreator tradeCreator;
    private MenuPresenter menuPresenter = new MenuPresenter();

    private UserAlertManager userAlertManager = new UserAlertManager();

    private AdminAlertManager adminAlertManager = new AdminAlertManager();
    private AdminActions adminActions = new AdminActions();
    private UserActions userActions = new UserActions();

    /**
     * Empty constructor. Creates a new instance of TradeSystem.
     */
    public TradeSystem(){}

    /**
     * Runs the program as normal.
     * @throws InputZeroException An exception that occurs when a user attempts to enter "0" in their account info.
     */
    public void run() throws InputZeroException {
        File directory = new File("phase1/data");
        if (! directory.exists()) {
            directory.mkdir();
        }

        if (!((new File("phase1/data/adminUser.ser"))).exists()) {
            createAdminUser();
        }
        if (!((new File("phase1/data/userManager.ser"))).exists()) {
            createUserManager();
        }
        if (!((new File("phase1/data/tradeCreator.ser"))).exists()){
            createTradeCreator();
        }

        adminUser = FileManager.loadAdminUser();
        userManager = FileManager.loadUserManager();
        tradeCreator = FileManager.loadTradeCreator();

        tradeCreator.tradeHistories.checkForExpiredTempTrades();
        tradeCreator.onStartup();

        User loggedIn = null;
        boolean isAdmin = false;
        for (int i = 0; i < 6; i++){
            menuPresenter.printMenu(10, i);
        }

        int input = optionChoice(3);
        try {
            if (input == 1) {
                loggedIn = createAccount();
            } else if (input == 2) {
                User x = login();
                if (x == null) return;
                loggedIn = x;
            } else if (input == 3) {
                isAdmin = adminLogin();
                while (!isAdmin) {
                    isAdmin = adminLogin();
            }
            } else if (input == 0) {
                return;
            }
            } catch (InputZeroException e) {
            run();
        }


//buildAdminUser method
        if (isAdmin) {

            adminUser.onStartUp(userManager, tradeCreator);
            ArrayList<AdminAlert> adminAlerts = adminUser.getAdminAlerts();
            adminAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, adminAlerts);
            adminActions.runAdminMenu(menuPresenter, adminUser, tradeCreator, userManager);
        } else {
            if (loggedIn == null) {
                return;
            }
            userManager.onStartUp(tradeCreator);
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

    private void createAdminUser(){
        AdminUser adminUser = new AdminUser("admin", "admin");
        FileManager.saveAdminToFile(adminUser);
    }

    private void createUserManager(){
        UserManager userManager = new UserManager();
        FileManager.saveUserManagerToFile(userManager);
    }

    private void createTradeCreator(){
        TradeCreator tradeCreator = new TradeCreator();
        FileManager.saveTradeCreatorToFile(tradeCreator);
    }

    private User createAccount(){  // does not check that the username is taken
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                //"Enter your desired username"
                menuPresenter.printMenu(1, 1);
                String inputUsername = scan.nextLine();
                //"Enter your desired password"
                menuPresenter.printMenu(1, 2);
                String password = scan.nextLine();
                return userManager.createUser(inputUsername, password);
            } catch (UserNameTakenException e) {
            } menuPresenter.printMenu(8, 0);
        }
    }

    /**
     *
     * @return method which allows the user to login to their account.
     */
    private User login() throws InputZeroException {
        User user = takeUsername();
        if (user != null && takePassword(user)){
            return user;
        }
        return null;
    }

    private boolean adminLogin() throws InputZeroException {
        Scanner scanner = new Scanner(System.in);
        menuPresenter.printMenu(2, 1);
        String username = scanner.nextLine();
        if (username.equals("0")) {
            throw new InputZeroException();
        }
        menuPresenter.printMenu(3, 1);
        String password = scanner.nextLine();

        return adminUser.isValidUsername(username) && takeAdminPassword(username, password);
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
                menuPresenter.printMenu(8, 1);
            }
        }
        return user;
    }
    private boolean takePassword(User user) throws InputZeroException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //"Please enter your password:"
            menuPresenter.printMenu(3, 1);
            String pass = scanner.nextLine();
            if (pass.equals("0")){
                throw new InputZeroException();
            }
            else if (user.checkPassword(pass)) {
                //"Login successful"
                menuPresenter.printMenu(8, 3);
                return true;
            }else{
                //"Invalid Password. Please try again or enter 0 to return to the main menu."
                menuPresenter.printMenu(3, 3);
            }
        }
    }
    private Boolean takeAdminPassword(String username, String password){
        boolean loggedIn = true;
        while(loggedIn) {
            if (adminUser.checkPassword(username, password)) {
                //"Logged in as Admin"
                menuPresenter.printMenu(3, 4);
                return true;
            }else{
                //"Invalid Password. Please try again or enter 0 to return to the main menu."
                loggedIn = false;
                menuPresenter.printMenu(3, 3);
            }
        }
        return loggedIn;
    }

    private int optionChoice(int x){
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


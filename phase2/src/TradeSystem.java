import alertpack.*;
import controllerpresenterpack.*;
import entitypack.*;
import usecasepack.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main TradeSystem class that links everything together. Technically this is a controller class.
 */
public class TradeSystem {

    private AdminUser adminUser;
    private UserManager userManager;
    private TradeCreator tradeCreator;
    private ItemManager itemManager;
    private MenuPresenter menuPresenter;

    private UserAlertManager userAlertManager = new UserAlertManager();

    private AdminAlertManager adminAlertManager = new AdminAlertManager();
    private AdminActions adminActions = new AdminActions();
    private TradingUserActions tradingUserActions = new TradingUserActions();
    private BrowsingUserActions browsingUserActions = new BrowsingUserActions();

    private String dir = "phase2/data/";

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

        if (!((new File(dir + "adminUser.ser"))).exists()) {
            createAdminUser();
        }
        if (!((new File(dir + "userManager.ser"))).exists()) {
            createUserManager();
        }
        if (!((new File(dir + "tradeCreator.ser"))).exists()){
            createTradeCreator();
        }
        if (!((new File(dir + "itemManager.ser"))).exists()){
            createItemManager();
        }

        setLanguage();
        //ASCII ART
        for (int i = 0; i < 5; i++){
            menuPresenter.printMenu(40, i);
        }

        adminUser = FileManager.loadAdminUser();
        userManager = FileManager.loadUserManager();
        tradeCreator = FileManager.loadTradeCreator();
        itemManager = FileManager.loadItemManager();


        checkForExpiredTempTrades();

        checkForPastDateTrades();
        tradeCreator.onStartup();

        User loggedIn = null;
        boolean isAdmin = false;
        for (int i = 0; i < 6; i++){
            menuPresenter.printMenu(10, i);
        }

        int input = optionChoice(0,3);
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
            ArrayList<AdminAlert> adminAlerts = new ArrayList<AdminAlert>();
            for (Prompt prompt : adminUser.getAdminAlerts()){
                adminAlerts.add((AdminAlert)prompt);
            }
            adminAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, itemManager,
                    adminAlerts);
            adminActions.runAdminMenu(menuPresenter, adminUser, tradeCreator, userManager, itemManager);
        } else {
            if (loggedIn == null) {
                return;
            }
            userManager.onStartUp(tradeCreator);
            ArrayList<UserAlert> userAlerts = new ArrayList<UserAlert>();
            for (Prompt prompt : userManager.getUserAlerts(loggedIn.getUsername())){
                userAlerts.add((UserAlert)prompt);
            }
            userAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, itemManager,
                    userAlerts);
            if (loggedIn instanceof TradingUser) {
                tradingUserActions.runTradingUserMenu(menuPresenter, userManager, tradeCreator, (TradingUser)loggedIn,
                        itemManager);
            } else if (loggedIn instanceof BrowsingUser){
                browsingUserActions.runBrowsingUserMenu(menuPresenter, userManager, tradeCreator,
                        (BrowsingUser)loggedIn, itemManager);
            }
        }

        FileManager.saveAdminToFile(adminUser);
        FileManager.saveTradeCreatorToFile(tradeCreator);
        FileManager.saveUserManagerToFile(userManager);
        FileManager.saveItemManagerToFile(itemManager);

    }


    public void runGUI() throws InputZeroException {
        File directory = new File("phase1/data");
        if (! directory.exists()) {
            directory.mkdir();
        }

        if (!((new File(dir + "adminUser.ser"))).exists()) {
            createAdminUser();
        }
        if (!((new File(dir + "userManager.ser"))).exists()) {
            createUserManager();
        }
        if (!((new File(dir + "tradeCreator.ser"))).exists()){
            createTradeCreator();
        }
        if (!((new File(dir + "itemManager.ser"))).exists()){
            createItemManager();
        }

        setLanguage();
        //ASCII ART
        for (int i = 0; i < 5; i++){
            menuPresenter.printMenu(40, i);
        }

        adminUser = FileManager.loadAdminUser();
        userManager = FileManager.loadUserManager();
        tradeCreator = FileManager.loadTradeCreator();
        itemManager = FileManager.loadItemManager();


        checkForExpiredTempTrades();

        checkForPastDateTrades();
        tradeCreator.onStartup();

        User loggedIn = null;
        boolean isAdmin = false;
        for (int i = 0; i < 6; i++){
            menuPresenter.printMenu(10, i);
        }

        int input = optionChoice(0,3);
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
            ArrayList<AdminAlert> adminAlerts = new ArrayList<AdminAlert>();
            for (Prompt prompt : adminUser.getAdminAlerts()){
                adminAlerts.add((AdminAlert)prompt);
            }
            adminAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, itemManager,
                    adminAlerts);
            adminActions.runAdminMenu(menuPresenter, adminUser, tradeCreator, userManager, itemManager);
        } else {
            if (loggedIn == null) {
                return;
            }
            userManager.onStartUp(tradeCreator);
            ArrayList<UserAlert> userAlerts = new ArrayList<UserAlert>();
            for (Prompt prompt : userManager.getUserAlerts(loggedIn.getUsername())){
                userAlerts.add((UserAlert)prompt);
            }
            userAlertManager.handleAlertQueue(menuPresenter, adminUser, userManager, tradeCreator, itemManager,
                    userAlerts);
            if (loggedIn instanceof TradingUser) {
                tradingUserActions.runTradingUserMenu(menuPresenter, userManager, tradeCreator, (TradingUser)loggedIn,
                        itemManager);
            } else if (loggedIn instanceof BrowsingUser){
                browsingUserActions.runBrowsingUserMenu(menuPresenter, userManager, tradeCreator,
                        (BrowsingUser)loggedIn, itemManager);
            }
        }

        FileManager.saveAdminToFile(adminUser);
        FileManager.saveTradeCreatorToFile(tradeCreator);
        FileManager.saveUserManagerToFile(userManager);
        FileManager.saveItemManagerToFile(itemManager);

    }

    /**
     * Sets language of the program
     */
    private void setLanguage(){
        System.out.println("Choose Language:");
        System.out.println("(1) English ");
        System.out.println("(2) Francais");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        while(choice < 1 || choice > 2){
            System.out.println("Enter number from 1-2");
            choice = scan.nextInt();
        }
        if (choice == 1) {
            menuPresenter = new MenuPresenter("English");
        } else if (choice == 2){
            menuPresenter = new MenuPresenter("French");
        }
    }

    /**
     * Checks for any expired temporary trades, and then alerts the users involved that they must return their items.
     */
    private void checkForExpiredTempTrades(){
        ArrayList<TemporaryTrade> expiredTempTrade = tradeCreator.getTradeHistories().fetchExpiredTempTrades();

        for(TemporaryTrade tempTrade : expiredTempTrade) {
            LocalDateTime dueDate = tempTrade.getDueDate();
            int tradeID = tempTrade.getTradeID();

            if (!tempTrade.getUser1ItemReturnRequestAccepted()) {
                UserAlert alert = new ExpirationAlert(dueDate, tempTrade.getUsername1(), tradeID);
                tradeCreator.alertUser(tempTrade.getUsername1(), alert);
            }
            if (!tempTrade.getUser2ItemReturnRequestAccepted()) {
                UserAlert alert = new ExpirationAlert(dueDate, tempTrade.getUsername2(), tradeID);
                tradeCreator.alertUser(tempTrade.getUsername2(), alert);
            }
        }
    }

    /**
     * Checks for trades that have passed and alerts users that they must confirm whether or not the trade has happened.
     */
    private void checkForPastDateTrades(){
        ArrayList<Trade> pastDateTrades = tradeCreator.fetchPastDateTrades();
        for (Trade trade : pastDateTrades) {
            UserAlert alertUser1 = new TradePastDateAlert(trade.getTimeOfTrade(), trade.getUsername1(),
                    trade.getTradeID());
            UserAlert alertUser2 = new TradePastDateAlert(trade.getTimeOfTrade(), trade.getUsername2(),
                    trade.getTradeID());
            tradeCreator.alertUser(trade.getUsername1(), alertUser1);
            tradeCreator.alertUser(trade.getUsername2(), alertUser2);
            trade.setUsersAlertedToPastDue(true);
        }
    }

//in main:
//
//    ControllerPresenterPack.FileManager fm = new ControllerPresenterPack.FileManager();
//    TradeSystemBuilder tsb = TradeSystemBuilder(fm);
//    tsb.buildUserManager();
//    ...
//    TradeSystem ts = tsb.getTradeSystem();
//    ts.run();

//   or
//   ControllerPresenterPack.FileManager fm = new ControllerPresenterPack.FileManager();
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

    private void createItemManager(){
        ItemManager itemManager = new ItemManager();
        FileManager.saveItemManagerToFile(itemManager);
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
                menuPresenter.printMenu(1, 3);
                boolean valid_input = false;
                String YNInput;
                User user = null;
                while(!valid_input){
                    YNInput = scan.nextLine();
                    if (YNInput.equals("Y")){
                        user = userManager.createBrowsingUser(inputUsername, password);
                        valid_input = true;
                    } else if (YNInput.equals("N")){
                        user = userManager.createTradingUser(inputUsername, password);
                        valid_input = true;
                    } else{
                        menuPresenter.printMenu(1, 4);
                    }
                }
                pickCity(inputUsername);
                return user;
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

    private int optionChoice(int y, int x){
        Scanner scanner = new Scanner(System.in);
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice > x || choice < y){
            menuPresenter.printMenu(5, 2);
            choice = scanner.nextInt();
        }
        return choice;
    }

    private void pickCity(String username){
        for(int i = 0; i < 4; i++){
            menuPresenter.printMenu(42, i);
        }
        int choice = optionChoice(1, 3);
        MetroArea city = null;
        if (choice == 1){
            city =  MetroArea.TORONTO;
        } else if (choice == 2){
            city = MetroArea.VANCOUVER;
        } else if (choice == 3){
            city = MetroArea.OTTAWA;
        }
        userManager.setUserMetro(username, city);
    }
}


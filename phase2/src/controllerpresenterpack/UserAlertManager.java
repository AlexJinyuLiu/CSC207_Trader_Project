package controllerpresenterpack;

import alertpack.*;
import entitypack.TemporaryTrade;
import entitypack.Trade;
import entitypack.TradingUser;
import usecasepack.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A controller class describing the actions a user can take when handling their alerts.
 */
public class UserAlertManager {

    /**
     * Iterate through each alert in alerts, handle it and remove it from the list
     * @param alerts list of alert that are sent in
     */
    public void handleAlertQueue(GuiMenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager, TradeCreator tradeCreator, ItemManager itemManager,
                                 ArrayList<Prompt> alerts){

        while(!(alerts.size() == 0)){
            alerts.get(0).handle(menuPresenter, adminUser, userManager, tradeCreator, itemManager);
            alerts.remove(0);
        }

    }

    /**
     *
     * @param username username who we are querying
     * @param userManager UserManager Object
     * @return List of User alerts
     */
    public ArrayList<Prompt> getUserAlerts(String username, UserManager userManager){
        return  userManager.getUserAlerts(username);
    }







    //helper method to ensure the user picks a valid choice, options are between 1 and x - Louis
    private int optionChoice(MenuPresenter menuPresenter, int x){
        Scanner scanner = new Scanner(System.in);
        // "Please enter one of the numbers listed above"
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice > x || choice < 0){
            // "The number you entered was not listed above. Please enter a choice between 1 and " + x
            menuPresenter.printMenu(5, 2);
            choice = scanner.nextInt();
        }
        return choice;


    }
    //helper method to ensure the user picks a valid choice, options are between 1 and x - Louis
    private int optionChoice(MenuPresenter menuPresenter, int x, int y){
        Scanner scanner = new Scanner(System.in);
        // "Please enter one of the numbers listed above"
        menuPresenter.printMenu(5, 1);
        int choice = scanner.nextInt();
        while(choice > y || choice < x){
            // "The number you entered was not listed above. Please enter a choice between 1 and " + x
            menuPresenter.printMenu(5, 2);
            choice = scanner.nextInt();
        }
        return choice;


    }



}

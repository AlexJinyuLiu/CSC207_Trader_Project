package controllerpresenterpack;

import alertpack.*;
import entitypack.TradingUser;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the handling of admin alerts.
 */
public class AdminAlertManager {

    /** Method which iterates through each AdminAlert and handles it.
     *
     * @param alerts Array List of AdminAlerts that need to be processed.
     */
    public void handleAlertQueue(GuiMenuPresenter menuPresenter, AdminUser adminUser, UserManager userManager,
                                 TradeCreator tradeCreator, ItemManager itemManager, ArrayList<AdminAlert> alerts){

        while(!(alerts.size() == 0)){
            alerts.get(0).handle(menuPresenter, adminUser, userManager, tradeCreator, itemManager);
            alerts.remove(0);
        }

    }

}



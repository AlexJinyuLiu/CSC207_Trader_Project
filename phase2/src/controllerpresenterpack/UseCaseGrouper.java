package controllerpresenterpack;

import entitypack.Trade;
import usecasepack.*;

/**
 * Class to group together our use cases for readability.
 */
public class UseCaseGrouper {

    public AdminUser adminUser;
    public UserManager userManager;
    public TradeCreator tradeCreator;
    public ItemManager itemManager;
    public TradeHistories tradeHistories;


    public UseCaseGrouper(AdminUser adminUser, UserManager userManager,
                          TradeCreator tradeCreator, ItemManager itemManager){
        this.adminUser = adminUser;
        this.userManager = userManager;
        this.tradeCreator = tradeCreator;
        this.itemManager = itemManager;
    }
}

package controllerpresenterpack;

import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

/**
 * Class to group together our use cases for readability.
 */
public class UseCaseGrouper {

    public AdminUser adminUser;
    public UserManager userManager;
    public TradeCreator tradeCreator;
    public ItemManager itemManager;

    public UseCaseGrouper(AdminUser adminUser, UserManager userManager,
                          TradeCreator tradeCreator, ItemManager itemManager){
        this.adminUser = adminUser;
        this.userManager = userManager;
        this.tradeCreator = tradeCreator;
        this.itemManager =itemManager;
    }
}

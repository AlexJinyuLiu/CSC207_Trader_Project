package controllerpresenterpack;

import entitypack.BrowsingUser;
import entitypack.User;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

public interface UserBrowsing {

    void viewAllUsers(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                   User userViewing);
    void viewUser(MenuPresenter menuPresenter, UserManager userManager, TradeCreator tradeCreator,
                  User userToView, User userViewing);
}

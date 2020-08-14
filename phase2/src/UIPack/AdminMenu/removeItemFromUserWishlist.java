package UIPack.AdminMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//have already chosen user
public class removeItemFromUserWishlist {
    private JPanel mainPanel;
    private JLabel menuTitle;
    private JButton Submit;
    private JScrollPane UserItems;
public removeItemFromUserWishlist(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame window){
    TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
}

}

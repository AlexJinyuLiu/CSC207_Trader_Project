package AdminMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//have already chosen user
public class removeItemFromUserWishlist {
    private JPanel mainPanel;
    private JLabel menuTitle;
    private JButton submit;
    private JScrollPane userItems;
public removeItemFromUserWishlist(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame window){
    window.setContentPane(mainPanel);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.pack();
    TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
    menuTitle.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 2));
    submit.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 6));
    window.setVisible(true);

    String[] items = user.getWishlistItemNames().toArray(new String[0]);
    JCheckBox[] boxes = new JCheckBox();


}

}

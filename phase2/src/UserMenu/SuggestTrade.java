package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;
import usecasepack.ItemManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A UI class to suggest a trade to another user
 */
public class SuggestTrade {

    private JPanel mainPanel;
    private JButton coolButton;
    private JLabel myStuffTheyWantLabel;
    private JLabel theirStuffIwantLabel;
    private JLabel myStuffTheyWantList;
    private JLabel theirStuffIwantList;


    /**
     * Constructs the interface to suggest a trade
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param activeUser the current user's username
     * @param userToView the recipient user of the trade suggestion
     * @param frame the main window displayed to the trading user of the program
     */
    public SuggestTrade(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String activeUser, String userToView,
                        JFrame frame) {
        //TODO: Make sure the text here isn't swapped
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        coolButton.setText(cpg.menuPresenter.getText(Frame.SUGGESTTRADE, 0));
        myStuffTheyWantLabel.setText(cpg.menuPresenter.getText(Frame.SUGGESTTRADE, 1));
        theirStuffIwantLabel.setText(cpg.menuPresenter.getText(Frame.SUGGESTTRADE, 2));

        TradingUser user1 = (TradingUser) useCases.userManager.searchUser(activeUser);
        TradingUser user2 = (TradingUser) useCases.userManager.searchUser(userToView);

        ArrayList<Item> theywantIown = cpg.tradingUserActions.suggestItems(user2, user1, useCases.itemManager);
        ArrayList<Item> IwantTheyown = cpg.tradingUserActions.suggestItems(user1, user2, useCases.itemManager);


        itemLabelConstructor(theywantIown, myStuffTheyWantList);
        itemLabelConstructor(IwantTheyown, theirStuffIwantList);


        coolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewUserMenu viewUserMenu = new ViewUserMenu(useCases, cpg,activeUser, userToView, frame,
                        true, true);
            }
        });
    }

    private void itemLabelConstructor(ArrayList<Item> items, JLabel label){
        StringBuilder s = new StringBuilder();
        s.append("<html>");
        for (Item item: items){
            s.append(item.getName() +" ("+ item.getId()+")" + "<br/>");
            s.append("     - " + item.getDescription() + "<br/>");
        }
        s.append("<html>");

        label.setText(s.toString());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
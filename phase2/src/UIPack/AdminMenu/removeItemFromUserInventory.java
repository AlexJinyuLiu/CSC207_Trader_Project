package UIPack.AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * a UI class that allows an admin to remove an item from a user's inventory
 */
public class removeItemFromUserInventory {
    private JPanel mainPanel;
    private JLabel menuTitle;
    private JScrollPane userItems;
    private JButton submitButton;
    private JTextField itemToRemoveField;
    private JLabel removeItemText;
    private JButton backButton;
    private JTextPane itemTextPane;

    /**
     * creates the interface for removing an item from a user's inventory
     * @param useCases use case grouper
     * @param cpg controller presenter grouper
     * @param username current user's username
     * @param window the main window displayed to the trading user of the program
     */
    public removeItemFromUserInventory(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username,
                                       JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        menuTitle.setText(cpg.menuPresenter.getText(Frame.REMOVEFROMINVENTORY, 0));
        removeItemText.setText(cpg.menuPresenter.getText(Frame.REMOVEFROMINVENTORY, 1));
        submitButton.setText(cpg.menuPresenter.getText(Frame.REMOVEFROMINVENTORY, 2));
        backButton.setText(cpg.menuPresenter.getText(Frame.REMOVEFROMINVENTORY, 3));

        TradingUser user = (TradingUser) useCases.userManager.searchUser(username);

        StringBuilder availItemsString = new StringBuilder(
                cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 0));
        ArrayList<Item> availableItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                username);
        availItemsString.append("\n");
        for (Item item : availableItems){
            availItemsString.append(item.getName()).append(" ID: ").append(item.getId()).append("\n");
        }
        itemTextPane.setText(availItemsString.toString());

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemToRemove = itemToRemoveField.getText();
                if (itemToRemove.equals("")){
                    JOptionPane.showMessageDialog(window,
                            cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 4));
                    return;
                }


                JOptionPane.showMessageDialog(window,
                        cpg.menuPresenter.getText(Frame.ADDNEWADMIN, 7));
                new ViewingUserAsAdmin(useCases, cpg, username, window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewingUserAsAdmin(useCases, cpg, username, window);
            }
        });

    }

}

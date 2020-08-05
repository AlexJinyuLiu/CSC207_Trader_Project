import alertpack.MessageAlert;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewUserMenu {
    private JPanel mainPanel;
    private JButton backButton;
    private JTextPane itemList;
    private JButton createTradeRequestButton;
    private JTextPane userTitle;
    private JTextPane wishList;
    private JButton sendMessageButton;

    public ViewUserMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                        String activeUsername, String userToViewUsername, JFrame frame, boolean isTradingUserViewing) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ArrayList<Integer> availableItemIDs = new ArrayList<Integer>();
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 3));
        createTradeRequestButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 4));
        //TODO: Test this once item validation is working properly.
        if (cpg.tradingUserActions.isTradingUser(useCases.userManager, userToViewUsername)){
            userTitle.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 0) + userToViewUsername + "\n\n" +
                    cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 1));
            ArrayList<Item> availableItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                    userToViewUsername);
            StringBuilder availItemsString = new StringBuilder();
            for (Item item : availableItems){
                availItemsString.append(item.getName() + " ID: " + item.getId() + "\n");
                availableItemIDs.add(item.getId());
            }
            itemList.setText(availItemsString.toString());

            ArrayList<String> wishlistItems = cpg.tradingUserActions.getWishListItems(useCases.userManager,
                    userToViewUsername);
            StringBuilder wishlistItemsString = new StringBuilder();
            for (String wishlistItem : wishlistItems){
                wishlistItemsString.append(wishlistItem + "\n");
            }
            itemList.setText(wishlistItemsString.toString());

        } else {
            userTitle.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 0) + userToViewUsername + "\n\n" +
                    cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 2));
            createTradeRequestButton.setEnabled(false);
        }



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewAllOtherUsersMenu viewOtherUsersMenu = new ViewAllOtherUsersMenu(useCases, cpg, activeUsername,
                        frame, isTradingUserViewing);
            }
        });
        createTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO take each selected item and populate a list of IDs
                //TODO: Stop the user here if they're frozen or inactive.
                if (isTradingUserViewing) {
                    //note: availableItemIDs here is a list of all possible items to select in the trade request, not
                    // the IDs the user already wants to trade for.
                    SendTradeRequestMenu sendTradeRequestMenu = new SendTradeRequestMenu(useCases, cpg, activeUsername,
                            userToViewUsername, frame, availableItemIDs);
                } else{
                    //TODO: Call a guiMenu.txt line saying something like "you cannot send trade requests as a browsing
                    //  only user"
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 5));
                    return;
                }

            }
        });
        /**The button is only for testing now**/
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAlert messageAlert = new MessageAlert("sender", "message");
                useCases.userManager.alertUser(userToViewUsername, messageAlert);
            }
        });
    }
}

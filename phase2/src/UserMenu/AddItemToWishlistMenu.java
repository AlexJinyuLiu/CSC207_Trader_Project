package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.ItemValidationRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddItemToWishlistMenu {
    private JScrollPane userToViewItemsPane;
    private JLabel title;
    private JPanel mainPanel;
    private JButton backButton;

    public AddItemToWishlistMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                 String activeUsername, String userToViewUsername, JFrame frame,
                                 boolean isTradingUserViewing, boolean isUserToViewTrading){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        title.setText(cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 0) + userToViewUsername +
                cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 1));

        JPanel content = new JPanel();

        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        ArrayList<Item> userToViewItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                userToViewUsername);


        int n = 2;
        for (Item item : userToViewItems){

            JTextArea itemText = new JTextArea();
            itemText.setText(item.getName());
            c.gridy = n;
            n++;
            content.add(itemText, c);

            JButton addToWishlistButton = new JButton();
            addToWishlistButton.setText(cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 2));



            addToWishlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cpg.tradingUserActions.addItemToWishlist(useCases.userManager, activeUsername, item.getName())){
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU,
                                3));
                    } else{
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU,
                                4));
                    }

                }
            });
            content.add(addToWishlistButton, c);
        }

        userToViewItemsPane.setViewportView(content);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewUserMenu viewUserMenu = new ViewUserMenu(useCases, cpg, activeUsername, userToViewUsername, frame,
                        isTradingUserViewing, isUserToViewTrading);
            }
        });
    }
}

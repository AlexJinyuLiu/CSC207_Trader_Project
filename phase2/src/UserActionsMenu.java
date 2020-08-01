import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActionsMenu {
    private JButton viewItemsAndWishlistButton;
    private JButton viewUserStatsButton;
    private JButton viewOtherUsersButton;
    private JButton viewPendingTradesButton;
    private JButton setActiveStatusButton;
    private JButton changeMetropolitanAreaButton;
    private JButton requestUnfreezeButton;
    private JPanel mainPanel;

    public UserActionsMenu(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter
        if(english){
            //TODO un hard code below
            viewItemsAndWishlistButton.setText("view items and wishlist");
            viewOtherUsersButton.setText("view other users");
            viewUserStatsButton.setText("view user stats");
            viewPendingTradesButton.setText("view pending trades");
            setActiveStatusButton.setText("set active status");
            changeMetropolitanAreaButton.setText("change metro area");
            requestUnfreezeButton.setText("request unfreeze");
        }else {

        }
        viewItemsAndWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            ViewItemsAndWishlistMenu viewItemsAndWishlistMenu = new ViewItemsAndWishlistMenu(english, user, frame);
            }
        });
        viewUserStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserStatsMenu viewUserStatsMenu = new ViewUserStatsMenu(english, user, frame);
            }
        });
        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(english, user, frame);
            }
        });
        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewPendingTradesMenu viewPendingTradesMenu = new ViewPendingTradesMenu(english, user, frame);
            }
        });
        setActiveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveStatusMenu setActiveStatusMenu = new SetActiveStatusMenu(true, user, frame);
            }
        });
        changeMetropolitanAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveCityMenu setActiveCityMenu = new SetActiveCityMenu(true, user, frame);
            }
        });
        requestUnfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(true){
                    //TODO send the unfreeze request here
                    //TODO replace this with presenter call
                    JOptionPane.showMessageDialog(frame, "Your unfreeze request has been sent to the admin team for review");
                }else {
                    //TODO replace this with a presenter call
                    JOptionPane.showMessageDialog(frame, "You are not currently Frozen");
                }
            }
        });
    }
}

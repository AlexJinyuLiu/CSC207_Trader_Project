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

    public UserActionsMenu(boolean english, User user) {
        JFrame frame = new JFrame("Trade System");
        frame.setBounds(400, 400, 400, 400);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter
        if(english){

        }else {

        }
        viewItemsAndWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            viewItemsAndWishlistMenu viewItemsAndWishlistMenu = new viewItemsAndWishlistMenu(english, user);
            }
        });
        viewUserStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        setActiveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        changeMetropolitanAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveCityMenu setActiveCityMenu = new SetActiveCityMenu(true, user);
                frame.dispose();
            }
        });
        requestUnfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO if the user is frozen call method to request unfreeze
                if(true){
                    //call method here
                    JOptionPane.showMessageDialog(frame, "Your unfreeze request has been sent to the admin team for review");
                }else {
                    JOptionPane.showMessageDialog(frame, "You are not currently Frozen");
                }
            }
        });
    }
}

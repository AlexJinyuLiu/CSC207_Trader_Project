import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SendTradeRequestMenu {
    private JButton sendTradeRequestButton;
    private JButton backButton;
    private JList itemsList;
    private JList wishList;
    private JPanel mainPanel;

    public SendTradeRequestMenu(boolean english, User user, User userToView, JFrame frame, ArrayList<Integer> itemIDsToTrade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String message = "Trade Request sent successfully"; //TODO un hard code this


        //TODO use presenter and use-case to fill in buttons text and message text
        if(english){
            //TODO set message here.
        }else {
            //TODO set message here.

        }

        //TODO use use-cases to fill in the users items and wish list


        sendTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO gather selected items and send the trade request

                JOptionPane.showMessageDialog(frame, message);
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserMenu viewUserMenu = new ViewUserMenu(english, user, userToView, frame);
            }
        });
    }
}

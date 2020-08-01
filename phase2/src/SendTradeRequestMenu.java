import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
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

    public SendTradeRequestMenu(ControllerPresenterGrouper controllerPresenterGrouper, String activeUsername,
                                String userToViewUsername, JFrame frame, ArrayList<Integer> itemIDsToTrade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String message = "Trade Request sent successfully"; //TODO un hard code this


        //TODO use presenter and use-case to fill in buttons text and message text


        //TODO use use-cases to fill in the users items and wish list


        sendTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO gather selected items and send the trade request

                JOptionPane.showMessageDialog(frame, message);
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(controllerPresenterGrouper,
                        activeUsername, frame);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserMenu viewUserMenu = new ViewUserMenu(controllerPresenterGrouper, activeUsername,
                        userToViewUsername, frame);
            }
        });
    }
}

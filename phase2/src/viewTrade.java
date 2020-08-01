import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewTrade {
    private JPanel mainPanel;
    private JLabel user1Label;
    private JLabel user1Value;
    private JLabel user2Label;
    private JLabel user2Value;
    private JLabel itemsToUser1Label;
    private JLabel itemsToUser2Label;
    private JLabel meetingLocationLabel;
    private JList itemsToUser1List;
    private JList itemToUser2List;
    private JLabel meetingLocationValue;
    private JLabel meetingTimeLabel;
    private JLabel meetingTimeValue;
    private JLabel user1AcceptedValue;
    private JLabel user1AcceptedLabel;
    private JLabel user2AcceptedValue;
    private JLabel user2AcceptedLabel;
    private JLabel user1ConfirmedValue;
    private JLabel user1ConfirmedLabel;
    private JLabel user2ConfirmedValue;
    private JLabel user2ConfirmedLabel;
    private JButton backButton;

    public viewTrade(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter
        if(english){

        }else {

        }

        //TODO populate the labels with trade info

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewPendingTradesMenu viewPendingTradesMenu = new ViewPendingTradesMenu(english, user, frame);
            }
        });
    }
}

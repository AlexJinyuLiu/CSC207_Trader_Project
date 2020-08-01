import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUserStatsMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel numberBorrowedLabel;
    private JLabel numberBorrowedAmount;
    private JLabel numberLentLabel;
    private JLabel numberLentAmount;
    private JLabel frozenStatusLabel;
    private JLabel frozenStatusBool;
    private JLabel numIncompleteLabel;
    private JLabel numIncompleteAmount;
    private JLabel weeklyTransactionsLabel;
    private JLabel weeklyTransactionsAmount;
    private JLabel mostRecentlyTradedItemsLabel;
    private JLabel mostRecentlyTradedItemsAmount;
    private JLabel mostFrequentTradingPartnersLabel;
    private JLabel mostFrequentTradingPartnersValue;

    public ViewUserStatsMenu(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO use presenter and use-case to fill in labels
        if(english){

        }else {

        }

        //TODO use use-cases to fill in the stats


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);
            }
        });
    }
}

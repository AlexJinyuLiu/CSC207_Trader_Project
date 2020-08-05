package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import entitypack.TradingUser;
import usecasepack.AdminUser;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptableAlertPrompt {
    private JPanel mainPanel;
    private JButton acceptButton;
    private JButton dismissButton;
    private JLabel descLabel;

    public AcceptableAlertPrompt(int alertType, String desc, String username, GuiMenuPresenter menuPresenter,
                                 UserManager userManager, AdminUser adminUser, TradeCreator tradeCreator) {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        descLabel.setText(desc);
        dismissButton.setText(menuPresenter.getText(Frame.MESSAGEALERT,0));

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // I'm thinking about multiple alerts sharing the same forms while having different constructors (passing different alertTypes)
                // So based on my organization, AcceptableAlert applies to the followings:
                // FreezeUserAlert(String desc, String username, GuiMenuPresenter menuPresenter)
                // ReportAlert
                // UnfreezeRequestAlert - Tingyu

                // Report
                if (alertType == 1) {
                    userManager.increaseUserIncompleteTrades(
                            (TradingUser)userManager.searchUser(username));
                    int numIncompleteTrades =
                            ((TradingUser)userManager.searchUser(username)).getNumIncompleteTrades();
                    int threshold = userManager.getIncompleteThreshold();
                    if (numIncompleteTrades > threshold){
                        TradingUser reportedUser = (TradingUser)userManager.searchUser(username);
                        adminUser.freezeUser(reportedUser);
                        int numBorrowed = reportedUser.getNumBorrowed();
                        int numLent = reportedUser.getNumLent();
                        int incompleteT = reportedUser.getNumIncompleteTrades();
                        FrozenAlert frozenAlert = new FrozenAlert(numBorrowed, numLent, tradeCreator.getBorrowLendThreshold(), incompleteT,
                                userManager.getIncompleteThreshold());
                        userManager.alertUser(reportedUser.getUsername(), frozenAlert);
                    }
                }
                // FreezeUserAlert
                else if (alertType == 2) {
                    TradingUser user = (TradingUser)userManager.searchUser(username);
                    assert user != null;
                    adminUser.freezeUser(user);
                    int numBorrowed = user.getNumBorrowed();
                    int numLent = user.getNumLent();
                    int incompleteT = user.getNumIncompleteTrades();
                    FrozenAlert frozenAlert = new FrozenAlert(numBorrowed, numLent, tradeCreator.getBorrowLendThreshold(),
                            incompleteT, userManager.getIncompleteThreshold());
                    userManager.alertUser(user.getUsername(), frozenAlert);
                }

                else if (alertType == 3) {
                    TradingUser user = (TradingUser)userManager.searchUser(username);
                    adminUser.unfreezeAccount(user);
                }

            }
        });
        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

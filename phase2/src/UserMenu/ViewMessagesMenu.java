package UserMenu;

import AdminMenu.AdminActionsMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewMessagesMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextPane messagePane;

    public ViewMessagesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));

        ArrayList<String> messages;
        StringBuilder messageString = new StringBuilder();
        if (!username.equals("admin")) {
            messages = useCases.userManager.searchUser(username).getMessages();
        }
        else {
            messages = useCases.adminUser.getAdminMessages();
        }
        for (String message : messages) {
            messageString.append(message);
        }
        messagePane.setText(messageString.toString());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.equals("admin")) {
                    if (useCases.userManager.searchUser(username) instanceof TradingUser) {
                        TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, cpg, username, frame);
                    }
                    else {
                        BrowsingUserActionsMenu browsingUserActionsMenu = new BrowsingUserActionsMenu(useCases, cpg, username, frame);
                    }
                }
                else {
                    AdminActionsMenu adminActionsMenu = new AdminActionsMenu(useCases, cpg, frame);
                }
            }
        });
    }
}

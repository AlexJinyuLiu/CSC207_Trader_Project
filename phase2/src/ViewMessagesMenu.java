import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.TradingUserActions;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewMessagesMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JScrollPane messagePane;

    public ViewMessagesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));

        ArrayList<String> messages = useCases.userManager.searchUser(username).getMessages();
        for (String message : messages) {
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(message);
            messagePane.add(generatedLabel);
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}

package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewAdmin {
    private JPanel mainPanel;
    private JLabel addNewAdminTitle;
    private JLabel setAdminUsername;
    private JFormattedTextField newUsername;
    private JLabel setAdminPassword;
    private JPasswordField newPassword;
    private JButton submitButton;
    private JButton backButton;

    public AddNewAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                       JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = newUsername.getSelectedText();
                String password = newPassword.getSelectedText();
                controllerPresenterGrouper.adminActions.addNewLogin(useCases.adminUser, username, password);
            }

        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }
}

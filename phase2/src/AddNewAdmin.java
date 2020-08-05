import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;

public class AddNewAdmin {
    private JPanel mainPanel;
    private JLabel addNewAdminTitle;
    private JLabel setAdminUsername;
    private JFormattedTextField newUsername;
    private JLabel setAdminPassword;
    private JPasswordField newPassword;
    private JButton submitButton;

    public AddNewAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
}}

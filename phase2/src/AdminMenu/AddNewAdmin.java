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

        addNewAdminTitle.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,8));
        setAdminUsername.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,0));
        setAdminPassword.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,1));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,2));
        submitButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,3));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = newUsername.getText();
                String password = new String(newPassword.getPassword());
                controllerPresenterGrouper.adminActions.addNewLogin(useCases.adminUser, username, password);

                char[] passwordCharArr = newPassword.getPassword();
                if (passwordCharArr.length == 0){
                    JOptionPane.showMessageDialog(frame,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN, 5));
                    return;

            }
                JOptionPane.showMessageDialog(frame,
                        controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN, 7));
                AdminActionsMenu adminActionsMenu = new AdminActionsMenu(useCases,
                        controllerPresenterGrouper, frame);

        };

    });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }};

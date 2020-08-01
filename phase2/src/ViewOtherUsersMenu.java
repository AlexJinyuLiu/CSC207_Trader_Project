import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOtherUsersMenu {
    private JComboBox selectUserBox;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton selectUserButton;

    public ViewOtherUsersMenu(ControllerPresenterGrouper controllerPresenterGrouper, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO use presenter and use-case to fill in labels


        //TODO use use-cases to fill in the users

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(controllerPresenterGrouper,
                        username, frame);
            }
        });
        selectUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO Call method to view the selected user
                //TODO: I don't know if this actually returns the user object
                User userToView = (User) selectUserBox.getSelectedItem();
                ViewUserMenu viewUserMenu = new ViewUserMenu(controllerPresenterGrouper, username, userToView.getUsername(), frame);
            }
        });
    }
}

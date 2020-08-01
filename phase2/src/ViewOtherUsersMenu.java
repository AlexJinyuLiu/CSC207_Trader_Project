import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOtherUsersMenu {
    private JComboBox selectUserBox;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton selectUserButton;

    public ViewOtherUsersMenu(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO use presenter and use-case to fill in labels
        if(english){

        }else {

        }

        //TODO use use-cases to fill in the users

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);
            }
        });
        selectUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO Call method to view the selected user
                User userToView = (User) selectUserBox.getSelectedItem();
                ViewUserMenu viewUserMenu = new ViewUserMenu(english, user, userToView, frame);
            }
        });
    }
}

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.BrowsingUser;
import entitypack.Frame;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewAllUsers {
    private JComboBox<String> selectUserBox;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton confirmButton;

    public ViewAllUsers(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, boolean isAdmin, JFrame frame)
    {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        selectUserBox.addItem(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 0));
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 1));
        confirmButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 2));
        //TODO Fix this in AdminActions
        ArrayList<User> allUsers = cpg.adminActions.viewAllUsers(useCases.userManager);
        for (int i = 0; i < allUsers.size(); i++){
            selectUserBox.addItem(allUsers.get(i).getUsername());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }});

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, cpg,
                        (String)selectUserBox.getSelectedItem(), frame);
            }
        });
    }}


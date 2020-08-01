import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import entitypack.User;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewUserMenu {
    private JPanel mainPanel;
    private JButton backButton;
    private JList itemList;
    private JButton createTradeRequestButton;
    private JList wishList;

    public ViewUserMenu(ControllerPresenterGrouper controllerPresenterGrouper, String activeUsername,
                        String userToViewUsername, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO use presenter and use-case to fill in buttons text


        //TODO use use-cases to fill in the users items


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(controllerPresenterGrouper,
                        activeUsername, frame);
            }
        });
        createTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //TODO take each selected item and populate a list of IDs
                ArrayList<Integer> itemIDsToTrade = new ArrayList<Integer>();
                SendTradeRequestMenu sendTradeRequestMenu = new SendTradeRequestMenu(controllerPresenterGrouper,
                        activeUsername, userToViewUsername, frame, itemIDsToTrade);

            }
        });
    }
}

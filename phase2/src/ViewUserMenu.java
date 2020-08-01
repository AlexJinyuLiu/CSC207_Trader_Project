import entitypack.User;

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

    public ViewUserMenu(boolean english, User user, User userToView, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO use presenter and use-case to fill in buttons text
        if(english){

        }else {

        }

        //TODO use use-cases to fill in the users items


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(english, user, frame);
            }
        });
        createTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //TODO take each selected item and populate a list of IDs
                ArrayList<Integer> itemIDsToTrade = new ArrayList<Integer>();
                SendTradeRequestMenu sendTradeRequestMenu = new SendTradeRequestMenu(english, user, userToView, frame, itemIDsToTrade);

            }
        });
    }
}

import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewItemsAndWishlistMenu {
    private JScrollPane itemPane;
    private JPanel mainPanel;
    private JButton createItemValidationRequestButton;
    private JButton backButton;

    public viewItemsAndWishlistMenu(boolean english, User user) {
        JFrame frame = new JFrame("Trade System");
        frame.setBounds(400, 400, 400, 400);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter
        if(english){

        }else {

        }
        createItemValidationRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO open a menu to create an itemValidationRequest
                frame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user);
            }
        });
    }
}

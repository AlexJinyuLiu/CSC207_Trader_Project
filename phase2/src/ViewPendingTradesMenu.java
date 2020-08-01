import entitypack.Trade;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPendingTradesMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;

    public ViewPendingTradesMenu(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter
        if(english){

        }else {

        }

        //TODO populate the drop down box with pending trade IDs


        selectItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int tradeID = (int) pendingTradesBox.getSelectedItem();


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);
            }
        });
    }
}

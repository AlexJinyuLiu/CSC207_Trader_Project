import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setActiveStatusMenu {
    private JComboBox statusBox;
    private JButton setStatusButton;
    private JButton backButton;
    private JPanel mainPanel;

    public setActiveStatusMenu(boolean english, User user) {
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO set the status here
                JOptionPane.showMessageDialog(frame, "Status set successfully");
                frame.dispose();
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user);
            }
        });
        setStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user);
            }
        });
    }
}

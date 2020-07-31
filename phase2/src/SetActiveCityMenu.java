import entitypack.MetroArea;
import entitypack.Trade;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveCityMenu {
    private JPanel mainPanel;
    private JComboBox cities;
    private JButton backButton;
    private JButton setCityButton;


    public SetActiveCityMenu(boolean english, User user, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO call on the presenter to set the city text instead of the enum itself
        for (MetroArea metroArea: MetroArea.values()){
            cities.addItem(metroArea);
        }
        //TODO call on the presenter to set the button text
        if(english){

        }else{

        }
        setCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO set the users city
                JOptionPane.showMessageDialog(frame, "City selected successfully");
                frame.dispose();
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                UserActionsMenu userActionsMenu = new UserActionsMenu(english, user, frame);
            }

        });
    }
}

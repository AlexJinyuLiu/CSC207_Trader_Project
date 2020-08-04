import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.MetroArea;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveCityMenu {
    private JPanel mainPanel;
    private JComboBox cities;
    private JButton backButton;
    private JButton setCityButton;


    public SetActiveCityMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                             String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO call on the presenter to set the city text instead of the enum itself
        for (MetroArea metroArea: MetroArea.values()){
            cities.addItem(metroArea);
        }
        //TODO call on the presenter to set the button text



        setCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO set the users city
                JOptionPane.showMessageDialog(frame, "City selected successfully");
                frame.dispose();
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper,
                        username, frame);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper,
                        username, frame);
            }

        });
    }
}

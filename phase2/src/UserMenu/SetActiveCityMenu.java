package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.MetroArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveCityMenu {
    private JPanel mainPanel;
    private JComboBox cities;
    private JButton backButton;
    private JButton setCityButton;


    public SetActiveCityMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                             String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVECITYMENU, 0));

        setCityButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVECITYMENU, 1));

        for (MetroArea metroArea: MetroArea.values()){
            cities.addItem(metroArea);
        }



        setCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO set the users city

                cpg.tradingUserActions.setUsersCity(useCases.userManager, username,
                        (MetroArea)cities.getSelectedItem());
                JOptionPane.showMessageDialog(frame, "City selected successfully");
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);
            }

        });
    }
}

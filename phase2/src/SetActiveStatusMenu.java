import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveStatusMenu {
    private JComboBox statusBox;
    private JButton setStatusButton;
    private JButton backButton;
    private JPanel mainPanel;

    public SetActiveStatusMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO set the status here
                JOptionPane.showMessageDialog(frame, "Status set successfully");
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper,
                        username, frame);
            }
        });
        setStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper,
                        username, frame);
            }
        });
    }
}

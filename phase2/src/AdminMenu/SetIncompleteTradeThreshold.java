package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;

public class SetIncompleteTradeThreshold {
    private JLabel menuTitle;
    private JLabel enterThreshold;
    private JTextField newThresholdField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel changeIncompleteTradeThresholdLabel;

    public SetIncompleteTradeThreshold(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window){
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

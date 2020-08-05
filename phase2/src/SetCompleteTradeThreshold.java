import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;

public class SetCompleteTradeThreshold {
    private JLabel menuTitle;
    private JLabel enterThreshold;
    private JTextField newThresholdField;
    private JButton submitButton;
    private JPanel mainPanel;

    public SetCompleteTradeThreshold(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window){
        window.setContentPane(mainPanel);
        window.setBounds(450, 450, 500, 400);
        mainPanel.setSize(350, 350);
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
}}

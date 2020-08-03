import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetBorrowLendThreshold {
    private JLabel menuTitle;
    private JLabel enterThreshold;
    private JTextField newThresholdField;
    private JButton submitButton;
    private JPanel mainPanel;

    public SetBorrowLendThreshold(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, boolean isAdmin, JFrame window){
        window.setContentPane(mainPanel);
        window.setBounds(450, 450, 500, 400);
        mainPanel.setSize(350, 350);
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

}}

package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetBorrowLendThreshold {
    private JLabel menuTitle;
    private JFormattedTextField enterThreshold;
    private JTextField newThresholdField;
    private JButton submitButton;
    private JPanel mainPanel;

    public SetBorrowLendThreshold(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window){
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //clicked on setting borrow/lend threshold, redirect  to SetBorrowLendThreshold.java menu
                ;

        int newthres =
                useCases.adminUser.changeBorrowLendThreshold(useCases.tradeCreator, ); }

}}

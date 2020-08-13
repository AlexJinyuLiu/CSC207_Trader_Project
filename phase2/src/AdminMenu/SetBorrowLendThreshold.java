package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that allows the administrative user to set each threshold:
 *  - Max number of complete trades per week
 *  - Max number of incomplete trades per week
 *  - Borrow/lend threshold
 */
public class SetBorrowLendThreshold {
    private JLabel menuTitle;
    private JLabel enterThreshold;
    private JLabel incompleteThreshold;

    private JTextField newIncompletes;
    private JTextField newCompletes;
    private JTextField newThresholdField;


    private JLabel completeThreshold;
    private JButton submitThreshold;
    private JButton submitIncompletes;
    private JButton submitCompletes;
    private JPanel mainPanel;

    /**
     * Constructs the interface to set each threshold
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public SetBorrowLendThreshold(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window){
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();

        menuTitle.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,9));
        enterThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,0));
        int newThresh = Integer.parseInt(newThresholdField.getText());
        submitThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,10));
        incompleteThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,2));
        completeThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,1));
        int incompleted = Integer.parseInt(newIncompletes.getText());
        int completed = Integer.parseInt(newCompletes.getText());
        window.setVisible(true);

        submitIncompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                useCases.adminUser.changeIncompleteThreshold(useCases.userManager, incompleted);
            }
        });

        submitCompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCases.adminUser.changeCompleteThreshold(useCases.tradeCreator, completed);
            }
        });

        submitThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCases.adminUser.changeBorrowLendThreshold(useCases.tradeCreator, newThresh);
            }
        });

}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

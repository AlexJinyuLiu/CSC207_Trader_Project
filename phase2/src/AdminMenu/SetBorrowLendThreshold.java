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
    private JLabel completeThreshold;
    private JTextField newThresholdField;
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
        final int[] newThresh = new int[1];
        final int[] completed = new int[1];
        final int[] incompleted = new int[1];

        menuTitle.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,9));
        enterThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,0));
        newThresholdField.setText(enterThreshold.getText());
        submitThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,10));
        incompleteThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,2));
        completeThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINACTIONSMENU,1));
        newIncompletes.setText(incompleteThreshold.getText());
        newCompletes.setText(completeThreshold.getText());
        window.setVisible(true);

        submitIncompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incompleted[0] = Integer.parseInt(newIncompletes.getText()); //wrapping string as an int
                useCases.adminUser.changeIncompleteThreshold(useCases.userManager, incompleted[0]);
            }
        });

        submitCompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completed[0] = Integer.parseInt(newCompletes.getText()); //wrapping string as an int
                useCases.adminUser.changeCompleteThreshold(useCases.tradeCreator, completed[0]);
            }
        });

        submitThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newThresh[0] = Integer.parseInt(newThresholdField.getText()); //wrapping string as an int
                useCases.adminUser.changeBorrowLendThreshold(useCases.tradeCreator, newThresh[0]);
            }
        });

}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

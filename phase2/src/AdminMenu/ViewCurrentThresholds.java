package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays all the current thresholds of the program
 */
public class ViewCurrentThresholds {
    private JLabel BorrowLendThreshold;
    private JPanel mainPanel;
    private JLabel CompleteThreshold;
    private JLabel BLTnum;
    private JLabel ITTnum;
    private JLabel CTTnum;
    private JButton BackButton;
    private JLabel incompleteTradeThreshold;

    /**
     * Constructs the interface to view all thresholds
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewCurrentThresholds(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 JFrame window) {

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        //TODO: set text of the threshold labels by calling the menuPresenter method
        BLTnum.setText(Integer.toString(useCases.tradeCreator.getBorrowLendThreshold()));
        ITTnum.setText(Integer.toString(useCases.userManager.getIncompleteThreshold()));
        CTTnum.setText(Integer.toString(useCases.tradeCreator.getCompleteThreshold()));

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}

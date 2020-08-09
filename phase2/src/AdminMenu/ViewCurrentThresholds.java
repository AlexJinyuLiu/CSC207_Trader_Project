package AdminMenu;

import AdminMenu.AdminOptions;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCurrentThresholds {
    private JLabel BorrowLendThreshold;
    private JPanel mainPanel;
    private JLabel CompleteThreshold;
    private JLabel BLTnum;
    private JLabel ITTnum;
    private JLabel CTTnum;
    private JButton BackButton;
    private JLabel incompleteTradeThreshold;

    public ViewCurrentThresholds(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 JFrame window) {

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        BLTnum.setText(Integer.toString(useCases.tradeCreator.getBorrowLendThreshold()));
        ITTnum.setText(Integer.toString(useCases.userManager.getIncompleteThreshold()));
        CTTnum.setText(Integer.toString(useCases.tradeCreator.getCompleteThreshold()));

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminOptions adminOptions = new AdminOptions(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}

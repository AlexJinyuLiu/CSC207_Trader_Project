package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTrade {
    private JButton viewPendingTrades;
    private JButton viewConfirmedTradesButton;
    private JButton viewCompletedTradesButton;
    private JButton confirmButton;
    private JButton backButton;
    private JPanel mainPanel;

    public EditTrade(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        viewPendingTrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPendingRequests viewPendingRequests = new ViewPendingRequests(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        viewConfirmedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPendingTrades viewPendingTrades = new ViewPendingTrades(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        viewCompletedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCompletedTrades viewCompletedTrades = new ViewCompletedTrades(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}

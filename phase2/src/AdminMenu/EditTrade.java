package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTrade {
    private JButton viewPendingTradesButton;
    private JButton viewConfirmedTradesButton;
    private JButton viewCompletedTradesButton;
    private JButton backButton;
    private JPanel mainPanel;

    public EditTrade(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {

        viewPendingTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 0));
        viewConfirmedTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 1));
        viewCompletedTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 2));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 3));

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        viewPendingTradesButton.addActionListener(new ActionListener() {
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

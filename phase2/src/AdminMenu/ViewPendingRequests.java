package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPendingRequests {
    private JComboBox pendingRequests;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;

    public ViewPendingRequests(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        for (Trade pendingRequest : useCases.tradeCreator.getPendingTradeRequests())
            pendingRequests.addItem("Trade ID: " + pendingRequest.getTradeID());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTradeToUndo viewTradeToUndo = new ViewTradeToUndo(useCases, controllerPresenterGrouper,
                        "Request", Integer.parseInt(pendingRequests.getSelectedItem().toString().substring(10)),
                        window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTrade editTrade = new EditTrade(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}

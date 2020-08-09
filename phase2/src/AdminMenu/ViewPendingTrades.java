package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ViewPendingTrades {
    private JComboBox pendingTrades;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;

    public ViewPendingTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        for (Trade pendingTrade : useCases.tradeCreator.getPendingTrades()) {
            pendingTrades.addItem("Trade ID: " + pendingTrade.getTradeID());
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTradeToUndo viewTradeToUndo = new ViewTradeToUndo(useCases, controllerPresenterGrouper,
                        "Confirmed", Integer.parseInt(pendingTrades.getSelectedItem().toString().substring(10)),
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

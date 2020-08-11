package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompletedTrades {
    private JComboBox completedTrades;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;

    public ViewCompletedTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        for (Trade completedTrade : useCases.tradeCreator.getTradeHistories().getCompletedTrades())
            completedTrades.addItem("Trade ID: " + completedTrade.getTradeID());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTradeToUndo viewTradeToUndo = new ViewTradeToUndo(useCases, controllerPresenterGrouper,
                        "Completed", Integer.parseInt(completedTrades.getSelectedItem().toString().substring(10)),
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

package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
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
    private JLabel TradeID;

    public ViewPendingTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        confirmButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 0));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 1));
        TradeID.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 2));

        for (Trade pendingTrade : useCases.tradeCreator.getPendingTrades()) {
            pendingTrades.addItem(pendingTrade.getTradeID());
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTradeToUndo viewTradeToUndo = new ViewTradeToUndo(useCases, controllerPresenterGrouper,
                        "Confirmed", (Integer) pendingTrades.getSelectedItem(),
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

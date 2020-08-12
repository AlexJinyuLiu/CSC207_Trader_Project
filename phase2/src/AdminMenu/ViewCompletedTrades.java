package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompletedTrades {
    private JComboBox completedTrades;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel TradeID;

    public ViewCompletedTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        confirmButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 0));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 1));
        TradeID.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWTRADE, 2));

        for (Trade completedTrade : useCases.tradeCreator.getTradeHistories().getCompletedTrades())
            completedTrades.addItem(completedTrade.getTradeID());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTradeToUndo viewTradeToUndo = new ViewTradeToUndo(useCases, controllerPresenterGrouper,
                        "Completed", (Integer) completedTrades.getSelectedItem(),
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

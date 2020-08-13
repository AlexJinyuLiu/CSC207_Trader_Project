package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPendingRequests {
    private JComboBox pendingRequests;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel TradeID;

    public ViewPendingRequests(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        TradeID.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 0));
        confirmButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 1));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 2));


        for (Trade pendingRequest : useCases.tradeCreator.getPendingTradeRequests())
            pendingRequests.addItem(pendingRequest.getTradeID());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRequestToUndo viewRequestToUndo = new ViewRequestToUndo(useCases, controllerPresenterGrouper,
                        (Integer) pendingRequests.getSelectedItem(), window);
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

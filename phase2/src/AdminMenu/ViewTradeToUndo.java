package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTradeToUndo {

    private JPanel mainPanel;
    private JButton undoTradeButton;
    private JButton backButton;
    private JLabel tradeToString;
    private JLabel description;
    private JTextPane undoingARequestWillTextPane;

    public ViewTradeToUndo(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String tradeType, int tradeID, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        description.setText("Trade type: " + tradeType);

        tradeToString.setText(cpg.menuPresenter.printTradeToString(useCases.itemManager, useCases.tradeCreator.searchTrades(tradeID)));

        undoTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpg.adminActions.editTrade(tradeID, tradeType, useCases.adminUser, useCases.tradeCreator,
                        useCases.itemManager, useCases.userManager);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTrade editTrade = new EditTrade(useCases, cpg, window);
            }
        });
    }
}

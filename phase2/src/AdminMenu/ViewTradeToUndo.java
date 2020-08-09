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

    public ViewTradeToUndo(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String tradeType, int tradeID, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        description.setText("Trade type: " + tradeType + ". Undoing a \"Request\" will remove the request " +
                "from request queue of the person it was sent to. Undoing a \"Confirmed\" trade will remove a trade " +
                "from both party's pending trades queue and put it in the sender's request queue for confirmation. " +
                "Undoing a \"Completed\" trade will return the items to their respective owners prior to the trade and " +
                "revert all userStats.");

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

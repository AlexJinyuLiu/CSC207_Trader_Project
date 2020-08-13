package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompletedToUndo {
    private JTextPane tradeToString;
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton backButton;
    private JLabel tradeLabel;

    public ViewCompletedToUndo(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, int tradeID, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        tradeLabel.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 5));
        tradeToString.setText(cpg.menuPresenter.printTradeToString(useCases.itemManager, useCases.tradeCreator.searchTrades(tradeID)));
        undoButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 6));
        backButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 7));

        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cpg.adminActions.editTrade(tradeID, "Completed", useCases.adminUser, useCases.tradeCreator,
                        useCases.itemManager, useCases.userManager);
                JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 8));
                AdminActionsMenu adminActionsMenu = new AdminActionsMenu(useCases, cpg, window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCompletedTrades viewCompletedTrades = new ViewCompletedTrades(useCases, cpg, window);
            }
        });
    }
}

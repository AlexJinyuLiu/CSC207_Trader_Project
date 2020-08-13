package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportMenu {
    private JTextPane reportTextPane;
    private JButton backButton;
    private JButton reportButton;
    private JPanel mainPanel;
    private JLabel reportLabel;

    public ReportMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                      String username, JFrame frame, Trade trade) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 0));
        reportButton.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 14));
        reportLabel.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 16));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ConfirmTradeMenu confirmTradeMenu = new ConfirmTradeMenu(useCases, cpg, username, frame, trade);
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String reportText = reportTextPane.getText();
                cpg.tradingUserActions.messageAdmin(username, reportText, useCases.adminUser);
                TradingUser user1 = (TradingUser) useCases.userManager.searchUser(trade.getUsername1());
                TradingUser user2 = (TradingUser) useCases.userManager.searchUser(trade.getUsername2());
                useCases.tradeCreator.afterReportTrade(useCases.userManager, trade, user1, user2);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 15));
                TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}

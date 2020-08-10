package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewPendingTradeRequestsMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    public ViewPendingTradeRequestsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                              String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        selectItemButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 1));
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;



        int n = 0;
        ArrayList<Trade> trades = controllerPresenterGrouper.tradingUserActions.searchPendingTradeRequestsUser(username,
                useCases.userManager, useCases.tradeCreator);
        for (Trade trade: trades){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(controllerPresenterGrouper.menuPresenter.printTradeToString(useCases.itemManager,
                    trade));
            pendingTradesBox.addItem(trade.getTradeID());
            c.gridy = n;
            n++;
            content.add(generatedLabel, c);
        }

        tradeInfoPane.setViewportView(content);

        selectItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (pendingTradesBox.getSelectedIndex() == -1) {
                    //TODO un-hardcode this
                    JOptionPane.showMessageDialog(frame, "Please Pick a Trade");
                }else{
                    int tradeID = (int) pendingTradesBox.getSelectedItem();
                    AcceptTradeMenu acceptTradeMenu = new AcceptTradeMenu(useCases,controllerPresenterGrouper, username, frame, useCases.tradeCreator.searchTrades(tradeID));
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases,
                        controllerPresenterGrouper, username, frame);
            }
        });
    }
}

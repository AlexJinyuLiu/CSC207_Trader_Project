package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewDeadTradesMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    public ViewDeadTradesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        selectItemButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 1));


        //TODO populate the drop down box with pending trade IDs
        ArrayList<Trade> trades = controllerPresenterGrouper.tradingUserActions.searchDeadTradesUser(username, useCases.userManager, useCases.tradeCreator.getTradeHistories());
        for (Trade trade: trades){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(controllerPresenterGrouper.menuPresenter.printTradeToString(useCases.itemManager,trade));
            pendingTradesBox.addItem(trade.getTradeID());
            tradeInfoPane.add(generatedLabel);
        }

        selectItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (pendingTradesBox.getSelectedIndex() == -1) {
                    //TODO un-hardcode this
                    JOptionPane.showMessageDialog(frame, "Please Pick a Trade");
                }else{
                    int tradeID = (int) pendingTradesBox.getSelectedItem();
                    ViewTrade viewTrade = new ViewTrade(useCases, controllerPresenterGrouper, username, frame);
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

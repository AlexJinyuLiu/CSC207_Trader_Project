package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.TradingUserActions;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TemporaryTrade;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewActiveTemporaryTradesMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    public ViewActiveTemporaryTradesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        selectItemButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 1));


        //TODO populate the drop down box with pending trade IDs
        ArrayList<TemporaryTrade> trades = controllerPresenterGrouper.tradingUserActions.searchCurrentTempTradesUser(username, useCases.userManager, useCases.tradeCreator);
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
                    TemporaryTrade temporaryTrade = (TemporaryTrade) controllerPresenterGrouper.tradingUserActions.searchTrade(useCases.tradeCreator, tradeID);
                    ConfirmReExchangeMenu confirmReExchangeMenu = new ConfirmReExchangeMenu(useCases, controllerPresenterGrouper, username, frame, temporaryTrade);
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

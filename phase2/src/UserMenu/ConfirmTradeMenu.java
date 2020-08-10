package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.TradingUserActions;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmTradeMenu {
    private JLabel tradeIDLabel;
    private JLabel tradeIDValue;
    private JLabel user1Label;
    private JLabel user1Value;
    private JLabel itemsToUser1Label;
    private JLabel itemsToUser2Label;
    private JLabel meetingLocationLabel;
    private JLabel meetingTimeLabel;
    private JLabel user2Label;
    private JLabel user2Value;
    private JList itemsToUser1List;
    private JList itemsToUser2List;
    private JLabel meetingLocationValue;
    private JLabel meetingTimeValue;
    private JButton confirmTradeButton;
    private JButton backButton;
    private JButton reportButton;
    private JLabel user1ConfirmedValue;
    private JLabel user1ConfirmedLabel;
    private JLabel user2ConfirmedValue;
    private JLabel user2ConfirmedLabel;
    private JPanel mainPanel;

    public ConfirmTradeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                            String username, JFrame frame, Trade trade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 0));
        confirmTradeButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 1));
        reportButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 14));

        user1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 2));
        user2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 3));
        itemsToUser1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 4));
        itemsToUser2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 5));
        meetingLocationLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 6));
        meetingTimeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 7));
        user1ConfirmedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 8));
        user2ConfirmedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 9));
        tradeIDLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 12));

        //Should i do this or does this break clean architecture? - Louis
        user1Value.setText(trade.getUsername1());
        user2Value.setText(trade.getUsername2());
        meetingLocationValue.setText(trade.getMeetingPlace());
        meetingTimeValue.setText(trade.getTimeOfTrade().toString());
        Boolean user1ConfirmedBoolean = (Boolean)trade.getUser1TradeConfirmed();
        Boolean user2ConfirmedBoolean = (Boolean)trade.getUser2TradeConfirmed();
        user1ConfirmedValue.setText(user1ConfirmedBoolean.toString());
        user2ConfirmedValue.setText(user2ConfirmedBoolean.toString());
        Integer user1NumRequests = (Integer) trade.getUser1NumRequests();
        Integer user2NumRequests = (Integer) trade.getUser2NumRequests();
        Integer tradeID = (Integer) trade.getTradeID();
        tradeIDValue.setText(tradeID.toString());

        ArrayList<String> itemsToUser1 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser2ToUser1(trade, useCases.itemManager);
        ArrayList<String> itemsToUser2 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser1ToUser2(trade, useCases.itemManager);

        for(String string: itemsToUser1){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(string);
            itemsToUser1List.add(generatedLabel);
        }

        for(String string: itemsToUser2){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(string);
            itemsToUser2List.add(generatedLabel);
        }

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
        confirmTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 13));
                controllerPresenterGrouper.tradingUserActions.confirmTrade(useCases.userManager, useCases.tradeCreator, useCases.itemManager, trade, username);
                TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ReportMenu reportMenu = new ReportMenu(useCases, controllerPresenterGrouper, username, frame, trade);
            }
        });
    }
}

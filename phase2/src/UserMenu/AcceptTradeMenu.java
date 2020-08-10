package UserMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AcceptTradeMenu {
    private JLabel user1Value;
    private JLabel user2Value;
    private JLabel user2Label;
    private JLabel user1Label;
    private JLabel itemsToUser1Label;
    private JLabel itemsToUser2Label;
    private JList itemsToUser1List;
    private JList itemsToUser2List;
    private JLabel meetingLocationLabel;
    private JLabel meetingTimeLabel;
    private JLabel user1AcceptedLabel;
    private JLabel user2AcceptedLabel;
    private JLabel meetingLocationValue;
    private JLabel meetingTimeValue;
    private JLabel user1AcceptedValue;
    private JLabel user2AcceptedValue;
    private JButton backButton;
    private JButton acceptRequestButton;
    private JPanel mainPanel;
    private JLabel tradeIDLabel;
    private JLabel tradeIDValue;
    private JLabel user1EditsMadeLabel;
    private JLabel user2EditsMadeLabel;
    private JButton counterOfferButton;
    private JLabel user1EditsMadeValue;
    private JLabel user2EditsMadeValue;

    public AcceptTradeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                           String username, JFrame frame, Trade trade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        acceptRequestButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 1));

        user1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 2));
        user2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 3));
        itemsToUser1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 4));
        itemsToUser2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 5));
        meetingLocationLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 6));
        meetingTimeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 7));
        user1AcceptedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 8));
        user2AcceptedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ACCEPTTRADEMENU, 9));

        //Should i do this or does this break clean architecture? - Louis
        user1Value.setText(trade.getUsername1());
        user2Value.setText(trade.getUsername2());
        meetingLocationValue.setText(trade.getMeetingPlace());
        meetingTimeValue.setText(trade.getTimeOfTrade().toString());
        Boolean user1AcceptedBoolean = (Boolean)trade.getUser1AcceptedRequest();
        Boolean user2AcceptedBoolean = (Boolean)trade.getUser2AcceptedRequest();
        user1AcceptedValue.setText(user1AcceptedBoolean.toString());
        user2AcceptedValue.setText(user2AcceptedBoolean.toString());
        Integer user1NumRequests = (Integer) trade.getUser1NumRequests();
        Integer user2NumRequests = (Integer) trade.getUser2NumRequests();
        user1EditsMadeValue.setText(user1NumRequests.toString());
        user2EditsMadeValue.setText(user2NumRequests.toString());
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


        acceptRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        counterOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}

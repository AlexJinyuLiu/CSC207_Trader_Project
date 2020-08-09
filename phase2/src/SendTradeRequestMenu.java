import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SendTradeRequestMenu {
    private JButton sendTradeRequestButton;
    private JButton backButton;
    private JList userToViewItems;
    private JList myItems;
    private JPanel mainPanel;
    private JLabel userViewingLabel;
    private JLabel usertoViewLabel;
    private JTextField meetingPlace;
    private JTextField meetingTime;
    private JLabel meetingPlaceLabel;
    private JLabel meetingTimeLabel;


    public SendTradeRequestMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String activeUsername,
                                String userToViewUsername, JFrame frame, ArrayList<Integer> itemIDsToTrade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU,7));
        usertoViewLabel.setText(userToViewUsername + cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 0));
        userViewingLabel.setText(activeUsername + cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 0));

        meetingPlaceLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 1));
        meetingTimeLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 2));

        sendTradeRequestButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 5));
        backButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 6));


        ArrayList<Item> user2Items= useCases.itemManager.getAvailableItems(userToViewUsername);
        ArrayList<Item> user1Items= useCases.itemManager.getAvailableItems(activeUsername);

        for (Item item: user2Items){
            JCheckBox box = new JCheckBox();
            box.setText(item.getName() + " - " + item.getId());
            userToViewItems.add(box);
        }
        for (Item item: user1Items){
            JCheckBox box = new JCheckBox();
            box.setText(item.getName() + " - " + item.getId());
            myItems.add(box);
        }


        sendTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<Integer> otherSelectedItems = getSelectedItemIdsHelper(userToViewItems);
                ArrayList<Integer> mySelectedItems = getSelectedItemIdsHelper(myItems);

                TradingUser user1 = (TradingUser) useCases.userManager.searchUser(activeUsername);
                TradingUser user2 = (TradingUser) useCases.userManager.searchUser(userToViewUsername);

                LocalDateTime timeOfTrade = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                try {
                    timeOfTrade = LocalDateTime.parse(meetingTime.getText(), formatter);
                } catch (DateTimeParseException e) {
                    // "Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm):"
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 3));
                    return;
                }

                useCases.tradeCreator.createTradeRequest(user1, user2, mySelectedItems, otherSelectedItems,
                        timeOfTrade, meetingPlace.getText());

                //"Trade Request sent successfully"
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 4));
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases,
                        cpg, activeUsername, frame);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserMenu viewUserMenu = new ViewUserMenu(useCases, cpg, activeUsername,
                        userToViewUsername, frame, true,
                        useCases.userManager.searchUser(userToViewUsername) instanceof TradingUser);
            }
        });
    }

    private ArrayList<Integer> getSelectedItemIdsHelper(JList bruh){
        ArrayList<Integer> selectedItems = new ArrayList<>();

        ListModel list = bruh.getModel();
        for(int i = 0; i < list.getSize(); i++){
            JCheckBox checkbox = (JCheckBox) list.getElementAt(i);
            if (checkbox.isSelected()){
                selectedItems.add(Integer.parseInt(checkbox.getText().split(" - ")[1]));
            }
        }

        return selectedItems;
    }


}

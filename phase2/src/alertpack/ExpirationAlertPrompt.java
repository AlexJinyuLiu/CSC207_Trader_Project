package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ExpirationAlertPrompt {
    private JPanel mainPanel;
    private JButton reexchangeButton;
    private JButton reportButton;
    private JLabel descLabel;

    public ExpirationAlertPrompt(LocalDateTime dueDate, String username, int tradeID, GuiMenuPresenter menuPresenter,
                                 TradeCreator tradeCreator, ItemManager itemManager) {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        reportButton.setText(menuPresenter.getText(Frame.EXPIRATIONALERT,1));
        reexchangeButton.setText(menuPresenter.getText(Frame.EXPIRATIONALERT,2));
        String desc = menuPresenter.getText(Frame.EXPIRATIONALERT,0) + dueDate +
                ":\n" + menuPresenter.printTradeToString(itemManager, tradeCreator.getTradeHistories().searchTemporaryTrade(tradeID));
        descLabel.setText(desc);

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reexchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

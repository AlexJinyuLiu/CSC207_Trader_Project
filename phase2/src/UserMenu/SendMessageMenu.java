package UserMenu;

import AdminMenu.ViewingUserAsAdmin;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageMenu {
    private JPanel mainPanel;
    private JTextPane messageTextPane;
    private JButton sendButton;
    private JButton backButton;

    public SendMessageMenu(String sendersUsername, String receiversUsername, ControllerPresenterGrouper cpg,
                           UseCaseGrouper useCases, JFrame frame, boolean isTradingUserSending,
                           boolean isTradingUserReceiving) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        sendButton.setText(cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 0));
        backButton.setText(cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 1));
        messageTextPane.setText(cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 2));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageBody = messageTextPane.getText();
                cpg.tradingUserActions.messageUser(sendersUsername, receiversUsername, messageBody, useCases.userManager);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 3));
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sendersUsername.equals("admin")) {
                    ViewUserMenu viewUserMenu = new ViewUserMenu(useCases, cpg, sendersUsername, receiversUsername, frame,
                            isTradingUserSending, isTradingUserReceiving);
                }
                else {
                    ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, cpg, receiversUsername,
                            frame);
                }
            }
        });
    }
}

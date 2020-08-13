package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserMenu.SendMessageMenu;
import entitypack.Frame;
import entitypack.TradingUser;

/**
 * A UI class that links the following action menus that can be done to the user being viewed at:
 *  - Send message
 *  - Remove items from the user's wishlist
 *  - Remove items from the user's inventory
 *  - Freeze user
 */
public class ViewingUserAsAdmin {
    private JButton sendAMessageButton;
    private JButton removeAnItemFromButton;
    private JButton removeAnItemFromButton1;
    private JButton freezeUserButton;
    private JButton backButton;
    private JPanel mainPanel;

    /**
     * Constructs the interface that links multiple actions can be done to the User with usernameViewed
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param usernameViewed the username of the User being viewed at
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewingUserAsAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String usernameViewed,
                              JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        //TODO: set button text and implement the remaining button listeners

        sendAMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                SendMessageMenu sendMessageMenu = new SendMessageMenu("admin", usernameViewed, cpg,
//                        useCases, window, false, useCases.userManager.searchUser(usernameViewed)
//                        instanceof TradingUser);
                String message = JOptionPane.showInputDialog(window,cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 2),
                        cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 0), JOptionPane.PLAIN_MESSAGE);
                if (!message.isEmpty()) {
                    cpg.tradingUserActions.messageUser("admin", usernameViewed, message, useCases.userManager);
                    JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 3));
                }
            }
        });
        removeAnItemFromButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        removeAnItemFromButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        freezeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FreezeMenu freezeMenu = new FreezeMenu(useCases, cpg, usernameViewed, window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewAllUsers viewAllUsers = new ViewAllUsers(useCases, cpg, true, window);
            }
        });
    }
}

package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;
import entitypack.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreezeMenu {
    private JButton freezeButton;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton unfreezeButton;
    private JLabel usernameStatus;
    private JLabel currentFrozenStatus;

    public FreezeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String viewedUsername, JFrame window) {

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        freezeButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 4));
        unfreezeButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 5));
        backButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 6));

        usernameStatus.setText(viewedUsername + cpg.menuPresenter.getText(Frame.ADMINFREEZE, 0));
        if (!useCases.userManager.isTradingUser(viewedUsername)) {
            freezeButton.setEnabled(false);
            unfreezeButton.setEnabled(false);
            currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 3));
        } else {
            TradingUser viewedUser = (TradingUser) useCases.userManager.searchUser(viewedUsername);
            if (viewedUser.getFrozen()) {
                currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 1));
            } else {
                currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 2));
            }
        }
        freezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        unfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, cpg, viewedUsername, window);
            }
        });
    }
}

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.MetroArea;
import entitypack.TradingUser;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveStatusMenu {
    private JComboBox statusBox;
    private JButton setStatusButton;
    private JButton backButton;
    private JPanel mainPanel;

    public SetActiveStatusMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        backButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,0));
        setStatusButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,1));
        statusBox.addItem(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,2));
        statusBox.addItem(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,3));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);
            }
        });
        setStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
                if (statusBox.getSelectedItem() == cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,2) &&
                        !user.isActive()){
                    user.setActive(true);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,4));
                }
                else if (statusBox.getSelectedItem() == cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,3) &&
                        user.isActive()){
                    user.setActive(false);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,4));
                }
                else {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,5));
                }
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);
            }
        });
    }
}

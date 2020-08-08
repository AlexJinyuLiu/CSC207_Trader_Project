import alertpack.Alert;
import alertpack.DismissableAlert;
import alertpack.DismissibleAlertPrompt;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;
import usecasepack.Prompt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradingUserActionsMenu {
    private JButton viewItemsAndWishlistButton;
    private JButton viewUserStatsButton;
    private JButton viewOtherUsersButton;
    private JButton viewPendingTradesButton;
    private JButton setActiveStatusButton;
    private JButton changeMetropolitanAreaButton;
    private JButton requestUnfreezeButton;
    private JPanel mainPanel;
    private JButton viewDeadTradesButton;
    private JButton viewPendingTradeRequestsButton;
    private JButton viewCompletedTradesButton;
    private JButton viewActiveTempTradesButton;
    private JButton viewMessagesButton;

    public TradingUserActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        viewItemsAndWishlistButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 0));
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 1));
        viewUserStatsButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 2));
        viewPendingTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 3));
        setActiveStatusButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 4));
        changeMetropolitanAreaButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 5));
        requestUnfreezeButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 6));
        viewActiveTempTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 11));
        viewDeadTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 10));
        viewCompletedTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 12));
        viewPendingTradeRequestsButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 13));




        if (((TradingUser)useCases.userManager.searchUser(username)).getFrozen()){
            //Your account has been frozen.
            JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 9));
        }

        viewItemsAndWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            ViewItemsAndWishlistMenu viewItemsAndWishlistMenu = new ViewItemsAndWishlistMenu(useCases, cpg, username,
                    frame);
            }
        });
        viewUserStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserStatsMenu viewUserStatsMenu = new ViewUserStatsMenu(useCases, cpg, username, frame);
            }
        });
        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewAllOtherUsersMenu viewOtherUsersMenu = new ViewAllOtherUsersMenu(useCases, cpg, username, frame,
                        true);
            }
        });
        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewPendingTradesMenu viewPendingTradesMenu = new ViewPendingTradesMenu(useCases, cpg, username, frame);
            }
        });
        setActiveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveStatusMenu setActiveStatusMenu = new SetActiveStatusMenu(useCases, cpg, username, frame);
            }
        });
        changeMetropolitanAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveCityMenu setActiveCityMenu = new SetActiveCityMenu(useCases, cpg, username, frame);
            }
        });
        requestUnfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cpg.tradingUserActions.isUserFrozen(useCases.userManager, username)){
                    cpg.tradingUserActions.sendUnfreezeRequest(useCases.adminUser, username);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 7));
                }else {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 8));
                }
            }
        });
        viewDeadTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewDeadTradesMenu viewDeadTradesMenu = new ViewDeadTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewPendingTradeRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewPendingTradeRequestsMenu viewPendingTradeRequestsMenu = new ViewPendingTradeRequestsMenu(useCases, cpg, username, frame);
            }
        });
        viewActiveTempTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewActiveTemporaryTradesMenu viewActiveTemporaryTradesMenu = new ViewActiveTemporaryTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewCompletedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewCompletedTradesMenu viewCompletedTradesMenu = new ViewCompletedTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMessagesMenu viewMessagesMenu = new ViewMessagesMenu(useCases, cpg, username, frame);
            }
        });
    }
}

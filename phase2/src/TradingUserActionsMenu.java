import alertpack.Alert;
import alertpack.DismissableAlert;
import alertpack.DismissibleAlertPrompt;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
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

    public TradingUserActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        for(Prompt alert: cpg.userAlertManager.getUserAlerts(username, useCases.userManager)){
            if (alert instanceof DismissableAlert){
                //DismissibleAlertPrompt dismissibleAlertPrompt = new DismissibleAlertPrompt(alert.handle(cpg.menuPresenter, useCases.adminUser, useCases.userManager, useCases.tradeCreator, useCases.itemManager), cpg.menuPresenter);
            }
        }

        //TODO un hard code below. do this by calling menuPresenter.getText (create this method)
        viewItemsAndWishlistButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 0));
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 1));
        viewUserStatsButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 2));
        viewPendingTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 3));
        setActiveStatusButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 4));
        changeMetropolitanAreaButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 5));
        requestUnfreezeButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 6));

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
                ViewAllOtherUsersMenu viewOtherUsersMenu = new ViewAllOtherUsersMenu(useCases, cpg, username, frame, true);
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
                if(true){
                    //TODO send the unfreeze request here

                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 7));
                }else {
                    //TODO replace this with a presenter call
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 8));
                }
            }
        });
    }
}

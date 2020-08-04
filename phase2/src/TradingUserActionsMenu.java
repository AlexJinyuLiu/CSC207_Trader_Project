import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import entitypack.Frame;
import entitypack.TradingUser;
import entitypack.User;

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

    public TradingUserActionsMenu(ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


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
            ViewItemsAndWishlistMenu viewItemsAndWishlistMenu = new ViewItemsAndWishlistMenu(cpg, username, frame);
            }
        });
        viewUserStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewUserStatsMenu viewUserStatsMenu = new ViewUserStatsMenu(cpg, username, frame);
            }
        });
        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(cpg, username, frame);
            }
        });
        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewPendingTradesMenu viewPendingTradesMenu = new ViewPendingTradesMenu(cpg, username, frame);
            }
        });
        setActiveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveStatusMenu setActiveStatusMenu = new SetActiveStatusMenu(cpg, username, frame);
            }
        });
        changeMetropolitanAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetActiveCityMenu setActiveCityMenu = new SetActiveCityMenu(cpg, username, frame);
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

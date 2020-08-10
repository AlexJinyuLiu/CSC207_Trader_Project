import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUserStatsMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel numberBorrowedLabel;
    private JLabel numberBorrowedAmount;
    private JLabel numberLentLabel;
    private JLabel numberLentAmount;
    private JLabel frozenStatusLabel;
    private JLabel frozenStatusBool;
    private JLabel numIncompleteLabel;
    private JLabel numIncompleteAmount;
    private JLabel weeklyTransactionsLabel;
    private JLabel weeklyTransactionsAmount;
    private JLabel mostRecentlyTradedItemsLabel;
    private JLabel mostRecentlyTradedItemsAmount;
    private JLabel mostFrequentTradingPartnersLabel;
    private JLabel mostFrequentTradingPartnersValue;

    public ViewUserStatsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                             String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        numberBorrowedLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,0));
        numberLentLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,1));
        frozenStatusLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,2));
        numIncompleteLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,3));
        weeklyTransactionsLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,4));
        mostRecentlyTradedItemsLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,5));
        mostFrequentTradingPartnersLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,6));


        //TODO use use-cases to fill in the stats


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases,
                        controllerPresenterGrouper, username, frame);
            }
        });
    }
}

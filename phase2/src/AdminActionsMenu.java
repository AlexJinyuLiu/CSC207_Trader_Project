import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionsMenu {
    private JPanel mainPanel;
    private JButton setBorrowLendThresholdButton;
    private JButton setCompleteTradeThresholdButton;
    private JButton setIncompeteTradeThresholdButton;
    private JButton addNewAdminButton;
    private JButton viewThresholdValuesButton;
    private JButton editUndoTradeButton;
    private JButton viewUsers;

    public AdminActionsMenu(UseCaseGrouper usecases, ControllerPresenterGrouper cpg, boolean isAdmin, JFrame jframe) {
        jframe.setContentPane(mainPanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);


        setBorrowLendThresholdButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,0));
        setCompleteTradeThresholdButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,1));
        setIncompeteTradeThresholdButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,2));
        addNewAdminButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,3));
        viewThresholdValuesButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,4));
        editUndoTradeButton.setText(cpg.menuPresenter.getText(Frame.MAINMENU,5));
        viewUsers.setText(cpg.menuPresenter.getText(Frame.MAINMENU,6));

        setBorrowLendThresholdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetBorrowLendThreshold setBorrowLendThreshold = new SetBorrowLendThreshold(usecases, cpg, jframe);
            }
        });
        addNewAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewAdmin addNewAdmin = new AddNewAdmin(usecases, cpg, jframe);
            }
        });
        viewThresholdValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editUndoTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setIncompeteTradeThresholdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetIncompleteTradeThreshold setIncompleteTradeThreshold = new SetIncompleteTradeThreshold(usecases, cpg, jframe);
            }
        });
        setCompleteTradeThresholdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetCompleteTradeThreshold setCompleteTradeThreshold = new SetCompleteTradeThreshold(usecases, cpg, jframe);
            }
        });
        viewUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

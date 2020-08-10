package AdminMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserMenu.ViewMessagesMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

public class AdminActionsMenu {
    public JPanel mainPanel;
    private JButton setCompleteTradeThreshold;
    public JButton setBorrowLendThreshold;
    public JButton addNewAdmin;
    private JButton viewThresholdValues;
    private JButton setIncompleteTradeThreshold;
    private JButton editUndoTrade;
    public JButton viewAllUsers;
    private JButton viewItemValidationRequests;
    private JButton viewMessages;
    public JButton searchUser;

    public AdminActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        setBorrowLendThreshold.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 0));
        setCompleteTradeThreshold.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 1));
        setIncompleteTradeThreshold.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 2));
        addNewAdmin.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 3));
        viewThresholdValues.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 4));
        editUndoTrade.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 5));
        viewAllUsers.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 6));
        viewItemValidationRequests.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 7));
        viewMessages.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 8));

        setBorrowLendThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });

        addNewAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddNewAdmin addNewAdmin = new AddNewAdmin(useCases, cpg,  frame);
            }

        });
        viewAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewAllUsers viewAllUsers = new ViewAllUsers(useCases, cpg, true, frame);
                /*Login login = new Login(useCases, controllerPresenterGrouper, true, window);
                window.dispose();*/
            }
        });
        viewThresholdValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCurrentThresholds viewCurrentThresholds = new ViewCurrentThresholds(useCases,
                        cpg, frame);
            }
        });
        editUndoTrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTrade editTrade = new EditTrade(useCases, cpg, frame);
            }
        });

        viewItemValidationRequests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewItemValidationRequests viewItemValidationRequests = new ViewItemValidationRequests(useCases,
                        cpg, frame);
            }
        });
        viewMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMessagesMenu viewMessagesMenu = new ViewMessagesMenu(useCases, cpg, "admin", frame);
            }
        });
    }
}
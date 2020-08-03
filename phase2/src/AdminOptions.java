import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;

public class AdminOptions {
    public JPanel mainPanel;
    private JButton setBorrowLendThreshold;
    private JButton setCompleteTradeThreshold;
    public JButton setThreshold;
    public JButton addNewAdmin;
    private JButton viewThresholdValues;
    private JButton setIncompleteTradeThreshold;
    private JButton editUndoTrade;
    public JButton viewAllUsers;
    private JButton searchUsername;
    private JButton backToMainMenu;
    public JButton searchUser;

    public AdminOptions(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, boolean isAdmin, JFrame window){
        window.setContentPane(mainPanel);
        window.setBounds(450, 450, 500, 400);
        mainPanel.setSize(350, 350);
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        setThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                window.dispose();
            }
        });
        addNewAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(useCases, controllerPresenterGrouper, true, window);
                window.dispose();
            }
        });
        viewAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(useCases, controllerPresenterGrouper, true, window);
                window.dispose();
            }
        });
    }
}
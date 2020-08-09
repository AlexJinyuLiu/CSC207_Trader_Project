import AdminMenu.AdminOptions;
import controllerpresenterpack.*;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

     JFormattedTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel usernameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();

    public Login(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, boolean isAdmin, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO: Do this through menuPresenter, it already deals with the language abstraction

        passwordLabel.setText(cpg.menuPresenter.getText(Frame.LOGIN,0));
        usernameLabel.setText(cpg.menuPresenter.getText(Frame.LOGIN,1));
        backButton.setText(cpg.menuPresenter.getText(Frame.LOGIN,2));
        loginButton.setText(cpg.menuPresenter.getText(Frame.LOGIN,3));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pass = new String(password.getPassword());
                String user = username.getText();
                boolean validLogin;
                if(isAdmin) {
                    AdminActions adminActions = new AdminActions();
                    validLogin = adminActions.validateLogin(useCases.adminUser, user, pass);
                    if (validLogin) {
                        //TODO send user to Options
                        //TODO send admin to adminActionsMenu instead of userActionsMenu
                        AdminOptions adminOptionsMenu = new AdminOptions(useCases,
                                cpg, frame);
                    } else {
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.LOGIN,4));
                    }
                }else{
                    UserActions userActions = new UserActions();
                    //This version of validateLogin returns null if the login is invalid, true if the user is a
                    //TradingUser, and false if the user is a BrowsingUser
                    validLogin = userActions.validateLogin(useCases.userManager, user, pass);
                    if (validLogin) {
                        boolean isTradingUser = userActions.isTradingUser(useCases.userManager, user);
                        cpg.userAlertManager.handleAlertQueue(cpg.menuPresenter, useCases.adminUser, useCases.userManager,
                                useCases.tradeCreator, useCases.itemManager, useCases.userManager.getUserAlerts(user));
                        if (isTradingUser) {
                            TradingUserActionsMenu tradingUserActionsMenu =
                                    new TradingUserActionsMenu(useCases, cpg, user, frame);
                        } else{
                            BrowsingUserActionsMenu browsingUserActionsMenu = new BrowsingUserActionsMenu(useCases, cpg,
                                    user, frame);
                        }

                    } else {
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.LOGIN,4));
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            //TODO: Find a better solution for going back than this. This piles up function calls on the stack.
            //  Maybe have a .run() method in all the UI classes and call that instead of making a new object each time?
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(useCases, cpg, frame);
            }
        });
    }


}

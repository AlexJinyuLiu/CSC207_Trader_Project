import controllerpresenterpack.*;
import entitypack.TradingUser;

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

    public Login(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                 boolean isAdmin, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO: Do this through menuPresenter, it already deals with the language abstraction

        passwordLabel.setText("PasswordYeer");
        usernameLabel.setText("Username");
        backButton.setText("back");
        loginButton.setText("login");

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
                        /*AdminUserActionsMenu userActionsMenu = new AdminUserActionsMenu(menuPresenter,
                                user, frame);*/

                    } else {
                        JOptionPane.showMessageDialog(frame, "Username and Password not recognized");
                    }
                }else{
                    UserActions userActions = new UserActions();
                    //This version of validateLogin returns null if the login is invalid, true if the user is a
                    //TradingUser, and false if the user is a BrowsingUser
                    validLogin = userActions.validateLogin(useCases.userManager, user, pass);
                    if (validLogin) {
                        boolean isTradingUser = userActions.isTradingUser(useCases.userManager, user);
                        if (isTradingUser) {
                            //Trading User
                            TradingUserActionsMenu tradingUserActionsMenu =
                                    new TradingUserActionsMenu(controllerPresenterGrouper, user, frame);
                        } else{
                            //Browsing User
                            //TODO: Implement this
                            /*BrowsingUserActionsMenu browsingUserActionsMenu = new BrowsingUserActionsMenu(english,
                                    user, frame);*/
                        }

                    } else {
                        //TODO: Un-hard code this
                        JOptionPane.showMessageDialog(frame, "Username and Password not recognized");
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            //TODO: Find a better solution for going back than this. This piles up function calls on the stack.
            //  Maybe have a .run() method in all the UI classes and call that instead of making a new object each time?
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }


}

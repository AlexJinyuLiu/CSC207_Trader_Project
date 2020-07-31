import entitypack.TradingUser;
import entitypack.User;

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

    public Login(boolean english, boolean isAdmin, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        if (english){
        //TODO set the labels text to
            //do we want to user the presenter here?? -Louis
            usernameLabel.setText("Username");
            passwordLabel.setText("Password");
            backButton.setText("back");
            loginButton.setText("login");

        }else{
        //TODO set the labels text to french
        }
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pass = new String(password.getPassword());
                String user = username.getText();
                if(isAdmin) {
                    //TODO replace this with an actual way to log in
                    if (true) {
                        //TODO send user to Options
                        UserActionsMenu userActionsMenu = new UserActionsMenu(english, new TradingUser("test" ), frame);

                    } else {
                        JOptionPane.showMessageDialog(frame, "Username and Password not recognized");
                    }
                }else{
                    //TODO replace if statement with an actual way to log in
                    if (true) {
                        //TODO send admin to adminActionsMenu instead of userActionsMenu
                        UserActionsMenu userActionsMenu = new UserActionsMenu(english, new TradingUser("test"), frame);

                    } else {
                        JOptionPane.showMessageDialog(frame, "Username and Password not recognized");
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(english, frame);
            }
        });
    }


}

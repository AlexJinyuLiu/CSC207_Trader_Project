import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

    private JFormattedTextField username;
    private JPasswordField password;
    private JButton Login;
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel usernameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();

    public Login(boolean english, boolean isAdmin) {
        JFrame frame = new JFrame("Trade System");
        frame.setBounds(400, 400, 400, 400);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        if (english){
        //TODO set the labels text to
            //do we want to user the presenter here?? -Louis
            usernameLabel.setText("Username");
            passwordLabel.setText("Password");
        }else{
        //TODO set the labels text to french
        }
        if(isAdmin){
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pass = new String(password.getPassword());
                String user = username.getText();
                //TODO replace this with an actual way to log in
                if (pass.length() > 1 && user.length() > 1){
                    //TODO send user to Options

                }else{
                    JOptionPane.showMessageDialog(frame, "Username and Password not recognized");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(english);
                frame.dispose();
            }
        });
    }
    }

}

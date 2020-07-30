import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

    private JFormattedTextField username;
    private JPasswordField password;
    private JButton Login;
    private JPanel mainPanel;

    public Login() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}

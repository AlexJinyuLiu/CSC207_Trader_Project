import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount {
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmationField;
    private JButton back;
    private JButton createAccountButton;
    private JLabel username;
    private JLabel password;
    private JLabel passwordConfirmation;
    private JPanel mainPanel;
    private JCheckBox tradingUserCheckBox;
    private JLabel tradingUserCheckBoxLabel;
    private JComboBox citySelectorComboBox;
    private JTextField usernameTextField;
    private JLabel citySelectorLabel;

    public CreateAccount(boolean english, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}

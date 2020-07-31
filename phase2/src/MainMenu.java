import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JButton createAccountButton;
    private JButton loginAsUserButton;
    private JButton loginAsAdminButton;
    private JPanel mainPanel;

    public MainMenu(boolean english, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateAccount createAccount = new CreateAccount(english);
                frame.dispose();
            }
        });
        loginAsUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(english, false);
                frame.dispose();
            }
        });
        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(english, true);
                frame.dispose();
            }
        });
    }

}

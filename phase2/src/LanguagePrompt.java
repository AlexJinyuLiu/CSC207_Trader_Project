import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguagePrompt {
    public JPanel panelMain;
    private JButton englishButton;
    private JButton frenchButton;


    public static void main(String[] args) {
        LanguagePrompt languagePrompt = new LanguagePrompt();
    }


    public LanguagePrompt() {
        JFrame frame = new JFrame("Trade System");
        frame.setBounds(400, 400, 400, 400);
        frame.setVisible(true);
        frame.setContentPane(new LanguagePrompt().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login();
                frame.dispose();
                login.setVisible(true);



            }
        });
        frenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            Login login = new Login();
            frame.dispose();
            login.setVisible(true);
            }
        });
    }
}

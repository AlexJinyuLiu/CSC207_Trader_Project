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
        panelMain.setSize(400, 400);
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(true);
                frame.dispose();



            }
        });
        frenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(false);
                frame.dispose();

            }
        });
    }
}

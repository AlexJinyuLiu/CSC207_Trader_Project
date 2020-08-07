import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateItemValidationRequestMenu {
    private JButton createRequestButton;
    private JButton backButton;
    private JTextField itemNameField;
    private JTextField itemDescriptionField;
    private JLabel itemDescriptionLabel;
    private JLabel itemNameLabel;
    private JPanel mainPanel;

    public CreateItemValidationRequestMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                           String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter

        createRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}

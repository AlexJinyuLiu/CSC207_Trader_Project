import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import controllerpresenterpack.UserActions;
import entitypack.MetroArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreateUserAccount {
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
    private JComboBox<MetroArea> citySelectorComboBox;
    private JTextField usernameTextField;
    private JLabel citySelectorLabel;

    public CreateUserAccount(UseCaseGrouper useCases, ControllerPresenterGrouper
            controllerPresenterGrouper, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //TODO: Get a better solution for this using menuPresenter
        tradingUserCheckBoxLabel.setText("Browsing only?:");
        username.setText("Username:");
        password.setText("Password:");
        passwordConfirmation.setText("Confirm Password:");
        citySelectorLabel.setText("City:");
        back.setText("Back");
        createAccountButton.setText("Confirm");

        //TODO: Do this in a loop
        citySelectorComboBox.addItem(MetroArea.TORONTO);
        citySelectorComboBox.addItem(MetroArea.OTTAWA);
        citySelectorComboBox.addItem(MetroArea.VANCOUVER);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = usernameTextField.getText();
                boolean isBrowsingUser = tradingUserCheckBox.isSelected();
                MetroArea metroArea = (MetroArea)citySelectorComboBox.getSelectedItem();

                char[] passwordCharArr = passwordField.getPassword();
                char[] passwordConfirmationArr = passwordConfirmationField.getPassword();
                if (passwordCharArr.length != passwordConfirmationArr.length){
                    JOptionPane.showMessageDialog(frame, "Passwords are not the same length. " +
                            "Please ensure the same password is entered in both fields.");
                    return;
                }
                StringBuilder password = new StringBuilder();
                for (int i = 0; i < passwordCharArr.length; i++){
                    if (Character.compare(passwordCharArr[i], passwordConfirmationArr[i]) != 0){
                        JOptionPane.showMessageDialog(frame, "Passwords do not match. Please ensure the same" +
                                " password is entered in both fields.");
                        return;
                    }
                    password.append(passwordCharArr[i]);
                }

                //TODO: boolean stuff with this method
                boolean userRegistered = controllerPresenterGrouper.tradingUserActions.addNewLogin(useCases.userManager,
                        username, password.toString(), isBrowsingUser, metroArea);
                if (!userRegistered){
                    JOptionPane.showMessageDialog(frame, "Could not register this account.");
                    return;
                }
                if (isBrowsingUser){
                    //BrowsingUserActionsMenu browsingUserActionsMenu = new BrowsingUserActionsMenu();
                } else {
                    TradingUserActionsMenu tradingUserActionsMenu = new
                            TradingUserActionsMenu(controllerPresenterGrouper, username, frame);
                }


            }
        });
    }
}

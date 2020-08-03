import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.MetroArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            cpg, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        tradingUserCheckBox.setText(cpg.menuPresenter.getText(1,3));
        username.setText(cpg.menuPresenter.getText(1,1));
        password.setText(cpg.menuPresenter.getText(1,2));
        passwordConfirmation.setText(cpg.menuPresenter.getText(1,5));
        citySelectorLabel.setText(cpg.menuPresenter.getText(42, 0));
        back.setText(cpg.menuPresenter.getText(47,2));
        createAccountButton.setText(cpg.menuPresenter.getText(47,4));


        for (MetroArea Area : MetroArea.values()){
            citySelectorComboBox.addItem(Area);
        }
//        citySelectorComboBox.addItem(MetroArea.TORONTO);
//        citySelectorComboBox.addItem(MetroArea.OTTAWA);
//        citySelectorComboBox.addItem(MetroArea.VANCOUVER);

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
                    //"Passwords are not the same length. " +
                    //                            "Please ensure the same password is entered in both fields."
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(1,6));
                    return;
                }
                StringBuilder password = new StringBuilder();
                for (int i = 0; i < passwordCharArr.length; i++){
                    if (Character.compare(passwordCharArr[i], passwordConfirmationArr[i]) != 0){
                        // "Passwords do not match. Please ensure the same password is entered in both fields."
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(1,7));
                        return;
                    }
                    password.append(passwordCharArr[i]);
                }

                //TODO: boolean stuff with this method
                boolean userRegistered = cpg.tradingUserActions.addNewLogin(useCases.userManager,
                        username, password.toString(), isBrowsingUser, metroArea);
                if (!userRegistered){
                    //"Could not register this account."
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(1,8));
                    return;
                }
                if (isBrowsingUser){
                    //BrowsingUserActionsMenu browsingUserActionsMenu = new BrowsingUserActionsMenu();

                } else {
                    TradingUserActionsMenu tradingUserActionsMenu = new
                            TradingUserActionsMenu(cpg, username, frame);
                }


            }
        });
    }
}

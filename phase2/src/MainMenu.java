import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JButton createAccountButton;
    private JButton loginAsUserButton;
    private JButton loginAsAdminButton;
    private JPanel mainPanel;

    public MainMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountButton.setText(controllerPresenterGrouper.menuPresenter.getText(10,2));
        loginAsAdminButton.setText(controllerPresenterGrouper.menuPresenter.getText(10,4));
        loginAsUserButton.setText(controllerPresenterGrouper.menuPresenter.getText(10,3));

        frame.pack();
        frame.setVisible(true);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateUserAccount createAccount = new CreateUserAccount(useCases, controllerPresenterGrouper, frame);

            }
        });
        loginAsUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(useCases, controllerPresenterGrouper, false, frame);

            }
        });
        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login login = new Login(useCases, controllerPresenterGrouper, true, frame);

            }
        });
    }

}

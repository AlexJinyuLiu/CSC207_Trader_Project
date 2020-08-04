import controllerpresenterpack.ControllerPresenterGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowsingUserActionsMenu {

    private JPanel mainPanel;
    private JButton viewOtherUsersButton;
    private JLabel Loggedin;

    public BrowsingUserActionsMenu(ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Loggedin.setText(cpg.menuPresenter.getText(Frame.BROWSINGUSERACTIONSMENU,0) + username);
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(Frame.BROWSINGUSERACTIONSMENU,1));

        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(cpg, username, frame);
            }
        });
    }
}

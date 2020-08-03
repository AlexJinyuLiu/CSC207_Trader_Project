import com.oracle.tools.packager.Log;
import controllerpresenterpack.ControllerPresenterGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class browsingUserActionsMenu {

    private JPanel mainPanel;
    private JButton viewOtherUsersButton;
    private JLabel Loggedin;

    public browsingUserActionsMenu(ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Loggedin.setText(cpg.menuPresenter.getText(43,5) + username);
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(43,1));

        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOtherUsersMenu viewOtherUsersMenu = new ViewOtherUsersMenu(cpg, username, frame);
            }
        });
    }
}

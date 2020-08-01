import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewItemsAndWishlistMenu {
    private JScrollPane itemPane;
    private JPanel mainPanel;
    private JButton createItemValidationRequestButton;
    private JButton backButton;

    public ViewItemsAndWishlistMenu(ControllerPresenterGrouper controllerPresenterGrouper,
                                    String username, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Todo set button text to english or french options from menu presenter

        createItemValidationRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO open a menu to create an itemValidationRequest

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(controllerPresenterGrouper,
                        username, frame);
            }
        });
    }
}

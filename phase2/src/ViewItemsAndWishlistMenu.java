import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.User;
import entitypack.Item;
import java.util.ArrayList;
import alertpack.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewItemsAndWishlistMenu {
    private JScrollPane itemPane;
    private JPanel mainPanel;
    private JButton createItemValidationRequestButton;
    private JButton backButton;

    public ViewItemsAndWishlistMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                    String username, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ArrayList<Item> items = useCases.itemManager.getAvailableItems(username);

        for (Item item: items){
            JButton thing = new JButton();
            thing.setText(item.getName());
            itemPane.add(thing);
            thing.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemViewer a = new ItemViewer(useCases, cpg, username, item.getId(),frame);
                }
            });
        }



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}

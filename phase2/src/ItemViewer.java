import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Item;

import javax.print.MultiDocPrintService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ItemViewer {
    private JButton removeItemButton;
    private JButton backButton;
    private JLabel itemDescription;
    private JPanel mainPanel;

    public ItemViewer(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, int itemId, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Item item = useCases.itemManager.searchItem(itemId);

        itemDescription.setText(item.getName() + "\n" + item.getDescription());


        //Todo set button text to english or french options from menu presenter

        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                useCases.itemManager.removeFromInventory(itemId);
                ViewItemsAndWishlistMenu viewitems = new ViewItemsAndWishlistMenu(useCases, cpg, username, frame);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);
            }
        });
    }
}

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.MenuPresenter;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
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
    private JScrollPane wishlistItemPane;
    private JTextPane itemTextPane;
    private JTextPane wishlistTextPane;

    public ViewItemsAndWishlistMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                    String username, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 2));
        createItemValidationRequestButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 3));

        StringBuilder availItemsString = new StringBuilder(
                cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 0));
        ArrayList<Item> availableItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                username);
        for (Item item : availableItems){
            availItemsString.append(item.getName() + " ID: " + item.getId() + "\n");
            //availableItemIDs.add(item.getId());
        }
        itemTextPane.setText(availItemsString.toString());

        ArrayList<String> wishlistItems = cpg.tradingUserActions.getWishListItems(useCases.userManager,
                username);
        StringBuilder wishlistItemsString = new StringBuilder(cpg.menuPresenter.getText(
                Frame.VIEWITEMSANDWISHLISTMENU, 1));
        for (String wishlistItem : wishlistItems){
            wishlistItemsString.append(wishlistItem + "\n");
        }
        wishlistTextPane.setText(wishlistItemsString.toString());

        for (Item item: availableItems){
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


        createItemValidationRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){

                CreateItemValidationRequestMenu createItemValidationRequestMenu =
                        new CreateItemValidationRequestMenu(useCases, cpg, username, frame);
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}

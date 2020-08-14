package AdminMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//have already chosen user
public class removeItemFromUserWishlist {
    private JPanel mainPanel;
    private JLabel menuTitle;
    private JButton submit;
    private JScrollPane userItems;

    public removeItemFromUserWishlist(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame window){
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
        menuTitle.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 2));
        submit.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 6));
        window.setVisible(true);

        DefaultListModel<String> items = new DefaultListModel<String>();
        items.addAll(user.getWishlistItemNames());
    //    JCheckBox[] boxes = new JCheckBox[items.length]; //making an array only references one but doesn't allocate any memory space for it
    //    //so gotta pass in an int
    //    for (int i = 0; i<boxes.length; i++){
    //        boxes[i] = new JCheckBox(items[i]); //then gotta actually put something/elements into the array

        JList list = new JList(items); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                items.remove(index);

                int size = items.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    submit.setEnabled(false);

                } else { //Select an index.
                    if (index == items.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);

                }
            }
        });
    }


}

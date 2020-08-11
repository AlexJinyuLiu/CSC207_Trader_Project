package AdminMenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.ItemValidationRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewItemValidationRequests {
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane itemPane;



    public ViewItemValidationRequests(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, JFrame frame){

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JPanel content = new JPanel();

        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS, 0));
        JTextArea titleText = new JTextArea();
        titleText.setText(cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS,
                1));
        content.add(titleText);
        ArrayList<ItemValidationRequest> validationRequests =
                cpg.adminActions.getItemValidationRequests(useCases.itemManager);

        int n = 2;
        for (ItemValidationRequest validationRequest : validationRequests){

            JTextArea itemText = new JTextArea();
            itemText.setText(cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS, 2) + " " +
                    validationRequest.getItemName() + ", " +
                    cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS, 3) + " " +
                    validationRequest.getItemDescription() + ", " +
                    cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS, 4) + " " +
                    validationRequest.getUsernameOfCreator() + "\n");
            c.gridy = n;
            n++;
            content.add(itemText, c);

            JButton approveButton = new JButton();
            approveButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMVALIDATIONREQUESTS, 5));



            approveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpg.adminActions.approveItemValidationRequest(useCases.itemManager, validationRequest);
                    approveButton.setEnabled(false);
                }
            });
            content.add(approveButton, c);
        }


        itemPane.setViewportView(content);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, cpg, frame);
            }
        });
    }
}

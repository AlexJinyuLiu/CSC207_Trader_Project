package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemValidationDeclinedAlertPrompt {
    private JButton sendItemValidationRequestButton;
    private JButton dismissButton;
    private JLabel descLabel;
    private JPanel mainPanel;

    public ItemValidationDeclinedAlertPrompt(String desc, GuiMenuPresenter menuPresenter) {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        sendItemValidationRequestButton.setText(menuPresenter.getText(Frame.ITEMVALIDATIONDECLINEDALERT,10));
        dismissButton.setText(menuPresenter.getText(Frame.ITEMVALIDATIONDECLINEDALERT,11));
        descLabel.setText(desc);

        sendItemValidationRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

package alertpack;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DismissibleAlert extends JFrame{
    private JButton dismissButton;
    private JLabel descLabel;
    private JPanel mainPanel;

    public DismissibleAlert(String desc, GuiMenuPresenter menuPresenter) {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        descLabel.setText(desc);
        dismissButton.setText(menuPresenter.getText(Frame.MESSAGEALERT,0));

        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // cpg.userAlertManager.
            }
        });
    }
}

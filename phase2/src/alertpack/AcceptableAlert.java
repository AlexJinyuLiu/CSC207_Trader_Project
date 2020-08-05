package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptableAlert {
    private JPanel mainPanel;
    private JButton acceptButton;
    private JButton dismissButton;
    private JLabel descLabel;

    public AcceptableAlert(String desc, String username, GuiMenuPresenter menuPresenter) {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        descLabel.setText(desc);
        dismissButton.setText(menuPresenter.getText(Frame.MESSAGEALERT,0));

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // I'm thinking about multiple alerts sharing the same forms while having different constructors (passing different alertTypes)
                // So based on my organization, AcceptableAlert applies to the followings:
                // FreezeUserAlert(String desc, String username, GuiMenuPresenter menuPresenter)
                // ReportAlert
                // ItemValidationRequestAlert
                // UnfreezeRequestAlert - Tingyu

            }
        });
    }
}

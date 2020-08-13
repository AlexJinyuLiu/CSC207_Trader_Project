import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllerpresenterpack.*;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;
import usecasepack.AdminUser;

public class LanguagePrompt {
    public JPanel panelMain;
    private JButton englishButton;
    private JButton frenchButton;



    public LanguagePrompt(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame frame){
        frenchButton.setIcon(new ImageIcon("phase2/data/baguette.png"));
        englishButton.setIcon(new ImageIcon("phase2/data/hamburger.png"));
        frame.setMinimumSize(new Dimension(800, 600));
        panelMain.setSize(800, 600);
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: Storing menuPresenter here might have some unforseen consequences when we want to access it
                //  from outside the UI layer (do we need to do that?)
                GuiMenuPresenter menuPresenter = new GuiMenuPresenter("English");
                controllerPresenterGrouper.setMenuPresenter(menuPresenter);
                MainMenu mainMenu = new MainMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
        frenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GuiMenuPresenter menuPresenter = new GuiMenuPresenter("French");
                controllerPresenterGrouper.setMenuPresenter(menuPresenter);
                MainMenu mainMenu = new MainMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }
}

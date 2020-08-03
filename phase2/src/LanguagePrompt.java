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




    public static void main(String[] args) {
        //TODO: Delete these once we've linked the whole program together
        AdminUser adminUser = new AdminUser("admin", "admin");
        UserManager userManager = new UserManager();
        TradeCreator tradeCreator = new TradeCreator();
        ItemManager itemManager = new ItemManager();
        UseCaseGrouper useCases = new UseCaseGrouper(adminUser, userManager, tradeCreator, itemManager);

        AdminActions adminActions = new AdminActions();
        TradingUserActions tradingUserActions = new TradingUserActions();
        BrowsingUserActions browsingUserActions = new BrowsingUserActions();
        ControllerPresenterGrouper controllerPresenterGrouper = new ControllerPresenterGrouper(adminActions,
                tradingUserActions, browsingUserActions);

        LanguagePrompt languagePrompt = new LanguagePrompt(useCases, controllerPresenterGrouper);
    }


    public LanguagePrompt(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper) {
        JFrame frame = new JFrame("Trade System");
        frenchButton.setIcon(new ImageIcon("phase2/data/baguette.png"));
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

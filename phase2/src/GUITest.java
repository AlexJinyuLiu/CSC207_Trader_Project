import AdminMenu.ViewingUserAsAdmin;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUITest {
    public static void main(String[] args) {
        TradeSystem ts = new TradeSystem();
        UseCaseGrouper useCases = ts.loadData();
        ts.onStartUp();

        ControllerPresenterGrouper controllerPresenters = ts.getControllerPresenters();

        JFrame frame = new JFrame("Trade System");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                ts.saveData();
            }
        });

        ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, controllerPresenters, "random", frame);
    }
}

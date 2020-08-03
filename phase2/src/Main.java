import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InputZeroException {
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

        LanguagePrompt languagePrompt = new LanguagePrompt(useCases, controllerPresenters, frame);

    }
}

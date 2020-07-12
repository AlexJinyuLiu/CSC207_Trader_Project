import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AlertPresenter {
    private final LinkedHashMap<Integer, ArrayList<String>> alertsMap = new LinkedHashMap<Integer, ArrayList<String>>();
    File alert;

    private LinkedHashMap readMenus() {
        try {
            alert = new File("Menu.txt");
            BufferedReader br = new BufferedReader(new FileReader(alert));

            try {
                int menuNum = 0;
                String readBuff = br.readLine();
                // add newline char back into sb
                ArrayList<String> section = new ArrayList<String>();
                while (readBuff != null) {
                    if (readBuff.equals("Alert{")) {
                        readBuff = br.readLine();
                        section.clear();
                        while (!readBuff.equals("}")){
                            section.add(readBuff);
                            readBuff = br.readLine();
                        }
                        alertsMap.put(menuNum, section);
                        menuNum ++;
                    }
                    readBuff = br.readLine();
                }

            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occured, please try again later.");
            e.printStackTrace();
        }
        return alertsMap;
    }

    public void printAlert(int alertIndex, int lineIndex) {
        System.out.println(alertsMap.get(alertIndex).get(lineIndex));
    }}

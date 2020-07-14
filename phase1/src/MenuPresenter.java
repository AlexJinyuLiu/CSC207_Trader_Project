
import java.io.*;
import java.util.*;



// import university.Student;

//take file in and store each group of lines into a data structure
//afterwards, call presenter methods i.e. MenuPresenter.loginmenu1() which will be the loginmenu
// corresponding to the cell in the data structure
//basically making a state machine, code pulled from:
//https://stackoverflow.com/questions/12218959/how-to-read-certain-portion-of-the-text-file-in-java


//store ea line in an arraylist for the hashmap

// getMenu(key).get[0] .. get[1]... get[2]
// stringbuilder -- //n
//be able to print it with w/e variable you pass in after you get the txt
public class MenuPresenter {
    private final LinkedHashMap<Integer, ArrayList<String>> menusMap = new LinkedHashMap<Integer, ArrayList<String>>();
    File menu;

    MenuPresenter() {
        try {
            menu = new File("phase1/data/Menu.txt");
            BufferedReader br = new BufferedReader(new FileReader(menu));

            try {
                int menuNum = 0;
                String readBuff = br.readLine();
                // add newline char back into sb
                ArrayList<String> section = new ArrayList<String>();
                while (readBuff != null) {
                    readBuff = readBuff.split("-")[0];
                    if (readBuff.equals("MENU{ ") || readBuff.equals("Alert{ ")) {
                        readBuff = br.readLine();
                        section.clear();
                        while (!readBuff.equals("}")){
                            section.add(readBuff);
                            readBuff = br.readLine();
                        }
                        menusMap.put(menuNum, (ArrayList<String>) section.clone());
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
    }

    public void printMenu(int menuIndex, int lineIndex) {
        try{
            System.out.println(menusMap.get(menuIndex).get(lineIndex));
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
        }
    }

    public void printMenu (int menuIndex, int lineIndex, Object variable){
        try{
            System.out.println(menusMap.get(menuIndex).get(lineIndex) + variable);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
        }
    }

    public void printMenu (int menuIndex, int lineIndex, Object variable1, Object variable2){
        try{
            System.out.println(variable1 + menusMap.get(menuIndex).get(lineIndex) + variable2);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
        }
    }
}

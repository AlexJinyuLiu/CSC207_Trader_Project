package controllerpresenterpack;

import entitypack.Item;
import entitypack.Trade;
import entitypack.User;
import usecasepack.UserManager;

import java.io.*;
import java.util.*;

/**
 * A presenter class that handles the output to the user for menus and alert handling.
 */
public class MenuPresenter {
    /**
     * A map containing all menus and all lines within those menus.
     */
    private final LinkedHashMap<Integer, ArrayList<String>> menusMap = new LinkedHashMap<Integer, ArrayList<String>>();
    /**
     * The file from which we read our menu text.
     */
    File menu;

    /**
     * Initializes a new ControllerPresenterPack.MenuPresenter object, while also populating the menusMap HashMap with the lines from Menu.txt
     */
    public MenuPresenter() {
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

    /**
     * Prints to the user the line in Menu.txt corresponding to the menuIndex and lineIndex.
     * @param menuIndex the index of menu at which the line is located
     * @param lineIndex the index of the line within the menu.
     */
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

    /**
     *  Prints a string representation of a trade.
     * @param trade a trade object
     */
    public void printTradeToString(UserManager userManager, Trade trade){
        // "EntityPack.User 1: " + trade.getUsername1() + "\nEntityPack.User 2: " + trade.getUsername2() +
        //        "\nItems being traded from user 1 to user 2: " + GetItemNamesFromUser1ToUser2(userManager, trade) +
        //"\nItems being traded from user 2 to user 1: " + GetItemNamesFromUser2ToUser1(userManager, trade) +
        //        "\nTime & Date of item exchange: " + trade.getTimeOfTrade().toString() +
        //        "\nLocation of EntityPack.Trade: " + trade.getMeetingPlace() + "\nTradeID: " + trade.getTradeID();
        System.out.println(trade);
        printMenu(32, 1, trade.getUsername1());
        printMenu(32, 2, trade.getUsername2());
        printMenu(32, 3, GetItemNamesFromUser1ToUser2(userManager, trade));
        printMenu(32, 4, GetItemNamesFromUser2ToUser1(userManager, trade));
        printMenu(32, 5, trade.getTimeOfTrade().toString());
        printMenu(32, 6, trade.getMeetingPlace());
        printMenu(32, 7, trade.getTradeID());
        // return "EntityPack.User 1: " + trade.getUsername1(); // to be changed
    }
    // helper method which lists the names of the items going from user 1 to user 2 - Louis
    private String GetItemNamesFromUser1ToUser2(UserManager userManager, Trade trade){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser2()){
            Item item = userManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }
    // helper method which lists the names of the items going from user 2 to user 1 - Louis
    private String GetItemNamesFromUser2ToUser1(UserManager userManager, Trade trade){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser1()){
            Item item = userManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * Prints a string representation of a EntityPack.User
     * @param user the EntityPack.User in question.
     */
    public void printUserToString(User user){
        StringBuilder userString = new StringBuilder("EntityPack.User: " + user.getUsername() + "\n");
        if (user.getAvailableItems().size() == 0){
            userString.append("This EntityPack.User has no items available for trade. \n");
        } else {
            userString.append("Items available for trade: \n");
            for (int i = 0; i < user.getAvailableItems().size() - 1; i++) {
                userString.append(user.getAvailableItems().get(i).getName() + " (ID: " + user.getAvailableItems().get(i).getId() + "), ");
            }
            userString.append(user.getAvailableItems().get(user.getAvailableItems().size() - 1).getName() + " (ID: " +
                    user.getAvailableItems().get(user.getAvailableItems().size() - 1).getId() + ")\n");
        }
        if (user.getWishlistItemNames().size() == 0){
            userString.append("This EntityPack.User has no items in their wishlist. \n");
        } else {
            userString.append("Wishlist: \n");
            for (int i = 0; i < user.getWishlistItemNames().size() - 1; i++) {
                userString.append(user.getWishlistItemNames().get(i) + ", ");
            }
            userString.append(user.getWishlistItemNames().get(user.getWishlistItemNames().size() - 1) + "\n");
        }
        if (user.getFrozen()){
            userString.append("This user is frozen, and thus cannot make a trade. \n");
        }

        System.out.println(userString.toString());
    }

    /**
     * Prints a string representation of an EntityPack.Item object.
     * @param item the item in question.
     */
    public void printItemToString(Item item) {
        printMenu(29, 3, item.getName());
        printMenu(29, 5, item.getId());
        printMenu(29, 4, item.getDescription());
    }
}

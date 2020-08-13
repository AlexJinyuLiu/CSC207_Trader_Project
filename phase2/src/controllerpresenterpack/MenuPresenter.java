package controllerpresenterpack;

import entitypack.*;
import usecasepack.ItemManager;
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
    public MenuPresenter(String language) {
        try {
            if (language.equals("English")){
                menu = new File("phase2/data/Menu.txt");
            } else if (language.equals("French")){
                menu = new File("phase2/data/FrenchMenu.txt");
            }
            BufferedReader br = new BufferedReader(new FileReader(menu));

            try {
                int menuNum = 0;
                String readBuff = br.readLine();
                // add newline char back into sb
                ArrayList<String> section = new ArrayList<String>();
                while (readBuff != null) {
                    readBuff = readBuff.split("-")[0];
                    if (readBuff.equals("MENU{ ") || readBuff.equals("Alert{ ") || readBuff.equals("Alerte{ ")) {
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
    public String printMenu(int menuIndex, int lineIndex) {
        try{
            return (menusMap.get(menuIndex).get(lineIndex));
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return ("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
        }
    }

    public String printMenu (int menuIndex, int lineIndex, Object variable){
        try{
            return (menusMap.get(menuIndex).get(lineIndex) + variable);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return ("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
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
    public void printTradeToString(ItemManager itemManager, Trade trade){
        // "EntityPack.User 1: " + trade.getUsername1() + "\nEntityPack.User 2: " + trade.getUsername2() +
        //        "\nItems being traded from user 1 to user 2: " + GetItemNamesFromUser1ToUser2(userManager, trade) +
        //"\nItems being traded from user 2 to user 1: " + GetItemNamesFromUser2ToUser1(userManager, trade) +
        //        "\nTime & Date of item exchange: " + trade.getTimeOfTrade().toString() +
        //        "\nLocation of EntityPack.Trade: " + trade.getMeetingPlace() + "\nTradeID: " + trade.getTradeID();
        System.out.println(trade);
        printMenu(32, 1, trade.getUsername1());
        printMenu(32, 2, trade.getUsername2());
        printMenu(32, 3, GetItemNamesFromUser1ToUser2(trade, itemManager));
        printMenu(32, 4, GetItemNamesFromUser2ToUser1(trade, itemManager));
        printMenu(32, 5, trade.getTimeOfTrade().toString());
        printMenu(32, 6, trade.getMeetingPlace());
        printMenu(32, 7, trade.getTradeID());
        // return "EntityPack.User 1: " + trade.getUsername1(); // to be changed
    }
    // helper method which lists the names of the items going from user 1 to user 2 - Louis
    private String GetItemNamesFromUser1ToUser2(Trade trade, ItemManager itemManager){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser2()){
            Item item = itemManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }
    // helper method which lists the names of the items going from user 2 to user 1 - Louis
    private String GetItemNamesFromUser2ToUser1(Trade trade, ItemManager itemManager){
        StringBuilder stringBuilder = new StringBuilder();
        for(int itemID: trade.getItemIDsSentToUser1()){
            Item item = itemManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * Prints a string representation of a TradingUser
     * @param user the EntityPack.User in question.
     */
    public void printTradingUserToString(TradingUser user, ItemManager itemManager){
        StringBuilder userString = new StringBuilder("User: " + user.getUsername() + "\n");
        ArrayList<Item> usersItems = itemManager.getAvailableItems(user.getUsername());

        if (usersItems.size() == 0) {
            userString.append("This User has no items available for trade. \n");
        } else {
            userString.append("Items available for trade: \n");
            for (int i = 0; i < usersItems.size() - 1; i++) {
                String str = usersItems.get(i).getName() + " (ID: " + usersItems.get(i).getId() + "), ";
                userString.append(str);
            }
            String str = usersItems.get(usersItems.size() - 1).getName() + " (ID: " +
                    usersItems.get(usersItems.size() - 1).getId() + ")\n";
            userString.append(str);
        }
        if (user.getWishlistItemNames().size() == 0) {
            userString.append("This User has no items in their wishlist. \n");
        } else {
            userString.append("Wishlist: \n");
            for (int i = 0; i < user.getWishlistItemNames().size() - 1; i++) {
                String str = user.getWishlistItemNames().get(i) + ", ";
                userString.append(str);
            }
            String str = user.getWishlistItemNames().get(user.getWishlistItemNames().size() - 1) + "\n";
            userString.append(str);
        }
        if (user.getFrozen()) {
            userString.append("This user is frozen, and thus cannot make a trade. \n");
        }


        System.out.println(userString.toString());
    }

    /**
     *
     * @param page
     * @param nextPageExists
     * @param allUsers
     * @return
     */
    public boolean printPageOfUsers(int page, boolean nextPageExists, ArrayList<User> allUsers){
        int input = -1;
        StringBuilder usersString = new StringBuilder();
        for(int i = (9 * (page - 1)) + 1; i < (9 * page) + 1; i++){
            try {
                usersString.append("(").append(i).append(") ").append(allUsers.get(i - 1).getUsername()).append("\n");
            } catch (IndexOutOfBoundsException e){
                nextPageExists = false;
                usersString.append("Back to Main Menu");
                // menuPresenter.printMenu(18, 2);
                break;
            }
        }
        if (nextPageExists) {
            usersString.append("(0) next page (current page: ").append(page).append(")").append("\n");
            // menuPresenter.printMenu(18, 3);
        }
        printMenu(35, 0, usersString.toString());
        return nextPageExists;
    }

    public void printBorrowingOnlyUserToString(BrowsingUser user){
        System.out.println("User: " + user.getUsername() + "\nThis user is \"Browsing only\", " +
                "meaning they have no items and cannot trade.");
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

package controllerpresenterpack;

import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.*;

/**
 * A gateway class that handles the input and output to and from files for serialization.
 */
public class FileManager {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    //used provided code in file StudentManager.java as a reference
    //used https://www.tutorialspoint.com/java/java_serialization.htm and
    //https://attacomsian.com/blog/java-write-object-to-file as a reference

    private final static String dir = "phase2/data/"; //file directory

    /**
     * Serializes an object to a file
     * @param obj object to be serialized
     * @param fileName name of file
     */
    private static void saveToFile(Object obj, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(dir + fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes an object from a file
     * @param fileName name of file
     * @return de-serialized object
     */
    private static Object loadFromFile(String fileName){
        Object obj;
        try {
            FileInputStream file = new FileInputStream(dir + fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (Object) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    /**
     * Serializes an admin object to a .ser file
     * @param admin admin which is being saved to a file
     */
    public static void saveAdminToFile(AdminUser admin) {
        saveToFile(admin, "adminUser.ser");
    }

    /**
     * Serializes a UseCasePack.UserManager object to a .ser file
     * @param userManager which is being saved to a file
     */
    public static void saveUserManagerToFile(UserManager userManager) {
        saveToFile(userManager, "userManager.ser");
    }

    /**
     * Serializes a UseCasePack.TradeCreator object to a .ser file
     * @param tradeCreator which is being saved to a file
     */
    public static void saveTradeCreatorToFile(TradeCreator tradeCreator) {
        saveToFile(tradeCreator, "tradeCreator.ser");
    }

    /**
     * Serializes a UseCasePack.ItemManager object to a .ser file
     * @param itemManager which is being saved to a file
     */
    public static void saveItemManagerToFile(ItemManager itemManager){
        saveToFile(itemManager, "itemManager.ser");
    }

    /**
     * Loads Admin from .ser file
     * @return UseCasePack.AdminUser object
     */
    public static AdminUser loadAdminUser(){
        return (AdminUser) loadFromFile("adminUser.ser");
    }

    /**
     * Loads a UseCasePack.UserManager from a .ser file
     * @return the UseCasePack.UserManager object stored in the file
     */
    public static UserManager loadUserManager(){
        return (UserManager) loadFromFile("userManager.ser");
    }

    /**
     * Loads a UseCasePack.TradeCreator from a .ser file
     * @return the UseCasePack.UserManager object stored in the file
     */
    public static TradeCreator loadTradeCreator(){
        return (TradeCreator) loadFromFile("tradeCreator.ser");
    }

    /**
     * Loads a UseCasePack.ItemManager from a .ser file
     * @return the UseCasePack.ItemManager object stored in the file
     */
    public static ItemManager loadItemManager(){
        return (ItemManager) loadFromFile("itemManager.ser");
    }
}
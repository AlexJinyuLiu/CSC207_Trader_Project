import java.io.*;

public class FileManager {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    //used provided code in file StudentManager.java as a reference
    //used https://www.tutorialspoint.com/java/java_serialization.htm and
    //https://attacomsian.com/blog/java-write-object-to-file as a reference

    private final static String dir = "phase1/data/"; //file directory

    /**
     * Serializes an admin object to a .ser file
     * @param admin admin which is being saved to a file
     */
    public static void saveAdminToFile(AdminUser admin) {
        //serializes the given user's information in a .ser file with the title of their username
        try {
            FileOutputStream file = new FileOutputStream(dir + "adminUser.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(admin);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializes a UserManager object to a .ser file
     * @param userManager which is being saved to a file
     */
    public static void saveUserManagerToFile(UserManager userManager) {
        //serializes the given user manager information in a .ser file
        try {
            FileOutputStream file = new FileOutputStream(dir + "userManager.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(userManager);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializes a TradeCreator object to a .ser file
     * @param tradeCreator which is being saved to a file
     */
    public static void saveTradeCreatorToFile(TradeCreator tradeCreator) {
        //serializes the given trade creator information in a .ser file
        try {
            FileOutputStream file = new FileOutputStream(dir + "tradeCreator.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tradeCreator);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Admin from .ser file
     * @return AdminUser object
     */
    public static AdminUser loadAdminUser(){
        // deserializes AdminUser object
        AdminUser adminUsr;
        try {
            FileInputStream file = new FileInputStream(dir + "adminUser.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            adminUsr = (AdminUser) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return adminUsr;
    }

    /**
     * Loads a UserManager from a .ser file
     * @return the UserManager object stored in the file
     */
    public static UserManager loadUserManager(){
        UserManager userManager;
        try {
            FileInputStream file = new FileInputStream(dir + "userManager.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            userManager = (UserManager) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            // ClassNotFoundException if User class is not recognized
            e.printStackTrace();
            return null;
        }
        return userManager;
    }

    /**
     * Loads a TradeCreator from a .ser file
     * @return the UserManager object stored in the file
     */
    public static TradeCreator loadTradeCreator(){
        TradeCreator tradeCreator;
        try {
            FileInputStream file = new FileInputStream(dir + "tradeCreator.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            tradeCreator = (TradeCreator) in.readObject();

            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            // ClassNotFoundException if User class is not recognized
            e.printStackTrace();
            return null;
        }

        return tradeCreator;
    }
}
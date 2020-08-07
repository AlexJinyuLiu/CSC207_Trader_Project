import javax.crypto.SecretKey;
import java.security.*;
import java.util.Scanner;

public class Main {

    private static Encryptor encryptor = new Encryptor();

    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to encrypt (1) or decrypt (2)");
        int numInput = scan.nextInt();
        if (numInput == 1){
            addAndEncryptData();
        } else if (numInput == 2){
            decryptAndPrintData();
        }
    }

    public static void addAndEncryptData(){
        Scanner  scan = new Scanner(System.in);
        String input = null;
        String platform = null;
        String pass = null;
        while (true) {
            System.out.println("Wan't to enter a new login? (enter quit to exit");
            input = scan.nextLine();
            if (input.equals("quit")){
                break;
            }
            System.out.println("Please enter the platform:");
            platform = scan.nextLine();
            System.out.println("Please enter the thing:");
            pass = scan.nextLine();

            encryptor.addLogin(platform, pass);
        }

        //all logins have been added. now encrypt them

        SecretKey myKey = encryptor.encryptLogins();

        encryptor.saveEncryptedDataToFile("EncryptedData.txt");
        encryptor.saveKeyToFile(new KeyWrapper(myKey), "key.txt");

        System.out.println("Files successfully saved (probably)");
    }

    public static void decryptAndPrintData(){
        encryptor.loadKey("key.txt");
        encryptor.loadEncryptedData("EncryptedData.txt");
        encryptor.decryptLogins(encryptor.loadKey("key.txt"),
                encryptor.loadEncryptedData("EncryptedData.txt"));
        Scanner scan = new Scanner(System.in);
        String platform;
        String password;
        while(true) {
            System.out.println("Please enter the platform for which you want to grab the password " +
                    "(enter quit to exit)");
            platform = scan.nextLine();
            if (platform.equals("quit"))
                return;
            password = encryptor.getPasswordFromPlatform(platform);
            if (password == null){
                System.out.println("Could not get password for such a platform. Try again.");
            } else {
                System.out.println("Password: " + password);
            }
        }
    }
}

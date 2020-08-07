import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.ArrayList;

public class Encryptor {

    ArrayList<LoginInfo> loginInfo = new ArrayList<LoginInfo>();

    EncryptedData encryptedData;

    public String getPasswordFromPlatform(String platform){
        for (LoginInfo login : loginInfo){
            if (login.getPlatform().equals(platform)){
                return login.getPassword();
            }
        }
        return null;
    }
    public void addLogin(String platform, String pass){
        loginInfo.add(new LoginInfo(platform, pass));
    }

    public void saveEncryptedDataToFile(String fileName){
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(encryptedData);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveKeyToFile(KeyWrapper obj, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SecretKey loadKey(String fileName){
        KeyWrapper obj;
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (KeyWrapper) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return obj.getKey();
    }

    public EncryptedData loadEncryptedData(String fileName){
        EncryptedData obj;
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (EncryptedData) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    public SecretKey encryptLogins(){
        SecretKey myKey;
        try {
            myKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (Exception e){
            return null;
        }
        Cipher cipher;
        try{
            cipher = Cipher.getInstance("AES");
        } catch (Exception e){
            return null;
        }

        ArrayList<byte[]> encryptedPasses = new ArrayList<byte[]>();
        ArrayList<byte[]> encryptedPlatforms = new ArrayList<byte[]>();
        for (LoginInfo login : loginInfo){
            try{
                byte[] pass = login.getPassword().getBytes("UTF-8");
                cipher.init(Cipher.ENCRYPT_MODE, myKey);
                byte[] encryptedPass = cipher.doFinal(pass);
                encryptedPasses.add(encryptedPass);

                byte[] platform = login.getPlatform().getBytes("UTF-8");
                byte[] encryptedPlatform = cipher.doFinal(platform);
                encryptedPlatforms.add(encryptedPlatform);
            } catch(Exception e){
                return null;
            }
        }
        encryptedData = new EncryptedData(encryptedPlatforms, encryptedPasses);

        return myKey;
    }

    public void decryptLogins(SecretKey key, EncryptedData data){

        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            ArrayList<byte[]> encryptedPasswords = data.encryptedPasses;
            ArrayList<byte[]> encryptedPlatforms = data.encryptedPlatforms;
            String decryptedPassword;
            String decryptedPlatform;
            for (int i = 0; i < encryptedPasswords.size(); i++){
                decryptedPassword = new String(cipher.doFinal(encryptedPasswords.get(i)));
                decryptedPlatform = new String(cipher.doFinal(encryptedPlatforms.get(i)));
                addLogin(decryptedPlatform, decryptedPassword);
            }
        } catch (Exception e){
            return;
        }
    }

}

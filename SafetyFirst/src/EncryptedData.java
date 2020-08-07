import java.io.Serializable;
import java.util.ArrayList;

public class EncryptedData implements Serializable {
    
    public ArrayList<byte[]> encryptedPasses = new ArrayList<byte[]>();
    public ArrayList<byte[]> encryptedPlatforms = new ArrayList<byte[]>();
    
    public EncryptedData(ArrayList<byte[]> encryptedPlatforms, ArrayList<byte[]> encryptedPasses){
        this.encryptedPasses = encryptedPasses;
        this.encryptedPlatforms = encryptedPlatforms;
    }
    
    
}

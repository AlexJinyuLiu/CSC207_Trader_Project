public class LoginInfo {
    
    private String platform;
    
    private String password;
    
    public LoginInfo(String platform, String password){
        this.platform = platform;
        this.password = password;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getPassword(){
        return this.password;
    }
}

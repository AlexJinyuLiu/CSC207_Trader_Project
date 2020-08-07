package entitypack;


/**
 * An entity class storing login info for a single admin account.
 */
public class AdminLogin {

    private String username;
    private String password;

    /**
     * Initialize a new AdminLogin with a specified username and password
     * @param username the username of the login
     * @param password the matching password
     */
    public AdminLogin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}

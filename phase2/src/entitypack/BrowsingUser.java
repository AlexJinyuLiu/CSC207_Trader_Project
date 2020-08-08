package entitypack;

import java.io.Serializable;

/**
 * A class defining the data associated with a user who cannot make any trades, but only browses.
 */
public class BrowsingUser implements Serializable, User {

    private String username;
    private String password;
    private MetroArea metro;

    /**
     *
     * @param pass string to be checked against this user's password.
     * @return whether the entered string matches this user's password.
     */
    public boolean checkPassword(String pass){return pass.equals(password);}

    /**
     *
     * @param
     */
    public String getUsername() {return username;}

    /**
     *
     * @return the password of this user
     */
    public String getPassword() { return password; }

    public BrowsingUser(String username){
        this.username = username;
    }

    public MetroArea getMetro(){
        return this.metro;
    }

    public void setMetro(MetroArea metro){
        this.metro = metro;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

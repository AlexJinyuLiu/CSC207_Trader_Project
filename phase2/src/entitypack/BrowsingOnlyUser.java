package entitypack;

import java.io.Serializable;

/**
 * A class defining the data associated with a user who cannot make any trades, but only browses.
 */
public class BrowsingOnlyUser implements Serializable, User {

    private String username;

    private String password;

    private MetroArea metro;

    /**
     *
     * @param pass string to be checked against this user's password.
     * @return whether the entered string matches this user's password.
     */
    public boolean checkPassword(String pass){return pass.equals(password);}


    public BrowsingOnlyUser(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
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

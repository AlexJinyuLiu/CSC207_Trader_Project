package entitypack;

import java.io.Serializable;

public abstract class LoginAccount implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

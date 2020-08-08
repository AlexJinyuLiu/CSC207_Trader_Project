package usecasepack;

import entitypack.LoginAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ValidateLoginStrategy implements Serializable {

    public boolean validateLogin(String username, String password, HashMap<String, String> validLogins) {
        String loginUsername = null;
        for (String adminUsername : validLogins.keySet()) {
            if (adminUsername.equals(adminUsername)) {
                loginUsername = adminUsername;
            }
        }
        if (loginUsername != null) {
            if (validLogins.get(loginUsername).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean usernameAvailable(String username, HashMap<String, String> validLogins) {
        for (String adminUsername : validLogins.keySet()) {
            if (adminUsername.equals(username)) {
                return false;
            }
        }
        return true;
    }
}
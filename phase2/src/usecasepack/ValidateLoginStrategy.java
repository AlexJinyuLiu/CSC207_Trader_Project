package usecasepack;

import java.io.Serializable;
import java.util.HashMap;

public class ValidateLoginStrategy implements Serializable {

    public boolean validateLogin(String username, String password, HashMap<String, String> validLogins) {
        String login = null;
        for (String loginUsername : validLogins.keySet()) {
            if (loginUsername.equals(username)) {
                login = loginUsername;
            }
        }
        if (login != null) {
            if (validLogins.get(login).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean usernameAvailable(String username, HashMap<String, String> validLogins) {
        System.out.println(validLogins);
        if (validLogins.isEmpty()) {
            return true;
        }
        for (String loginUsername : validLogins.keySet()) {
            if (loginUsername.equals(username)) {
                return false;
            }
        }
        return true;
    }
}
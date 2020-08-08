package usecasepack;

import entitypack.AdminLogin;
import entitypack.MetroArea;

import java.util.HashMap;

public interface AccountDataOperations {

    boolean validateLogin(String username, String password, HashMap<String, String> validLogins);

    boolean usernameAvailable(String username, HashMap<String, String> validLogins);

}

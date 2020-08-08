package usecasepack;

import entitypack.AdminLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminValidateLoginStrategy extends ValidateLoginStrategy implements Serializable {

    public HashMap<String, String> turnListIntoHashMap(ArrayList<AdminLogin> logins) {
        HashMap<String, String> loginHashMap = new HashMap<String, String>();
        for (AdminLogin login : logins) {
            loginHashMap.put(login.getUsername(), login.getPassword());
        }
        return loginHashMap;
    }
}

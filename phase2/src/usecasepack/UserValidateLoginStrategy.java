package usecasepack;

import entitypack.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserValidateLoginStrategy extends ValidateLoginStrategy implements Serializable {

    public HashMap<String, String> turnListIntoHashMap(ArrayList<User> logins) {
        HashMap<String, String> loginHashMap = new HashMap<String, String>();
        for (User login : logins) {
            loginHashMap.put(login.getUsername(), login.getPassword());
        }
        return loginHashMap;

    }
}

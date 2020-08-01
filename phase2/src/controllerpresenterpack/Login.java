package controllerpresenterpack;

import entitypack.MetroArea;
import usecasepack.AccountDataOperations;

public interface Login {

    boolean validateLogin(AccountDataOperations loginData, String username, String password);

    boolean addNewLogin(AccountDataOperations loginData, String username, String password, boolean isTrading,
                        MetroArea metro);
}

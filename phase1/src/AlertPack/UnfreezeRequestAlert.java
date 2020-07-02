package AlertPack;

import java.io.Serializable;

public class UnfreezeRequestAlert extends FreezeUserAlert implements Serializable {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private String lent; // amount user has lent
    private String borrowed; // amount user has borrowed

    public UnfreezeRequestAlert(String username, String lent, String borrowed) {
        super(username, lent, borrowed);
    }

    /**
     * toString() method to return the user's stats to the admin
     * @return a string containing the user's stats and menu options for the admin
     */
    @Override
    public String toString() {
        return "Unfreeze User Request Alert" +
                "\n" + username + " has lent: " + lent + " items" +
                "\n" + username + " has borrowed: " + borrowed + " items";
    }

}

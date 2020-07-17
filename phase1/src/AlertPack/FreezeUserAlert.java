package AlertPack;

import java.io.Serializable;

public class FreezeUserAlert extends AdminAlert implements Serializable {
    /** Alert which is generated when a User crosses the threshold of borrowed minus lent that they are required to
     * maintain. The admin will then decide to freeze the user or leave the user unfrozen.
     */

    //group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private int lent; // amount user has lent
    private int borrowed; // amount user has borrowed
    private int thresholdRequired; // difference needed between lent and borrowed

    /** Constructor for an alert that tells the admin that the system has identified a user to freeze.
     *
     * @param username username of the user to be frozen.
     * @param lent the number of items the user has borrowed.
     * @param borrowed the number items the user has borrowed.
     * @param thresholdRequired the threshold the user is required to maintain.
     */
    public FreezeUserAlert(String username, int lent, int borrowed, int thresholdRequired){
        super(2);
        this.username = username;
        this.lent = lent;
        this.borrowed = borrowed;
        this.thresholdRequired = thresholdRequired;
    }

    /**
     *
     * @return returns the username of the user who should be frozen.
     */
    public String getUsername(){ return username; }

    /**
     *
     * @return the number of items the user has lent.
     */
    public int getLent(){ return lent; }

    /**
     *
     * @return the number of items the user has borrowed.
     */
    public int getBorrowed(){ return borrowed; }

    /**
     *
     * @param username the username of the user who should be frozen.
     */
    public void setUsername(String username){ this.username = username; }

    /**
     *
     * @param lent the number of items the user has lent.
     */
    public void setLent(int lent){ this.lent = lent; }

    /**
     *
     * @param borrowed the number of items the user has borrowed.
     */
    public void setBorrowed(int borrowed){ this.borrowed = borrowed; }

    /**
     *
     * @return the threshold of lent - borrowed that the user should be above.
     */
    public int getThresholdRequired(){
        return this.thresholdRequired;
    }


}

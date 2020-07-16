package AlertPack;

import java.io.Serializable;

public class UnfreezeRequestAlert extends AdminAlert implements Serializable {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private int lent; // amount user has lent
    private int borrowed; // amount user has borrowed
    private int thresholdRequired; // difference needed between lent and borrowed
    private int incompleteT;
    private int incompThreshold;

    public UnfreezeRequestAlert(String username, int lent, int borrowed, int thresholdRequired, int incompleteTrades,
                                int incompleteThresholdRequired){
        super(3);
        this.username = username;
        this.lent = lent;
        this.borrowed = borrowed;
        this.thresholdRequired = thresholdRequired;
        this.incompleteT = incompleteTrades;
        this.incompThreshold = incompleteThresholdRequired;
    }

    /**
     *
     * @return the username of the user who sends the unfreeze request
     */
    public String getUsername(){ return username; }

    /**
     *
     * @return the number of the items lent by the user
     */
    public int getLent(){ return lent; }

    /**
     *
     * @return the number of items borrowed by the user
     */
    public int getBorrowed(){ return borrowed; }

    /**
     *
     * @return the number of items that a user is required to lend before borrowing
     */
    public int getThresholdRequired(){
        return this.thresholdRequired;
    }

    /**
     *
     * @return the number of incomplete trades that this user has made
     */
    public int getIncompleteT() {return this.incompleteT;}

    /**
     *
     * @return the allowed number of incomplete trades that a user can make
     */
    public int getIncompThreshold() {return this.incompThreshold;}
}

package AlertPack;

import java.io.Serializable;

/**
 * An admin alert that is sent to an admin when a user requests to unfreeze their account.
 */
public class UnfreezeRequestAlert extends AdminAlert implements Serializable {
    //author: Callan Murphy in group 0110 for CSC207H1 summer 2020 project
    private String username; // username of the user
    private int lent; // amount user has lent
    private int borrowed; // amount user has borrowed
    private int thresholdRequired; // difference needed between lent and borrowed
    private int incompleteT;
    private int incompThreshold;

    /**
     * Initializes a new unfreezeRequestAlert, storing the username of the user in question, the number of items they've
     * lent and borrowed, the threshold of items lent/items borrowed required, their number of incomplete trades, and
     * the threshold of incomplete trades they must remain under.
     * @param username the username of the user
     * @param lent the number of items that user has lent
     * @param borrowed the number of items that user has borrowed
     * @param thresholdRequired the threshold of items lent/borrowed required.
     * @param incompleteTrades the number of incomplete trades this user has.
     * @param incompleteThresholdRequired the threshold of incomplete trades they must stay under.
     */
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

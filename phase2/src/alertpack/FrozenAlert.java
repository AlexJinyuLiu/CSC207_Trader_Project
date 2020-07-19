package alertpack;

import java.io.Serializable;

public class FrozenAlert extends UserAlert implements Serializable {
    /** Alert which tells a user that they have been frozen by an admin. Displays the number of items borrowed and lent.
     * Also displays the number on incomplete trades and the thresholds of incomplete trades and lent minus borrowed
     * the user is required to maintain.
     */

    private int numBorrowedofUser;
    private int numLentofUser;
    private int thresholdNumofUser;
    private int numIncomplete;
    private int thresholdIncomplete;

    /** Constructor which will be called when a user is frozen by an admin.
     *
     * @param numBorrowed the total amount of incoming items a user has traded.
     * @param numLent the total amount of outgoing items a user has traded.
     * @param threshholdNum the number of lent minus borrowed a user is required to maintain.
     * @param numIncomp the number of incomplete trades attributed to the user.
     * @param thresholdIncomp the threshold of incomplete trades before a user is frozen.
     */
    public FrozenAlert(int numBorrowed, int numLent, int threshholdNum, int numIncomp, int thresholdIncomp){
        super(0);
        numBorrowedofUser = numBorrowed;
        numLentofUser = numLent;
        thresholdNumofUser = threshholdNum;
        numIncomplete = numIncomp;
        thresholdIncomplete = thresholdIncomp;

    }

    /**
     *
     * @return The number of items the user has borrowed.
     */
    public int getNumBorrowedofUser() {
        return numBorrowedofUser;
    }

    /**
     *
     * @return the number of items the user has lent.
     */
    public int getNumLentofUser() {
        return numLentofUser;
    }

    /**
     *
     * @return the threshold of lent - borrowed that each user should be greater than or equal to.
     */
    public int getThreshholdNumofUser() {
        return thresholdNumofUser;
    }

    /**
     *
     *  @return the number of incomplete transactions of this user
     */
    public int getNumIncomplete() { return numIncomplete; }

    /**
     *
     * @return the incompleteThreshold
     */
    public int getThresholdIncomplete() { return thresholdIncomplete; }
}

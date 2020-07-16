package AlertPack;

import java.awt.*;
import java.io.Serializable;

public class FrozenAlert extends UserAlert implements Serializable {

    protected int numBorrowedofUser;
    protected int numLentofUser;
    protected int thresholdNumofUser;
    protected int numIncomplete;
    protected int thresholdIncomplete;

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

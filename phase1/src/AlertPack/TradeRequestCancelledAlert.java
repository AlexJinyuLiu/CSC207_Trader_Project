package AlertPack;

import java.io.Serializable;

/**
 * A user alert that alerts a user when their trade request has been cancelled because one of the items in that request
 * has been traded away to annother user.
 */
public class TradeRequestCancelledAlert extends UserAlert implements Serializable {
    //author: Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    protected int tradeID;

    /**
     * Constructs a new TradeRequestCancelledAlert, storing a unique TradeID
     * @param tradeID the unique ID of the trade that was cancelled.
     */
    public TradeRequestCancelledAlert(int tradeID){
        super(9);
        this.tradeID = tradeID;
    }

    /**
     *
     * @return the ID of the trade request cancelled
     */
    public int getTradeID() {
        return tradeID;
    }

}

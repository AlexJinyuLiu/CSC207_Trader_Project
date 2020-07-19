package alertpack;

import java.io.Serializable;

public class TradeCancelledAlert extends UserAlert implements Serializable {
    /** Alert which tells a user that a pending trade in which they were involved has been cancelled as one of the users
     * is no longer in possession of one of the items that was to be traded.
     */
    //author: group 0110 for CSC207H1 summer 2020 project
    protected int tradeID;

    /** Called when a pending trade is cancelled due to item unavailability.
     *
     * @param tradeID the id number of the trade.
     */
    public TradeCancelledAlert(int tradeID){
        super(5);
        System.out.println("EntityPack.Trade " + tradeID + " has been cancelled. Generating alerts...");
        this.tradeID = tradeID;
    }

    /**
     *
     * @return the ID of the trade cancelled (item no longer available to be traded)
     */
    public int getTradeID() {
        return tradeID;
    }

}

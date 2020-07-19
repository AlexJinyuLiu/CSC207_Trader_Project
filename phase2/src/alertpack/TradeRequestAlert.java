package alertpack;

import java.io.Serializable;

/**
 * A user alert that alerts a user when they have recieved a trade request from annother user.
 */
public class TradeRequestAlert extends UserAlert implements Serializable {
    private String senderUserName;
    private int tradeID;
    private boolean isTempTrade;

    /**
     * Constructs a new TradeRequestAlert, storing data about the sender and trade.
     * @param senderUserName the username of the user who sent the trade request
     * @param tradeID the ID of the trade
     * @param isTempTrade a boolean representing whether or not the trade was accepted.
     */
    public TradeRequestAlert(String senderUserName, int tradeID, boolean isTempTrade){
        super(8);
        this.senderUserName = senderUserName;
        this.tradeID = tradeID;
        this.isTempTrade = isTempTrade;
    }

    /**
     *
     * @return username of the person who has proposed the trade.
     */
    public String getSenderUserName() {
        return senderUserName;
    }

    /**
     *
     * @return the ID of the trade request proposed
     */
    public int getTradeID(){ return tradeID; }

    public boolean getIsTempTrade(){
        return this.isTempTrade;
    }
}

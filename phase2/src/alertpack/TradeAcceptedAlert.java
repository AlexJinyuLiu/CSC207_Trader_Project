package alertpack;

import java.io.Serializable;

public class TradeAcceptedAlert extends UserAlert implements Serializable {
    /** An alert which tells a user that a trade they have proposed has been accepted.
     *
     */

    private String acceptingUsername;
    private int tradeID;

    /** Called when a trade request has been accepted.
     *
     * @param acceptingUsername The username of the user which has accepted the trade.
     * @param tradeID The ID number of the trade.
     */
    public TradeAcceptedAlert(String acceptingUsername, int tradeID) {
        super(4);
        this.acceptingUsername = acceptingUsername;
        this.tradeID = tradeID;
    }

    /**
     *
     * @return the username of the user who accepts the trade
     */
    public String getAcceptingUsername(){
        return this.acceptingUsername;
    }

    /**
     *
     * @return the ID of the trade accepted
     */
    public int getTradeID(){
        return tradeID;
    }

}
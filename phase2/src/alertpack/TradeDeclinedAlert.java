package alertpack;

import java.io.Serializable;

/**
 * An user alert sent to a user when a trade request they have sent has been declined by the recieving user.
 */
public class TradeDeclinedAlert extends UserAlert implements Serializable {
    private String decliningUserName;
    private int tradeID;

    /**
     * Constructs a TradeDeclinedAlert, storing data about the declining user and the trade.
     * @param decliningUserName the username of the user that declined the trade request.
     * @param tradeID the unique ID of the trade that was declined.
     */
    public TradeDeclinedAlert(String decliningUserName, int tradeID){
        super(6);
        this.decliningUserName = decliningUserName;
        this.tradeID = tradeID;
    }

    /**
     *
     * @return the username of the user who declines the trade
     */
    public String getDecliningUserName(){
        return this.decliningUserName;
    }

    /**
     *
     * @return the ID of the trade declined by a user
     */
    public int getTradeID(){
        return this.tradeID;
    }
}

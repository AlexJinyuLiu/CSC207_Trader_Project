package AlertPack;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExpirationAlert extends UserAlert implements Serializable {
    /** An alert which is tells a user that a temporary trade they are involved in has expired.
     *
     */

    private LocalDateTime dueDate;
    private String username;
    private int tradeId;

    /** Called when a temporary trade has expired.
     *
     * @param dueDate the time & date at which the temporary trade expired.
     * @param username the username with whom the user conducted the trade with.
     * @param tradeId the ID number of the trade.
     */
    public ExpirationAlert(LocalDateTime dueDate, String username, int tradeId){
        super(2);
        this.dueDate = dueDate;
        this.username = username;
        this.tradeId = tradeId;
    }

    /**
     *
     * @return The date & time at which the temporary trade is over and the items should be returned.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     *
     * @return returns the username of the user this alert is being sent to.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the ID of the temporary trade.
     */
    public int getTradeId() {
        return tradeId;
    }


}

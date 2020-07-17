package AlertPack;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A user alert that is sent to a user when the meetup date for one of their trades has passed.
 */
public class TradePastDateAlert extends UserAlert implements Serializable {

    private LocalDateTime dueDate;
    private String username;
    private int tradeId;

    /**
     * Constructs a new TradePastDateAlert, storing data about when the trade was due to happen and the other user
     * involved.
     * @param dueDate A LocalDateTimeObject
     * @param username The username of the user.
     * @param tradeId the unique ID of the trade.
     */
    public TradePastDateAlert(LocalDateTime dueDate, String username, int tradeId){
        super(7);
        this.dueDate = dueDate;
        this.username = username;
        this.tradeId = tradeId;
    }

    /**
     *
     * @return the due date of the trade.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }


    /**
     *
     * @return the username of the user involved in the trade.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return trade id.
     */
    public int getTradeId() {
        return tradeId;
    }

}

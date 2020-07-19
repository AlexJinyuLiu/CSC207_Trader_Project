package alertpack;

import java.io.Serializable;

public class ReportAlert extends AdminAlert implements Serializable {
    /** Alert in which a user has been reported by another user for not showing up to a trade.
     *
     */

    private String senderUserName; // username of the user who is sending the report
    private String reportedUserName; // username of the user who is being reported
    private boolean isTradeComplete;
    private String reportDescription; // provision to describe why the report is being sent

    /** Called when a user has been reported.
     *
     * @param senderUserName the username of the person sending the report.
     * @param reportedUserName the username of the user being reported.
     * @param isTradeComplete whether or not the trade has been completed.
     * @param reportDescription a description of why a user is being reported.
     */
    public ReportAlert(String senderUserName, String reportedUserName, boolean isTradeComplete,
                       String reportDescription) {
        super(1);
        this.senderUserName = senderUserName;
        this.reportedUserName = reportedUserName;
        this.isTradeComplete = isTradeComplete;
        this.reportDescription = reportDescription;
    }

    /**
     *
     * @return the username of the user who is sending the report.
     */
    public String getSenderUserName() {
        return senderUserName;
    }

    /**
     *
     * @return the username of the user who is being reported.
     */
    public String getReportedUserName() {
        return reportedUserName;
    }

    /**
     *
     * @return whether the trade between users is incomplete.
     */
    public boolean getIsTradeComplete() {
        return isTradeComplete;
    }

    /**
     *
     * @return the description by the sending user as to why the reported user is being reported.
     */
    public String getReportDescription() {
        return reportDescription;
    }


}


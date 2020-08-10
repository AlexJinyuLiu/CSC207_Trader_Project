package controllerpresenterpack;

import alertpack.ExpirationAlert;
import alertpack.TradePastDateAlert;
import alertpack.UserAlert;
import entitypack.TemporaryTrade;
import entitypack.Trade;
import usecasepack.AdminUser;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StartupController {

    /**
     * Called on startup. Handles all startup functionality for users and admins.
     */
    public void onStartUp(AdminUser adminUser, UserManager userManager, TradeCreator tradeCreator){
        userManager.onStartUp(tradeCreator);
        adminUser.onStartUp(userManager, tradeCreator);
        tradeCreator.onStartup();

        checkForExpiredTempTrades(tradeCreator);
        checkForPastDateTrades(tradeCreator);
    }

    /**
     * Checks for any expired temporary trades, and then alerts the users involved that they must return their items.
     */
    private void checkForExpiredTempTrades(TradeCreator tradeCreator){
        ArrayList<TemporaryTrade> expiredTempTrade = tradeCreator.getTradeHistories().fetchExpiredTempTrades();

        for(TemporaryTrade tempTrade : expiredTempTrade) {
            LocalDateTime dueDate = tempTrade.getDueDate();
            int tradeID = tempTrade.getTradeID();

            if (!tempTrade.getUser1ItemReturnRequestAccepted()) {
                UserAlert alert = new ExpirationAlert(dueDate, tempTrade.getUsername1(), tradeID);
                tradeCreator.alertUser(tempTrade.getUsername1(), alert);
            }
            if (!tempTrade.getUser2ItemReturnRequestAccepted()) {
                UserAlert alert = new ExpirationAlert(dueDate, tempTrade.getUsername2(), tradeID);
                tradeCreator.alertUser(tempTrade.getUsername2(), alert);
            }
        }
    }

    /**
     * Checks for trades that have passed and alerts users that they must confirm whether or not the trade has happened.
     */
    private void checkForPastDateTrades(TradeCreator tradeCreator){
        ArrayList<Trade> pastDateTrades = tradeCreator.fetchPastDateTrades();
        for (Trade trade : pastDateTrades) {
            UserAlert alertUser1 = new TradePastDateAlert(trade.getTimeOfTrade(), trade.getUsername1(),
                    trade.getTradeID());
            UserAlert alertUser2 = new TradePastDateAlert(trade.getTimeOfTrade(), trade.getUsername2(),
                    trade.getTradeID());
            tradeCreator.alertUser(trade.getUsername1(), alertUser1);
            tradeCreator.alertUser(trade.getUsername2(), alertUser2);
            trade.setUsersAlertedToPastDue(true);
        }
    }


}

package controllerpresenterpack;

public class ControllerPresenterGrouper {

    public GuiMenuPresenter menuPresenter;
    public AdminActions adminActions;
    public TradingUserActions tradingUserActions;
    public BrowsingUserActions browsingUserActions;

    public ControllerPresenterGrouper(AdminActions adminActions,
                                      TradingUserActions tradingUserActions, BrowsingUserActions browsingUserActions){
        this.adminActions = adminActions;
        this.tradingUserActions = tradingUserActions;
        this.browsingUserActions = browsingUserActions;
    }

    public void setMenuPresenter(GuiMenuPresenter guiMenuPresenter){
        this.menuPresenter = guiMenuPresenter;
    }
}

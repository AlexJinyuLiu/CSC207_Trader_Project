package controllerpresenterpack;

public class ControllerPresenterGrouper {

    public GuiMenuPresenter menuPresenter;
    public AdminActions adminActions;
    public TradingUserActions tradingUserActions;
    public BrowsingUserActions browsingUserActions;
    public UserAlertManager userAlertManager;
    public AdminAlertManager adminAlertManager;

    public ControllerPresenterGrouper(AdminActions adminActions,
                                      TradingUserActions tradingUserActions, BrowsingUserActions browsingUserActions,
                                      UserAlertManager userAlertManager, AdminAlertManager adminAlertManager){
        this.adminActions = adminActions;
        this.tradingUserActions = tradingUserActions;
        this.browsingUserActions = browsingUserActions;
        this.userAlertManager = userAlertManager;
        this.adminAlertManager = adminAlertManager;
    }

    public void setMenuPresenter(GuiMenuPresenter guiMenuPresenter){
        this.menuPresenter = guiMenuPresenter;
    }
}

package usecasepack;

public interface Prompt {

    /**
     * Handles the Prompt.
     */
    void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                TradeCreator tradeCreator, ItemManager itemManager);
}

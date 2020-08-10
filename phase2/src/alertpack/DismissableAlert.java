package alertpack;

import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public interface DismissableAlert {

    public String getAlertText(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                               TradeCreator tradeCreator, ItemManager itemManager);
}

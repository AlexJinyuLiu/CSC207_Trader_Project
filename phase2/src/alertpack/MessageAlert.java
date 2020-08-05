package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;

public class MessageAlert extends UserAlert implements Serializable {
    /** Alert which gives a message to a user from another user.
     *
     */
    //group 0110 for CSC207H1 summer 2020 project
    private String message;
    private String senderUsername;

    /** Called when a user messages another user.
     *
     * @param message the text of the message.
     * @param senderUsername The user from which it is sent.
     */
    public MessageAlert(String senderUsername, String message){
        super(3);
        this.message = message;
        this.senderUsername = senderUsername;
    }

    /** Handles the message alert by displaying the message to the user receiving it.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter)menuPresenterObject;
        // "From: " + a.getSenderUsername() + "\n" + a.getMessage()
        // String desc = menuPresenter.printMenu(31, 1, getSenderUsername()) +
        //        menuPresenter.printMenu(35, 0, getMessage());
        String desc = menuPresenter.getText(Frame.MESSAGEALERT, 1) + getSenderUsername() +
                menuPresenter.getText(Frame.MESSAGEALERT, 2) + getMessage();
        /**boolean handled = false;

        int input = 0;

        while (!handled){
            Scanner scan = new Scanner(System.in);
            // "(1) Dismiss"
            menuPresenter.printMenu(31, 2);
            input = scan.nextInt();
            if (input == 1) handled = true;
        }**/

        DismissibleAlertPrompt messageAlert = new DismissibleAlertPrompt(desc, menuPresenter);

    }
    /**
     *
     * @return the username of the sender.
     */
    public String getSenderUsername(){
        return this.senderUsername;
    }

    /**
     *
     * @return the message being sent.
     */
    public String getMessage(){
        return this.message;
    }
}

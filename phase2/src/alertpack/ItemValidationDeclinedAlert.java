package alertpack;

import controllerpresenterpack.MenuPresenter;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.Scanner;

public class ItemValidationDeclinedAlert extends UserAlert implements Serializable {
    /** An alert which is created when an admin denies a ItemValidationRequest. Tells the user why the request was
     * declined.
     */

    //group 0110 for CSC207H1 summer 2020 project
    private String usernameOfOwner;
    private String name;
    private String description;
    private final int itemID;
    private String message;

    /** 5 Argument constructor.
     * Created when an ItemValidationRequestAlert is declined by the admin.
     *
     * @param owner the owner of the proposed item.
     * @param obj the name of the proposed item.
     * @param desc the description of the proposed item.
     * @param itemID the ID number of the proposed item.
     * @param message A message given by the admin, as to why the request was declined.
     */
    public ItemValidationDeclinedAlert(String owner, String obj, String desc, int itemID, String message){
        super(1);
        this.description = desc;
        this.name = obj;
        this.usernameOfOwner = owner;
        this.itemID = itemID;
        this.message = message;
    }

    /** 4 Argument constructor.
     * Created when an ItemValidationRequestAlert is declined by the admin.
     *
     * @param owner the owner of the proposed item.
     * @param obj the name of the proposed item.
     * @param itemID the ID number of the proposed item.
     * @param message A message given by the admin, as to why the request was declined.
     */
    public ItemValidationDeclinedAlert(String owner, String obj, int itemID, String message){
        super(1);
        this.name = obj;
        this.usernameOfOwner = owner;
        this.itemID = itemID;
        this.message = message;
    }

    /** Handles the ItemValidationDeclinedAlert by informing the user and prompting them to make a new validation
     * request.
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                       TradeCreator tradeCreator, ItemManager itemManager){
        MenuPresenter menuPresenter = (MenuPresenter) menuPresenterObject;

        //"Your item validation request has been declined for the following reason: \n" +
        //        a.getMessage()+ ".\nUser: " + a.getOwner() + "EntityPack.Item name: " + a.getName() + "\nEntityPack.Item description: " +
        //        a.getDescription() + "\nItem ID number: " + a.getItemID()
        menuPresenter.printMenu(29, 1);
        menuPresenter.printMenu(29, 2);
        menuPresenter.printMenu(29, 3);
        menuPresenter.printMenu(29, 4);
        menuPresenter.printMenu(29, 5);
        // "(1) Dismiss"
        menuPresenter.printMenu(29, 6);
        //"(2) Send a new item validation request"
        menuPresenter.printMenu(29, 7);
        Scanner scan = new Scanner(System.in);
        // "Please enter one of the numbers listed above"
        menuPresenter.printMenu(5, 1);
        int choice = scan.nextInt();
        while(choice > 2 || choice < 0){
            // "The number you entered was not listed above. Please enter a choice between 1 and " + x
            menuPresenter.printMenu(5, 2);
            choice = scan.nextInt();
        }
        if (choice == 2){
            String name = null;
            // "Please enter the name of your item"
            menuPresenter.printMenu(29, 8);
            scan.nextLine(); //This awfulness is needed to prevent it from skipping a line. - Louis
            name = scan.nextLine();
            // "Please enter the item description"
            menuPresenter.printMenu(29, 9);
            String description = scan.nextLine();
            String username = getOwner();
            int itemID = userManager.getNewItemID();

            ItemValidationRequestAlert alert = new ItemValidationRequestAlert(itemID, username, name, description);
            userManager.alertAdmin(alert);
        }
    }

    /**
     *
     * @return the name or title of the item.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the username of the user who is submitting the item validation request.
     */
    public String getOwner() {
        return usernameOfOwner;
    }

    /**
     *
     * @return the description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the admin message attached to the alert.
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return the item ID of the item validation request that was declined.
     */
    public int getItemID(){ return itemID; }
}

package alertpack;

import controllerpresenterpack.GuiMenuPresenter;
import controllerpresenterpack.MenuPresenter;
import entitypack.Frame;
import usecasepack.AdminUser;
import usecasepack.ItemManager;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.io.Serializable;
import java.util.Scanner;

public class ItemValidationRequestAlert extends AdminAlert implements Serializable{
    /** AdminAlert which is created from ControllerPresenterPack.UserActions when a user requests that an item be validated.
     *
     */

    //group 0110 for CSC207H1 summer 2020 project
    private String usernameOfOwner;
    private String name;
    private String description;
    private final int itemID;
    private static int idGenerator = 0;

    /** 4 argument constructor. Called when a user has requested to validate and item.
     *
     * @param itemID the ID number of the proposed item.
     * @param owner the owner of the proposed item.
     * @param obj the name of the proposed item.
     * @param desc a description of the proposed item.
     */
    public ItemValidationRequestAlert(int itemID, String owner, String obj, String desc){
        super(0);
        this.description = desc;
        this.name = obj;
        this.usernameOfOwner = owner;
        this.itemID = itemID;
        idGenerator++;
    }

    /** 3 argument constructor. Called when a user has requested to validate and item.
     *
     * @param itemID the ID number of the proposed item.
     * @param owner the owner of the proposed item.
     * @param obj the name of the proposed item.
     */
    public ItemValidationRequestAlert(int itemID, String owner, String obj){
        super(0);
        this.name = obj;
        this.usernameOfOwner = owner;
        this.itemID = itemID;
        idGenerator++;
    }

    /** Method that handles an ItemValidationRequestAlert by approving or denying the request
     *
     */
    public void handle(Object menuPresenterObject, AdminUser adminUser, UserManager userManager,
                        TradeCreator tradeCreator, ItemManager itemManager){
        GuiMenuPresenter menuPresenter = (GuiMenuPresenter) menuPresenterObject;
        // "EntityPack.Item validation request\nUser: " + alert.getOwner() + "\nEntityPack.Item name: " + alert.getName() +
        //         "\nItem description: " + alert.getDescription() + "\nItem ID number: " + alert.getItemID()
        /**menuPresenter.printMenu(11,0);
        menuPresenter.printMenu(11,1, getOwner()) ;
        menuPresenter.printMenu(11,2, getName());
        menuPresenter.printMenu(11,3, getDescription());
        menuPresenter.printMenu(11,4, getItemID());

        Scanner scanner = new Scanner(System.in);
        String message;
        // "(1) Approve this item"
        menuPresenter.printMenu(11,5);
        // "(2) Deny this item"
        menuPresenter.printMenu(11,6);
        // "Please enter one of the numbers listed above"
        menuPresenter.printMenu(5,1);
        int choice = scanner.nextInt();
        while(choice > 2 || choice < 0){
            // "The number you entered was not listed above. Please enter a choice between 1 and " + x
            menuPresenter.printMenu(5,2, 2);
            choice = scanner.nextInt();
        }
        if (choice == 2){
            // "Please enter a reason why this request was declined."
            menuPresenter.printMenu(11,7);
            message = scanner.next();
        }else{
            message = "";
        }
        if (choice == 1) {
            adminUser.pollValidationRequest(userManager, itemManager, getOwner(), getName(), getItemID(),
                    getDescription());
        } else{
            UserAlert declinedAlert = new ItemValidationDeclinedAlert(getOwner(), getOwner(),
                    getDescription(), getItemID(), message);
            userManager.alertUser(getOwner(), declinedAlert);
        }**/

        String desc = menuPresenter.getText(Frame.ITEMVALIDATIONREQUESTALERT,0) +
                menuPresenter.getText(Frame.ITEMVALIDATIONREQUESTALERT,1, getOwner()) +
                menuPresenter.getText(Frame.ITEMVALIDATIONREQUESTALERT,2, getName()) +
                menuPresenter.getText(Frame.ITEMVALIDATIONREQUESTALERT,3, getDescription()) +
                menuPresenter.getText(Frame.ITEMVALIDATIONREQUESTALERT,4, getItemID());

        AcceptableAlert itemValidationRequestAlert = new AcceptableAlert(desc, getOwner(), menuPresenter);
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
     * @return the item ID should it be accepted.
     */
    public int getItemID(){
        return this.itemID;
    }
}

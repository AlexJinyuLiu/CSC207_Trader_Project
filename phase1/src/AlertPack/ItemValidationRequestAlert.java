package alertpack;

import java.io.Serializable;

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

package AlertPack;

import java.io.Serializable;

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

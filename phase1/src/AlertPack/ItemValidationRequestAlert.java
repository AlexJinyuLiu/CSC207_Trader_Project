package AlertPack;

import java.io.Serializable;

public class ItemValidationRequestAlert extends AdminAlert implements Serializable{
    //author: Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    protected String usernameOfOwner;
    protected String name;
    protected String description;
    protected final int itemID;
    private static int idGenerator = 0;

    public ItemValidationRequestAlert(int itemID, String owner, String obj, String desc){
        super(0);
        this.description = desc;
        this.name = obj;
        this.usernameOfOwner = owner;
        this.itemID = itemID;
        idGenerator++;
    }
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

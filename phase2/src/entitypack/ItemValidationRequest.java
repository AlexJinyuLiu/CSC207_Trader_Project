package entitypack;

import java.io.Serializable;

/**
 * Entity class storing data about a user's request to validate an item and add it to their inventory.
 */
public class ItemValidationRequest  implements Serializable {

    /**
     * The name of the item
     */
    private String itemName;

    /**
     * A description of the item.
     */
    private String itemDescription;

    /**
     * The username of the user who created this ItemValidationRequest.
     */
    private String usernameOfCreator;

    /**
     * Initialize a new itemvalidationrequest with specified username, item name, and description.
     */
    public ItemValidationRequest(String usernameOfCreator, String itemName, String itemDescription){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.usernameOfCreator = usernameOfCreator;
    }

    /**
     *
     * @return The name of the item.
     */
    public String getItemName(){
        return itemName;
    }

    /**
     *
     * @return A description of the item.
     */
    public String getItemDescription(){
        return itemDescription;
    }

    /**
     *
     * @return The username of the user who is requesting the item.
     */
    public String getUsernameOfCreator(){
        return usernameOfCreator;
    }

}

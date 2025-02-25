package entitypack;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an EntityPack.Item in the trading system.
 */
public class Item implements Serializable {
    //author: Tian Yue Dong in group 0110 for CSC207H1 summer 2020 project

    /**
     * The name of the item
     */
    private String name;
    /**
     * A short description of the item
     */
    private String description;
    /**
     * A unique ID for the item.
     */
    private final int id;

    /**
     * Initializes a new EntityPack.Item with a name, description, and unique ID.
     * @param name the name of the item
     * @param description a short description of the item
     * @param itemID the unique ID of the item.
     */
    public Item(String name, String description, int itemID) {
        this.name = name;
        this.description = description;
        this.id = itemID; // id number is taken from the item validation request when the item is created within ControllerPresenterPack.AdminAlertManager
    }

    /**
     * Initializes a new EntityPack.Item with a name and unique ID.
     * @param name the name of the item
     * @param itemID the unique ID of the item.
     */
    public Item(String name, int itemID){
        this.name = name;
        this.id = itemID;
    }


    //setters

    /**
     * @param description the description of the string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //getters

    /**
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the ID of the item
     */
    public int getId() {
        return id;
    }


}

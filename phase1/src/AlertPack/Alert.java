package AlertPack;

import java.io.Serializable;
public abstract class Alert implements Serializable{
    //author: Louis Scheffer V in group 0110 for CSC207H1 summer 2020 project
    private static int idGenerator = 0;
    protected int alertID;
    protected final int type;


    public Alert(int type) {
        alertID = idGenerator;
        idGenerator++;
        this.type = type;
    }

    /**
     *
     * @return ID number of the alert.
     */
    public int getAlertID() {
        return alertID;
    }

    /**
     *
     * @return an integer indicating the type of the alert.
     */
    public int getType() {
        return type;
    }
}

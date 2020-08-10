package alertpack;
import usecasepack.Prompt;
import java.io.Serializable;
public abstract class Alert implements Serializable, Prompt {
    /** Messages which will be presented to users and may require action.
     *
     */

    private static int idGenerator = 0;
    private int alertID;
    private final int type;

    /** The Super Constructor for all subclasses of Alert.
     *
     * @param type the type of the alert which will be defined by the constructor of the alert.
     */
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

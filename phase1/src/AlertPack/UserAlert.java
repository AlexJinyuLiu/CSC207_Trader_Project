package AlertPack;

import AlertPack.Alert;

import java.io.Serializable;

/**
 * An abstract alert class that is a superclass to all alerts that get sent to users.
 */
public abstract class UserAlert extends Alert implements Serializable {
    public UserAlert(int type) {
        super(type);
    }
}

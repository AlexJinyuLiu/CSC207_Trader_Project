package AlertPack;

import AlertPack.Alert;

import java.io.Serializable;

public abstract class UserAlert extends Alert implements Serializable {
    public UserAlert(int type) {
        super(type);
    }
}

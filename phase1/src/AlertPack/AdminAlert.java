package AlertPack;

import AlertPack.Alert;

import java.io.Serializable;

public abstract class AdminAlert extends Alert implements Serializable {
    public AdminAlert(int type) {
        super(type);
    }
}

package alertpack;

import java.io.Serializable;

public abstract class AdminAlert extends Alert implements Serializable {
    /** Alerts which which will be viewed and handled by and admin user.
     *
     */

    /** Super-Constructor used for All admin alerts.
     *
     * @param type the type of the alert to be passed by the individual admin alert
     */
    public AdminAlert(int type) {
        super(type);
    }
}

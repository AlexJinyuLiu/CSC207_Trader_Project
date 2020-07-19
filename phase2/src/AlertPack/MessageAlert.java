package AlertPack;

import java.io.Serializable;

public class MessageAlert extends UserAlert implements Serializable {
    /** Alert which gives a message to a user from another user.
     *
     */
    //group 0110 for CSC207H1 summer 2020 project
    private String message;
    private String senderUsername;

    /** Called when a user messages another user.
     *
     * @param message the text of the message.
     * @param senderUsername The user from which it is sent.
     */
    public MessageAlert(String message, String senderUsername){
        super(3);
        this.message = message;
        this.senderUsername = senderUsername;
    }

    /**
     *
     * @return the username of the sender.
     */
    public String getSenderUsername(){
        return this.senderUsername;
    }

    /**
     *
     * @return the message being sent.
     */
    public String getMessage(){
        return this.message;
    }
}

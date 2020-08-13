package entitypack;

/**
 * In the controller layer, if the input is 0, this exception is thrown and for controller purposes and the
 * exception brings the user back to the main menu.
 */
public class InputZeroException extends Exception{
    public InputZeroException(){
    }
}

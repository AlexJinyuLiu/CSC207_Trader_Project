package entitypack;

/**
 * Interface defining common functionality to all users of the program.
 */
public interface User {
    final String username = null;

    String password = null;

    MetroArea metro = null;

    String getUsername();

    MetroArea getMetro();

    void setMetro(MetroArea metro);

    void setPassword(String password);

    boolean checkPassword(String password);
}

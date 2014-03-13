package modelInterfaces.users;

/**
 * Created by qzcx on 3/13/14.
 */
public interface User {
    void setName(String name);

    void setPassword(String password);

    String getName();

    String getPassword();

    int getId();
}

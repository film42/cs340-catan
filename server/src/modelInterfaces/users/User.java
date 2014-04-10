package modelInterfaces.users;

/**
 * Created by Jon George on 3/13/14.
 */
public interface User {
    void setName(String name);

    void setPassword(String password);

	void setId(int id);

    String getName();

    String getPassword();

    int getId();

	String toJson();
}

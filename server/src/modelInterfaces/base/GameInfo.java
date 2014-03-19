package modelInterfaces.base;

import comm.request.CreateGameRequest;
import modelInterfaces.users.User;

/**
 * Created by qzcx on 3/17/14.
 */
public interface GameInfo {
    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    Game getData();

    void setData(Game data);

    void initGame(CreateGameRequest createGameRequest);

    void addPlayer(User user);
}

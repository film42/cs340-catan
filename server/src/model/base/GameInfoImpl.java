package model.base;

import com.google.inject.Inject;
import comm.request.CreateGameRequest;
import modelInterfaces.base.Game;
import modelInterfaces.users.User;

/**
 * Created by qzcx on 3/17/14.
 */
public class GameInfoImpl implements modelInterfaces.base.GameInfo {
    private static int next_id = 0;
    private int id;
    private String title;
    private Game data;

    @Inject
    public GameInfoImpl(Game data) {
        this.id = next_id;
        this.data = data;
        next_id++;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Game getData() {
        return data;
    }

    @Override
    public void setData(Game data) {
        this.data = data;
    }

    @Override
    public void initGame(CreateGameRequest createGameRequest){
        this.data.initGame(createGameRequest);
    }

    @Override
    public void addPlayer(User user) {
        this.data.addPlayer(user);
    }
}

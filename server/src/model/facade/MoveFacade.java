package model.facade;

import comm.moves.Command;
import comm.moves.InvalidCommandException;
import model.JsonSerializable;

import java.io.IOException;

/**
 * Created by qzcx on 3/7/14.
 */
public class MoveFacade {

    private JsonSerializable m_model;

    public MoveFacade(JsonSerializable model) {
        m_model = model;
    }

    public void onMove(Command command) throws InvalidCommandException, IOException {
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        command.execute();
    }

}

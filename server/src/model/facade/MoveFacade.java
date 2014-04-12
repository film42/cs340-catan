package model.facade;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import model.Model;
import modelInterfaces.base.GameInfo;
import persistence.PersistenceManager;

import java.io.IOException;

import static comm.moves.base.Command.commandForType;

/**
 * Created by Jon George on 3/7/14.
 */
public class MoveFacade {

    private Model m_model;
    private PersistenceManager m_PersistenceManager;

    public MoveFacade(Model model, PersistenceManager myPersistenceManager) {
        m_model = model;
        m_PersistenceManager = myPersistenceManager;
    }

    public String onGetCommands(int gameId){
        GameInfo game = m_model.findGameById(gameId);
        Gson gson = new Gson();
        return gson.toJson(game.getCommandList());
    }

    public boolean onPostCommands(String json, int gameId){
        m_model.findGameById(gameId);
        //TODO finish this method :D
        return true;
    }

    public boolean onMove(int gameId, String json, String type)
            throws IOException, InvalidCommandException, JsonSyntaxException {
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        GameInfo game = m_model.findGameById(gameId);
        if(game == null) {
            throw new InvalidCommandException(type);
        }

		Command command = commandForType(type, json);

		if (command == null)
			return false;

        // Run this command then return OK
        command.execute(game);

        // Add the command to the Game's command list
        game.addCommand(command);

        m_PersistenceManager.updateCommandList(game);

        // Test game for winner
        game.getData().checkForCompletedGame();
        return true;
    }

}

package persistance;

import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;

import java.util.List;

/**
* This interface specifies the methods used to query and mutate the Games and User_Games Tables.
*
*
*/
public interface GamesDba {
  
  /**
  * This method will return a list of GameInfo classes.
  * This is designed to match the games/list server command. 
  * It will query all three table in order to retrieve the user info.
  */
  public List<GameInfo> getGamesList();
  /*
  * Add a created game to the database
  * Sets the cursor to 0.
  */
  public void addGame(String name, Game intialModel);
  
  /**
  *Since the Players are stored inside of the model, just restore the intial model.
  */
  public void joinGame(int gameId, Game intialModel);
  
  /**
  * retrieves the commandList json for a certain game
  */
  public String getCommandList(int gameId); 
  
  /**
  * Updates the commandList json for a certain game
  */
  public void updateCommandList(int gameId, String commandListJson);
  
  /**
  * Updates the checkpoint json and the cursor
  */
  public void updateCheckpoint(int gameId, Game checkpointModel, int cursor);
  
  /**
  * get the cursor out of the database
  */
  public int getCursor(int id);
  
  /*
  * Gets the most recent checkpoint, or if null the inital game
  */
  public Game getGame(int id);
  
  
  /**
  * clears the database.
  * I figure we would probably like to have this functionality.
  */
  public void clearDatabase();
  
} 

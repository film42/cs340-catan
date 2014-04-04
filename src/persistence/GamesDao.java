package persistence;


import java.util.List;

/**
* This interface specifies the methods used to query and mutate the Games and User_Games Tables.
*
*
*/
public interface GamesDao {

    /**
     *
     * @return a list of the ids of all games stored.
     */
    public List<Integer> getGameIds();

    /**
    *
    * @param gameId
    * @return the name of the game of the given Id.
    */
    public String getName(int gameId);

    /**
     *
     * @param gameId
     * @return the initial game model from the given Id
     */
    public String getInitialModel(int gameId);

    /**
    * Add a created game to the database
    * Sets the cursor to 0.
    */
    public void addGame(String name, String intialModel);

    /**
     * Updates the initial model
     * This is used to update the list of players who have joined the game since creation.
     * @param gameId
     */
    public void updateInitialModel(int gameId);

    /**
    *Since the Players are stored inside of the model, just restore the intial model.
    */
    public void joinGame(int gameId, String intialModel);

    /**
    * retrieves the commandList json for a certain game
    */
    public String getCommandList(int gameId);

    /**
    * Updates the commandList json for a certain game
    */
    public void updateCommandList(int gameId, String commandListJson);

    /**
     *
     * @param gameId
     * @return the checkpoint of the given id.
     */
    public String getCheckpoint(int gameId);

    /**
    * Updates the checkpoint json and the cursor
    */
    public void updateCheckpoint(int gameId, String checkpointModel, int cursor);

    /**
    * get the cursor out of the database
    */
    public int getCursor(int id);

    /**
    * clears the database.
    * I figure we would probably like to have this functionality.
    */
    public void clearDatabase();
  
} 

package persistance;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* This interface specifies the methods used to query and mutate the Games and User_Games Tables.
*
*
*/
public class GamesDAO {

    /**
     *
     * @return a list of the ids of all games stored.
     */
    public List<Integer> getGameIds(){
        List<Integer> gameIds = new ArrayList<Integer>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "SELECT id FROM games order by id";
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                gameIds.add(new Integer(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return gameIds;
    }

    private String getStringField(int gameId, String fieldName){
        String fieldValue = "";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "SELECT "+ fieldName + " FROM games WHERE id = " + gameId;
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()){
               fieldValue = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return fieldValue;
    }


    private boolean updateStringField(int gameId, String fieldName, String fieldValue){

        boolean isUpdated = false;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "UPDATE games SET "+ fieldName + " = ? WHERE id = " + gameId;
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            stmt.setString(1, fieldValue);
            if(stmt.executeUpdate() == 1){
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return isUpdated;
    }


    /**
    *
    * @param gameId
    * @return the name of the game of the given Id.
    */
    public String getName(int gameId){
        return (this.getStringField(gameId, "name"));
    }

    /**
     *
     * @param gameId
     * @return the initial game model from the given Id
     */
    public String getInitialModel(int gameId){
       return this.getStringField(gameId,"initialPoint");
    }


    /**
    * Add a created game to the database
    * Sets the cursor to 0.
    * return gameID
    */
    public int addGame(String name, String initialModel) {


        int gameID = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "INSERT INTO games( name, initialPoint) VALUES(?,?)";
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2,initialModel);
            if(stmt.executeUpdate() == 1){
                rs = stmt.getGeneratedKeys();
                gameID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return gameID;

    }


    /**
    *Since the Players are stored inside of the model, just restore the intial model.
    */
    public boolean joinGame(int gameId, String initialModel) {
        return this.updateStringField(gameId, "initialPoint", initialModel);
    }
    /**
    * retrieves the commandList json for a certain game
    */
    public String getCommandList(int gameId) {
        return this.getStringField(gameId, "commandList");
    }

    /**
    * Updates the commandList json for a certain game
    */
    public boolean updateCommandList(int gameId, String commandListJson) {
        return this.updateStringField(gameId, "commandList", commandListJson);

    }

    /**
     *
     * @param gameId
     * @return the checkpoint of the given id.
     */
    public String getCheckpoint(int gameId) {
        return this.getStringField(gameId,"checkPoint");
    }

    /**
    * Updates the checkpoint json and the cursor
    */
    public boolean updateCheckpoint(int gameId, String checkpointModel, int cursor) {

        boolean isUpdated = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "UPDATE games SET checkPoint = ?, checkPointCursor = ? WHERE id = " + gameId;
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            stmt.setString(1, checkpointModel);
            stmt.setInt(2,cursor);
            if (stmt.executeUpdate() == 1) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return isUpdated;
    }

    /**
    * get the cursor out of the database
    */
    public int getCursor(int gameId) {

        int cursor = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "SELECT checkPointCursor FROM games WHERE id = " + gameId;
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()){
                cursor = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return cursor;
    }
    public boolean deleteGame(int gameId){
        boolean isDeleted = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "Delete FROM WHERE id = " + gameId;
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            stmt.setInt(1,gameId);
            if (stmt.executeUpdate() == 1) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return isDeleted;

    }
    public List<GameDTO> getGames(){
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "SELECT id, name,initialPoint,checkPoint,commandList,checkPointCursor "+
                           "FROM games";
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                GameDTO gameDTO = new GameDTO();
                gameDTO.setId(rs.getInt(1));
                gameDTO.setName(rs.getString(2));
                gameDTO.setInitialPoint(rs.getString(3));
                gameDTO.setCheckPoint(rs.getString(4));
                gameDTO.setCommandList(rs.getString(5));
                gameDTO.setCheckPointCursor(rs.getInt(6));
                gamesDTO.add(gameDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            PersistenceProvider.close(stmt);
            PersistenceProvider.close(rs);
        }
        return gamesDTO;
    }



    /**
    * delete games
    */
    public void deleteGames(){

        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM games";
            stmt = PersistenceProvider.getConnection().prepareStatement(query);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PersistenceProvider.close(stmt);
        }
    }
} 

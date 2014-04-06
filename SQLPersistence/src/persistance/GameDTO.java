package persistance;

/**
 * Created by klu on 4/4/14.
 */
public class GameDTO {
    private int id;
    private String name;
    private String initialPoint;
    private String checkPoint;
    private String commandList;
    private int checkPointCursor;

    public GameDTO() {

    }

    public GameDTO(int id, String name, String initialPoint, String checkPoint,
                   String commandList, int checkPointCursor) {
        this.id = id;
        this.name = name;
        this.initialPoint = initialPoint;
        this.checkPoint = checkPoint;
        this.commandList = commandList;
        this.checkPointCursor = checkPointCursor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(String initialPoint) {
        this.initialPoint = initialPoint;
    }

    public String getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(String checkPoint) {
        this.checkPoint = checkPoint;
    }

    public String getCommandList() {
        return commandList;
    }

    public void setCommandList(String commandList) {
        this.commandList = commandList;
    }

    public int getCheckPointCursor() {
        return checkPointCursor;
    }

    public void setCheckPointCursor(int checkPointCursor) {
        this.checkPointCursor = checkPointCursor;
    }
}

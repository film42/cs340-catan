package model.base;

import com.google.inject.Inject;
import model.JsonImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class ResourcesImpl extends JsonImpl implements modelInterfaces.base.Resources {

    //final private String BRICK_TYPE = "brick";
    //final private String WOOD_TYPE  = "wood";
    //final private String SHEEP_TYPE = "sheep";
    //final private String WHEAT_TYPE = "wheat";
    //final private String ORE_TYPE   = "ore";
    //private String[] TYPES = {BRICK_TYPE, WOOD_TYPE, SHEEP_TYPE, WHEAT_TYPE, ORE_TYPE};

    private int brick;
    private int wood;
    private int sheep;
    private int wheat;
    private int ore;

    @Inject
    public ResourcesImpl() {
        brick = 0;
        wood = 0;
        sheep = 0;
        wheat = 0;
        ore = 0;
    }

    @Override
	public int getBrick() {
        return brick;
    }

    @Override
	public void setBrick(int brick) {
        this.brick = brick;
    }

    @Override
	public int getWood() {
        return wood;
    }

    @Override
	public void setWood(int wood) {
        this.wood = wood;
    }

    @Override
	public int getSheep() {
        return sheep;
    }

    @Override
	public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    @Override
	public int getWheat() {
        return wheat;
    }

    @Override
	public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    @Override
	public int getOre() {
        return ore;
    }

    @Override
	public void setOre(int ore) {
        this.ore = ore;
    }

    @Override
    public int getResourceCount() {
        return brick + wood + sheep + wheat + ore;
    }

    @Override
    public int getResourceByString(String type){
        switch(type){
            case BRICK:
                return brick;
            case WOOD:
                return wood;
            case SHEEP:
                return sheep;
            case WHEAT:
                return wheat;
            case ORE:
                return ore;
            default:
                System.err.println("getResourceByString: Invalid String");
                return -1;
        }
    }

    @Override
    public void setResourceByString(String type, int amount){
        switch(type){
            case BRICK:
                brick = amount;
			break;
            case WOOD:
                wood  = amount;
			break;
            case SHEEP:
                sheep = amount;
			break;
            case WHEAT:
                wheat = amount;
			break;
            case ORE:
                ore   = amount;
			break;
		default:
			System.err.println("setResourceByString: Invalid String");
        }
    }

    @Override
    public void incrementResourceByString(String type, int amount) {
        setResourceByString(type, getResourceByString(type) + amount);
    }

    @Override
    public void decrementResourceByString(String type, int amount) {
        setResourceByString(type, getResourceByString(type) - amount);
    }

    @Override
    public List<String> getAvailibleResources(){
        List<String> resources = new ArrayList<>();
        for (String type : TYPES) {
            if(getResourceByString(type) > 0)
                resources.add(type);
        }
        return resources;
    }

}

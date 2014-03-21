package model.base;

import com.google.inject.Inject;
import model.JsonImpl;

/**
 * Created by: film42 on: 3/7/14.
 */
public class ResourcesImpl extends JsonImpl implements modelInterfaces.base.Resources {

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
}

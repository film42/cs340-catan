package model.base;

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

    public ResourcesImpl() {
        brick = 0;
        wood = 0;
        sheep = 0;
        wheat = 0;
        ore = 0;
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }
}

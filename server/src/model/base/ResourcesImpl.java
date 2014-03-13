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
}

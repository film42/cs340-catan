package model.preview;

import model.JsonImpl;

/**
 * Created by: film42 on: 3/9/14.
 */
public class PlayerStub extends JsonImpl {

    private String color;
    private String name;
    private int id;

    public PlayerStub() {
        this.color = "red";
        this.name = "ash";
        this.id = -1;
    }
}

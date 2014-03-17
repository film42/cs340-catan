package model.preview;

import model.JsonImpl;

/**
 * This class is used to serialize responses to the client.
 * Created by: film42 on: 3/9/14.
 */
public class PlayerStub extends JsonImpl {

    private String color;
    private String name;
    private int id;

    public PlayerStub(int id, String name, String color){
       this.id = id;
       this.color = color;
        this.name = name;
    }

    public PlayerStub() {
        this.color = "red";
        this.name = "ash";
        this.id = -1;
    }
}

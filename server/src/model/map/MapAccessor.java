package model.map;

import comm.moves.form.VertexLocation;

/**
 * Created by: film42 on: 3/22/14.
 */
public interface MapAccessor {

    public void addRoad(int playerIndex, VertexLocation location);

    public void addSettlement(int playerIndex, VertexLocation location);

    public void addCity(int playerIndex, VertexLocation location);

}

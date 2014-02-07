var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};
  
  
/**
This class represents a vertex. It inherits from BaseContainer.
The data in this class (that you get from the JSON model) is independent of the hexgrid, except for the location.
Therefore, we leave it up to you to decide how to implement it.
It must however implement one function that the hexgrid looks for: 'isOccupied' - look at its documentation.
From the JSON, this object will have three properties: location, ownerID and worth.
Besides the 'isOccupied' method, you may add any other methods that you need.
Domain: 
  vertexjson: one vertex portion of a hex on the map from the JSON returned by the server
  
Invariants:
  INVARIANT: Vertex objects are read only
  
Constructor Specification:
  PRE: vertexjson.value contains an VertexValue object that defines the ownerID and worth of the vertex
  
  @class Vertex
  @constructor
  @param {Object} vertexjson One vertex portion of a hex on the map from the JSON returned by the server
  @extends hexgrid.BaseContainer
*/
catan.models.map.Vertex = (function Vertex_Class(){
  
  //core.forceClassInherit(Vertex, hexgrid.BaseContainer);

  /**
  @property value
  @type vertexValue
  */
  
  function Vertex(vertexjson){
    this.value = new catan.models.map.VertexValue(vertexjson.value);
  }
  
  /**
  Returns true if vertex is occupied by a player's piece (i.e. a settlement or city)
  <pre>
  PRE: None
  POST: returns a boolean that indicates whether a player has a piece on this vertex
  </pre>

  @method isOccupied
  */
  function isOccupied() {
    if(this.value.getOwnerID() > -1){
      return true;
    }
    return false;
  }

  /**
  Returns the value of the edge, which contains the ownerID
  @method getValue
  */
  function getValue(){
    return this.value;
  }

  return Vertex;

})();
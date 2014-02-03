var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

/**
This class represents an edge. It inherits from BaseContainer.
The data in this class (that you get from the JSON model) is independent of the hexgrid, except for the location.
Therefore, we leave it up to you to decide how to implement it.
It must however implement one function that the hexgrid looks for: 'isOccupied' - look at its documentation.
From the JSON, this object will have two properties: location, and ownerID.
Besides the 'isOccupied' method, you may add any other methods that you need.

Domain: 
  edgejson: one edge portion of a hex on the map from the JSON returned by the server
  
Invariants:
  INVARIANT: Edge objects are read only
  
Constructor Specification:
  PRE: edgejson.value contains an EdgeValue object that defines the ownerID
  
  @class Edge
  @constructor
  @param {Object} edgejson One edge portion of a hex on the map from the JSON returned by the server
  @extends hexgrid.BaseContainer
*/
catan.models.map.Edge = (function Edge_Class(){
  
  core.forceClassInherit(Edge, hexgrid.BaseContainer);

  core.defineProperty(Edge.prototype, "value");


  function Edge(edgejson){
    this.value = new EdgeValue(edgejson.value);
  }
  
  /**
  Returns true if edge is occupied by a player's piece (i.e. a road)
  <pre>
  PRE: None
  POST: returns a boolean that indicates whether a player has a piece on this edge
  </pre>

  @method isOccupied
  */
  function isOccupied(){
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

  return Edge;
}());
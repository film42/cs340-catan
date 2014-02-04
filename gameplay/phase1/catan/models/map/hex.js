var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

/**
This class represents a Hex. You may add any methods that you need (e.g., to get the resource/hex type, etc.)

In order to work with the hexgrid, this class must extend hexgrid.BasicHex (already done in the code). You also need to implement
a CatanVertex and CatanEdge classes (stubs are provided in this file).  Look at their documentation to see what needs to be done there.
 
The hexgrid will be passed an instance of this class to use as a model, and will pull the constructor from that instance. 
(The core.forceInherit sets the constructor, in case you are curious how that works)
Domain: 
  hexjson: the JSON returned by the server for an individual Hex, including its edges, vertexes, location, and type 
  
Invariants:
  INVARIANT: Hex objects are read only
  
Constructor Specification:
  PRE: hexjson.location contains the information for a HexLocation object
  PRE: hexjson.edges is an array of objects to define Edge objects
  PRE: hexjson.vertexes is an array of objects to define Vertex objects
  PRE: hexjson.landtype may be omitted or may contain a string defining the type of land the hex represents
  PRE: hexjson.island is a boolean that defines whether the hex is land or water

@constructor
@param {Object} hexjson The JSON returned by the server for an individual Hex, including its edges, vertexes, location, and type 
@extends hexgrid.BasicHex

@class Hex
*/
catan.models.map.Hex = (function CatanHex_Class(){

  core.forceClassInherit(Hex, hexgrid.BasicHex);
  
  /**
  @property location
  */
  core.defineProperty(Hex.prototype, "location");
  /**
  @property edges
  */
  core.defineProperty(Hex.prototype, "edges");
  /**
  @property vertexes
  */
  core.defineProperty(Hex.prototype, "vertexes");
  /**
  @property landtype
  */
  core.defineProperty(Hex.prototype, "landtype");
  /**
  @property island
  */
  core.defineProperty(Hex.prototype, "island");

  //constructor
  function Hex(hexjson){
    //set location
    this.location = new catan.model.hexgrid.HexLocation(hexjson.location.x, hexjson.location.y);
    this.island = hexjson.isLand;
    if(this.isLand){
      this.landtype = hexjson.landtype;
    }
    //make edges
    for(var i = 0; i < hexjson.edges.length; i++){
      this.edges[i] = new Edge(hexjson.edges[i]);
    }

    //make vertexes
    for(var i = 0; i < hexjson.vertexes.length; i++){
      this.vertexes[i] = new Vertex(hexjson.vertexes[i]);
    }      
  }

  /**
  Returns the location of the Hex
  <pre>
  PRE: none
  POST: returns a HexLocation that is the location for the Hex
  </pre>

  @method getLocation
  @return HexLocation
  */
  Hex.prototype.getLocation = function(){
    return this.location;
  }
  
  /**
  Returns the edge belonging to the Hex at direction given
  <pre>
  PRE: A valid EdgeDirection object that specifies a direction on the Hex
  POST: returns an Edge belonging to the hex in the specified direction
  </pre>

  @method getEdge
  @param EdgeDirection
  @return Edge
  */
  Hex.prototype.getEdge = function(direction){

  }

  /**
  Returns the vertex belonging to the Hex at direction given
  <pre>
  PRE: A valid VertexDirection object that specifies a direction on the Hex
  POST: returns a Vertex belonging to the hex in the specified direction
  </pre>

  @method getVertex
  @param VertexDirection
  @return Vertex
  */
  Hex.prototype.getVertex = function(direction){

  }

  /**
  Specifies whether the hex is land or water
  <pre>
  PRE: none
  POST: returns a boolean that is true if the Hex represents land
  </pre>

  @method isLand
  @return boolean
  */
  Hex.prototype.isLand = function(){
    return this.island;
  }

  /**
  Returns what kind of land the Hex is
  If it is not land, returns "water" which should not be passed to server.
  <pre>
  PRE: Caller has already determined that it is land using isLand()
  POST: returns a string indicating what kind of land it is
  </pre>

  @method getLandType
  @return string
  */
  Hex.prototype.getLandType = function(){
    if(this.island){
      return this.landtype
    }
    else{
      return "water";
    }
  }
  return Hex;
}());
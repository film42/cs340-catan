var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

catan.models.map.port = (function() {

  core.defineProperty(Port.prototype,"inputResource");
  core.defineProperty(Port.prototype,"location");
  core.defineProperty(Port.prototype,"orientation");
  core.defineProperty(Port.prototype,"ratio");
  core.defineProperty(Port.prototype,"validVertices");

  /**
    @author Jon George
    Port class contains data about a port
    Domain: 
      portjson: the port json data from server used to intialize the port
      
    Invariants:
      INVARIANT: port objects are read only
      
    Constructor Specification:
      PRE: portjson.inputResource is a string enum or ommitted 
      PRE: portjson.location {HexLocation} containing valid x,y location for an ocean tile
      PRE: portjson.orientation is a string enum ("NW","N","NE","E","SE","SW")
      PRE: portjson.ratio is a positive int
      PRE: portjson.validVertex1 contains valid x,y tile position and direction
      PRE: portjson.validVertex2 contains valid x,y tile position and direction
      
      @class Port
      @constructor
      @param {Object} portjson the port json data from server used to intialize the port
  */
  function Port(portjson) {}

  

  //not sure if this is needed, or just basic gets and sets
  //port.prototype.getValidVertices = function() {
  //    return null;
  //};

  return port;
})();


catan.models.map.vertexLocatiion = function() {

  core.defineProperty(HexLocation.prototype,"x");
  core.defineProperty(HexLocation.prototype,"y");
  core.defineProperty(HexLocation.prototype,"direction");

  /**
    @author Jon George
    VertexLocation class contains a location and direction of a given vertex
    Domain: 
      vertexjson: the vertex json data from server used to intialize the VertexLocation
      
    Invariants:
      INVARIANT: vertex objects are read only
      
    Constructor Specification:
      PRE: vertexjson.x {Number} contains valid x coordinate
      PRE: vertexjson.y {Number} contains valid y coordinate
      PRE: vertexjson.direction {String} enum ("W","NW","NE","E","SE","SW")
      
      @class VertexLocation
      @constructor
      @param {Object} portjson the port json data from server used to intialize the port
  */
  function VertexLocation(vertexjson) {

  }



}

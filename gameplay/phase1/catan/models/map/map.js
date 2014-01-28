var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

catan.models.map.Map = (function() {
  
  var hexgrid = catan.models.hexgrid;

  /**
  @author Jon George
  Map class contains data about the map
  Domain: 
    mapjson: the map json data from server used to intialize the map
    
  Invariants:
    INVARIANT: map objects are read only
    
  Constructor Specification:
    PRE: mapjson.hexGrid contains a object containing information to create the HexGrid object
    PRE: mapjson.numbers contains a object containing numbers information
    PRE: mapjson.ports contains an array containing port information
    PRE: mapjson.radius is an  positive integer value
    PRE: mapjson.robber is a valid HexLocation object
    
    @class Map
    @constructor
    @param {Object} mapjson the map json data from server used to intialize the map
  */
  function Map(mapjson) {
    this.hexGrid = hexgrid.HexGrid.getRegular(mapjson.radius, CatanHex);
  }
  
  core.defineProperty(Map.prototype,"hexGrid");
  core.defineProperty(Map.prototype,"numbers");
  core.defineProperty(Map.prototype,"ports");
  core.defineProperty(Map.prototype,"radius");
  core.defineProperty(Map.prototype,"robber");
  
  /**
  <pre>
  PRE: gave valid hexLocation and string
  POST: returns whether or not a settlement can be built there
  </pre>
   
  @method canBuildSettlement
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which vertex the user is trying to build on.
    
  */
  Map.prototype.canBuildSettlement = function(hexLoc, dir) {};
  
  /**
  <pre>
  PRE: gave valid hexLoc and dir
  POST: returns whether or not a city can be built there
  </pre>
   
  @method canBuildCity
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which vertex the user is trying to build on.
    
  */
  Map.prototype.canBuildCity = function(hexLoc, dir) {};
  
  /**
  <pre>
  PRE: gave valid hexLoc and dir
  POST: returns whether or not a road can be built there
  </pre>
   
  @method canBuildSettlement
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which edge the user is trying to build on.
    
  */
  Map.prototype.canBuildRoad = function(hexLoc, dir) {};
  
  /**
  <pre>
  PRE: The client has the cards he wants to trade in
  POST: returns whether or not the trade is valid based 
  </pre>
   
  @method canMaritimeTrade
  @param {resourceList} cardsTraded - cards the client wants to trade in
  @param {resourceList} cardsRecieved -cards the client will recieve
    
  */
  Map.prototype.canMaritimeTrade = function(cardsTraded, cardsRecieved) {};
  
  return Map;
})();
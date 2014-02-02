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
    //init hexgrid
    //TODO figure out how to init hexgrid
    this.hexGrid = hexgrid.HexGrid.getRegular(mapjson.radius, CatanHex); //given line
    //init ports
    this.ports = [];
    mapjson.ports.forEach(function(portjson){
    //is this the correct way to create a new object?
    ports.push(new catan.models.Port(portjson));
    });
    //init robber
    this.robber = new catan.model.hexgrid.HexLocation(mapjson.robber.x, mapjson.robber.y);
    //init numbers- since this is just a lookup table, so just use the json object from the server
    this.numbers = mapjson.numbers;
    //init radius
    this.radius = mapjson.radius;
    
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
  Map.prototype.canBuildSettlement = function(playerId, hexLoc, dir) {
  //make sure it is a valid vertex
  //make sure the vertex does not already have a buildings build there.
  //make sure the adjacent vertices do not have buildings
  //make sure there is a road owned by the player adjacent to the vertex
  };
  
  /**
  <pre>
  PRE: gave valid hexLoc and dir
  POST: returns whether or not a city can be built there
  </pre>
   
  @method canBuildCity
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which vertex the user is trying to build on.
    
  */
  Map.prototype.canBuildCity = function(playerId, hexLoc, dir) {
  //check if there is a settlement at the given vertex
  };
  
  /**
  <pre>
  PRE: gave valid hexLoc and dir
  POST: returns whether or not a road can be built there
  </pre>
  
     
  @method canBuildSettlement
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which edge the user is trying to build on.
    
  */
  Map.prototype.canBuildRoad = function(playerId, hexLoc, dir) {
  //make sure it is a valid edge and not in the ocean
  //Check if edge already has a road
  //Check if adjacent edges have roads 
  };
  
  /**
  <pre>
  PRE: The client has the cards he wants to trade in
  POST: returns whether or not the trade is valid based 
  </pre>
   
  @method canMaritimeTrade
  @param {resourceList} cardsTraded - cards the client wants to trade in
  @param {resourceList} cardsRecieved -cards the client will recieve
    
  */
  Map.prototype.canMaritimeTrade = function(playerId, cardsTraded, cardsRecieved){
  //check if it is a 4:1 trade
    //return true;
  //else check if it is a 3:1 trade
    //get valid vertices of 3:1 trade
    //check for buildings of playerId at those locations
      //return true;
  //check if it is a 2:1 trade
    //check what type of 
    //check for buildings of playerId at those locations
      //return true;
  //else return false
    
  };
  
  
  /**
  <pre>
  This method is a private function used for calculating if maritime trade is legal
  PRE: type is a valid type of port
  POST: returns an array of vertices of VertexLocation
  </pre>
   
  @method canMaritimeTrade
  @param {string} type - the type of port to search for
    
  */
  Map.prototype.findAllXPortVertices = function(type){
    //check if type is valid
    var validTypes = ["Wood", "Brick", "Sheep", "Wheat", "Ore"]
    if(type){
      var check = false;
      for(var i=0; i<validTypes.length; i++){
        if(type == validTypes[i]){
          check = true;
          break;
        }
      }
      if(!check){
        console.debug("type: " + type " is not a valid input to findAllXPortVertices()");
        return undefined;
      }
    }
    
    
    var ret =[];
    
    for(var i=0; i<this.port.length; i++){
      if(port[i].getType == type || (!port[i].getType && !type)){
        ret.concat(port[i].getValidVertices());
      }
    }
    return ret;
  }
  
  
  return Map;
})();













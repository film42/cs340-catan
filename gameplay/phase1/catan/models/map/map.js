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
    //this.hexGrid = hexgrid.HexGrid.getRegular(mapjson.radius, CatanHex); //given line
    this.hexGrid = new hexgrid.HexGrid(mapjson.hexgrid);
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
  
  Map.prototype.getHexGrid() = function(){
    return this.hexGrid;
  }
  Map.prototype.getNumbers() = function(){
    return this.numbers;
  }
  Map.prototype.getPorts() = function(){
    return this.ports;
  }
  Map.prototype.getRadius() = function(){
    return this.radius;
  }
  Map.prototype.getRobber() = function(){
    return this.robber;
  }

  /**
  <pre>
  PRE: gave valid hexLocation and string
  POST: returns whether or not a settlement can be built there
  </pre>
   
  @method canBuildSettlement
  @param {HexLocation} hexLoc Hex Location where client is trying to build on
  @param {String} dir Direction which indicates which vertex the user is trying to build on.
    
  */
  Map.prototype.canBuildSettlement = function(playerId, hexLoc, dir, isSetupPhase) {
    //make sure it is a valid vertex
    var buildVertex = this.hexGrid.getVertex(hexLoc, dir);
    if(buildVertex){
      //make sure the vertex does not already have a building built there.
      if(!buildVertex.isOccupied()){
        
        //make sure the adjacent vertices do not have buildings
        var adjVertexes = this.hexGrid.getAdjVertex(hexLoc,dir)
        for(var i=0; i<adjVertexes.length; i++){
          if(adjVertexes[i].isOccupied()){
            return false;
          }
        }
        //setup phase logic
        if(isSetupPhase){
          return true;
        }
        //make sure there is a road owned by the player adjacent to the vertex
        var adjEdges = this.hexGrid.getEdgesFromVertex(hexLoc, dir);
        for(var i=0; i< adjEdges.length; i++){
          if(adjEdges[i].isOccupied() && adjEdges[i].getOwnerId() == playerId){ //may not need first check
            return true;
          }
        }
      }
    }
    return false;

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
    var buildVertex = this.hexGrid.getVertex(hexLoc, dir);
    if(buildVertex.getValue() == 1/*"settlement"*/){
      return true;
    }
    return false;
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
  Map.prototype.canBuildRoad = function(playerId, hexLoc, dir, isSetupPhase){
    
    var buildEdge = this.hexGrid.getEdge(hexLoc, dir);
    if(buildEdge){ //make sure it is a valid edge and not in the ocean
      //Check if edge already has a road
      if(buildEdge.isOccupied){
        //setup phase logic check for a adj settlement owned by that player
        if(isSetupPhase){
          var adjVertexes = this.hexGrid.getVertexesFromEdge(hexLoc, dir);
          for(var i=0; i <adjVertexes.length; i++){
            if(adjVertexes[i].isOccupied() && adjVertexes[i].getOwnerId()){
              return true;
            }
          }
        }else{
          //Check if adjacent edges have roads owned by that player
          var adjEdges = this.hexGrid.getAdjEdges(hexLoc, dir);
          for(var i=0; i< adjEdges.length; i++){
            if(adjEdges[i].isOccupied() && adjEdges[i].getOwnerId()){
              return true;
            }
          }
        }
      }
    }
    return false;
  };
  
  /**
  <pre>
  PRE: The client has the cards he wants to trade in
  PRE: This is a single trade of trading a few cards of the same type for a single other card.
  POST: returns whether or not the trade is valid based 
  </pre>
   
  @method canMaritimeTrade
  @param {resourceList} cardsTraded - cards the client wants to trade in
    
  */
  Map.prototype.canMaritimeTrade = function(playerId, cardsTraded){
    var cardType = ""; //string
    var ratio;    //int
    var portVertices;
    //find the nonzero resource in cardsTraded
    if(cardsTraded.getBrick()){
      cardType = "brick";
      ratio = cardsTraded.getBrick();
      
    }else if(cardsTraded.Ore()){
      cardType = "ore";
      ratio = cardsTraded.getOre();
      
    }else if(cardsTraded.getSheep()){
      cardType = "sheep";
      ratio = cardsTraded.getSheep();
      
    }else if(cardsTraded.getWheat()){
      cardType = "wheat";
      ratio = cardsTraded.getWheat();
      
    }else if(cardsTraded.getWood()){
      cardType = "wood";
      ratio = cardsTraded.getWood();
    } else{ //no values given for resources
      return false;
    }
    
    
    //check if it is a 4:1 trade
    if(ratio == 4){
      return true;
    }else if( ratio == 3){
      portVertices = findAllXPortVertices();
    }else if( ratio == 2){
      portVertices = findAllXPortVertices(cardType);
    }
    if(!portVertices){
      return false;
    }
    
    //check for buildings of playerId at those locations
    for(var i=0; i<portVertices.length; i++){
      var portVertex = this.hexGrid.get(portVertices[i].getLocation(),portVertices[i].getDirection())
      if(portVertex && portVertex.isOccupied() && portVertex.getOwnerId == playerId){
        return true;
      }
      
    }
    return false;
  };

  
  Map.prototype.canPlaceRobber = function(newLoc, oldLoc){
    if(newLoc.x == oldLoc.x && newLoc.y == oldLoc.y){
      return false;
    }
    
    if(this.hexGrid.isLand(newLoc)){
      return true;
    }
    return false;
  }
  
  
  /**
  <pre>
  This method is a private function used for calculating if maritime trade is legal
  PRE: type is a valid type of port either the type enumerated 
  POST: returns an array of vertices of VertexLocation
  </pre>
   
  @method findAllXPortVertices
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













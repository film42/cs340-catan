var catan = catan || {};
catan.models = catan.models || {};

/**
	This module contains the map
	
	@module		catan.models
	@namespace models
*/

catan.models.Map = (function mapNameSpace(){
    
  var hexgrid = catan.models.hexgrid;
  
  var Map = (function Map_Class(){
  
  core.defineProperty(Map.prototype,"hexGrid");
  core.defineProperty(Map.prototype,"numbers");
  core.defineProperty(Map.prototype,"ports");
  core.defineProperty(Map.prototype,"radius");
  core.defineProperty(Map.prototype,"robber");
  
  
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
	function Map(mapjson)
	{
		this.hexGrid = hexgrid.HexGrid.getRegular(mapjson.radius, CatanHex);
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
	Map.prototype.canBuildSettlement(hexLoc, dir){
	
	}
	
	/**
    <pre>
    PRE: gave valid hexLoc and dir
    POST: returns whether or not a city can be built there
    </pre>
     
    @method canBuildCity
    @param {HexLocation} hexLoc Hex Location where client is trying to build on
    @param {String} dir Direction which indicates which vertex the user is trying to build on.
        
  */
	Map.prototype.canBuildCity(hexLoc, dir){
	
	}
	
	/**
    <pre>
    PRE: gave valid hexLoc and dir
    POST: returns whether or not a road can be built there
    </pre>
     
    @method canBuildSettlement
    @param {HexLocation} hexLoc Hex Location where client is trying to build on
    @param {String} dir Direction which indicates which edge the user is trying to build on.
        
  */
	Map.prototype.canBuildRoad(hexLoc, dir){
	
	}
	
	/**
    <pre>
    PRE: The client has the cards he wants to trade in
    POST: returns whether or not the trade is valid based 
    </pre>
     
    @method canMaritimeTrade
    @param {resourceList} cardsTraded - cards the client wants to trade in
    @param {resourceList} cardsRecieved -cards the client will recieve
        
  */
	Map.prototype.canMaritimeTrade(cardsTraded, cardsRecieved){
	
	}
	
	
	return Map;
		
  }());
    
    /**
	This class represents an edge. It inherits from BaseContainer.
    The data in this class (that you get from the JSON model) is independent of the hexgrid, except for the location.
    Therefore, we leave it up to you to decide how to implement it.
    It must however implement one function that the hexgrid looks for: 'isOccupied' - look at its documentation.
    From the JSON, this object will have two properties: location, and ownerID.
    Besides the 'isOccupied' method, you may add any other methods that you need.
    
    @constructor
    @extends hexgrid.BaseContainer
	
	@class CatanEdge
	*/
    var CatanEdge = (function CatanEdge_Class(){
    
        core.forceClassInherit(CatanEdge, hexgrid.BaseContainer);
    
        function CatanEdge(){}
        
        // once you override this, put in some documentation
        function isOccupied(){
            return false; // default implementation, change this!
        }

        return CatanEdge;
    }());
    
    /**
	This class represents a vertex. It inherits from BaseContainer.
    The data in this class (that you get from the JSON model) is independent of the hexgrid, except for the location.
    Therefore, we leave it up to you to decide how to implement it.
    It must however implement one function that the hexgrid looks for: 'isOccupied' - look at its documentation.
    From the JSON, this object will have three properties: location, ownerID and worth.
    Besides the 'isOccupied' method, you may add any other methods that you need.
    
    @constructor
    @extends hexgrid.BaseContainer
	
	@class CatanVertex
	*/
    var CatanVertex = (function CatanVertex_Class(){
    
        core.forceClassInherit(CatanVertex, hexgrid.BaseContainer);
        
        function CatanVertex(){}
        
        // once you override this, put in some documentation
        function isOccupied(){ 
            return false; // default implementation, change this!
        }

        return CatanVertex;
    }()); 
    
    
    /**
	This class represents a Hex. You may add any methods that you need (e.g., to get the resource/hex type, etc.)
    
    In order to work with the hexgrid, this class must extend hexgrid.BasicHex (already done in the code). You also need to implement
    a CatanVertex and CatanEdge classes (stubs are provided in this file).  Look at their documentation to see what needs to be done there.
     
    The hexgrid will be passed an instance of this class to use as a model, and will pull the constructor from that instance. 
    (The core.forceInherit sets the constructor, in case you are curious how that works)
      
    @constructor
    @param {hexgrid.HexLocation} location - the location of this hex. It's used to generate locations for the vertexes and edges.
    @extends hexgrid.BasicHex
	
	@class CatanVertex
	*/
    var CatanHex = (function CatanHex_Class(){
    
        core.forceClassInherit(CatanHex, hexgrid.BasicHex);
        
        function CatanHex(location){          
            hexgrid.BasicHex.call(this,location,CatanEdge,CatanVertex);
        } 
        
        return CatanHex;
    }());
    
	return Map;

}());



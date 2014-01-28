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
  
  Domain: 
    edgejson: one edge portion of a hex on the map from the JSON returned by the server
    
  Invariants:
    INVARIANT: CatanEdge objects are read only
    
  Constructor Specification:
    PRE: edgejson.value contains an EdgeValue object that defines the ownerID
    
    @class CatanEdge
    @constructor
    @param {Object} edgejson One edge portion of a hex on the map from the JSON returned by the server
    @extends hexgrid.BaseContainer
	*/
  var CatanEdge = (function CatanEdge_Class(){
  
    core.forceClassInherit(CatanEdge, hexgrid.BaseContainer);

    core.defineProperty(CatanEdge.prototype, "value");

  
    function CatanEdge(edgejson){}
    
    /**
    Returns true if edge is occupied by a player's piece (i.e. a road)
    <pre>
    PRE: None
    POST: returns a boolean that indicates whether a player has a piece on this edge
    </pre>

    @method isOccupied
    */
    function isOccupied(){
      return false; // default implementation, change this!
    }

    /**
    Returns the value of the edge, which contains the ownerID
    @method getValue
    */
    function getValue(){
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
  Domain: 
    vertexjson: one vertex portion of a hex on the map from the JSON returned by the server
    
  Invariants:
    INVARIANT: CatanVertex objects are read only
    
  Constructor Specification:
    PRE: vertexjson.value contains an VertexValue object that defines the ownerID and worth of the vertex
    
    @class CatanVertex
    @constructor
    @param {Object} vertexjson One vertex portion of a hex on the map from the JSON returned by the server
    @extends hexgrid.BaseContainer
	*/
  var CatanVertex = (function CatanVertex_Class(){
  
    core.forceClassInherit(CatanVertex, hexgrid.BaseContainer);


    
    function CatanVertex(vertexjson){}
    
    /**
    Returns true if vertex is occupied by a player's piece (i.e. a settlement or city)
    <pre>
    PRE: None
    POST: returns a boolean that indicates whether a player has a piece on this vertex
    </pre>

    @method isOccupied
    */
    function isOccupied(){ 
      return false; // default implementation, change this!
    }

    /**
    Returns the value of the edge, which contains the ownerID
    @method getValue
    */
    function getValue(){
    }


    return CatanVertex;
  }()); 
  
  /**
	This class represents a Hex. You may add any methods that you need (e.g., to get the resource/hex type, etc.)
  
  In order to work with the hexgrid, this class must extend hexgrid.BasicHex (already done in the code). You also need to implement
  a CatanVertex and CatanEdge classes (stubs are provided in this file).  Look at their documentation to see what needs to be done there.
   
  The hexgrid will be passed an instance of this class to use as a model, and will pull the constructor from that instance. 
  (The core.forceInherit sets the constructor, in case you are curious how that works)
  Domain: 
    hexjson: the JSON returned by the server for an individual Hex, including its edges, vertexes, location, and type 
    
  Invariants:
    INVARIANT: CatanHex objects are read only
    
  Constructor Specification:
    PRE: hexjson.location contains the information for a HexLocation object
    PRE: hexjson.edges is an array of objects to define Edge objects
    PRE: hexjson.vertexes is an array of objects to define Vertex objects
    PRE: hexjson.landtype may be omitted or may contain a string defining the type of land the hex represents
    PRE: hexjson.island is a boolean that defines whether the hex is land or water
  
  @constructor
  @param {Object} hexjson The JSON returned by the server for an individual Hex, including its edges, vertexes, location, and type 
  @extends hexgrid.BasicHex
	
	@class CatanHex
	*/
  var CatanHex = (function CatanHex_Class(){
  
    core.forceClassInherit(CatanHex, hexgrid.BasicHex);
    
    function CatanHex(hexjson){      
    } 
    
    return CatanHex;
  }());
  
	return Map;

}());



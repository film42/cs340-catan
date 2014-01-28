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
  
  function Hex(hexjson){}
  
  return Hex;
}());
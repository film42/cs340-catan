var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

catan.models.map.HexGrid = (function HexGrid_Class(){


  function HexGrid(hexgridjson){
    for(var i = 0; i < hexgridjson.hexes.length; i++){
      var hexloc = new HexLocation(hexgridjson.hexes[i].location.x, hexgridjson.hexes[i].location.y);
      var arrayloc = _getInternalHexRef(hexloc);
      this.hexes[arrayloc.y][arrayloc.x];
    }
    this.radius = hexgridjson.radius;
    this.x0 = hexgridjson.x0;
    this.y0 = hexgridjson.y0;

    this.offsets = hexgridjson.offsets;
  }

  HexGrid.prototype._getInternalHexRef = function(hexLoc){
    var translatedX = hexLocation.getX() + this.x0;
    var translatedY = hexLocation.getY() + this.y0;
    var arrayX = translatedX - this.offsets[translatedY];
    var arrayY = translatedY;
    return {x:arrayX, y:arrayY};
  }

  HexGrid.prototype.getHex = function(hexLocation){
    var internalLoc = this._getInternalHexRef(hexLocation);
    if (this.hexes[internalLoc.y]){
      return this.hexes[internalLoc.y][internalLoc.x];
    } else {
      return undefined;
    }
  }

  HexGrid.prototype.getVertex = function(hexLoc, dir){
    var hex = getHex(hexLoc);
    return hex.getVertex(dir);
  }
  
  HexGrid.prototype.getEdge = function(hexLoc, dir){
    var hex = getHex(hexLoc);
    return hex.getVertex(dir);
  }

  HexGrid.prototype.getEdgesFromVertex = function(hexLoc, dir){

  }

  HexGrid.prototype.getVertexesFromEdge = function(hexLoc, dir){

  }
  HexGrid.prototype.getAdjVertexes = function(hexLoc, dir){

  }
  HexGrid.prototype.getAdjEdges = function(hexLoc, dir){

  }

}());
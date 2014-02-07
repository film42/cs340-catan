var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

catan.models.map.HexGrid = (function HexGrid_Class(){


  function HexGrid(hexgridjson){
    //console.log(hexgridjson);
    this.hexes = [];
    for(var i = 0; i < hexgridjson.hexes.length; i++){
      //console.log(hexgridjson.hexes[i]);
      /*var hexloc = new catan.models.map.HexLocation(hexgridjson.hexes[i].location.x, hexgridjson.hexes[i].location.y);
      var arrayloc = _getInternalHexRef(hexloc);
      this.hexes[arrayloc.y][arrayloc.x] = new catan.models.map.Hex(hexgridjson.hexes[i]);
      */
      this.hexes[i]= [];
      console.log(hexgridjson.hexes[i].length);
      for(var j = 0; j < hexgridjson.hexes[i].length; j++){
        console.log(j);
        console.log(hexgridjson.hexes[i][j]);
        this.hexes[i][j] = new catan.models.map.Hex(hexgridjson.hexes[i][j]);      
      }
    }
    this.radius = hexgridjson.radius;
    this.x0 = hexgridjson.x0;
    this.y0 = hexgridjson.y0;

    this.offsets = hexgridjson.offsets;
  }

  HexGrid.prototype._getInternalHexRef = function(hexLoc){
    var translatedX = hexLoc.getX() + this.x0;
    var translatedY = hexLoc.getY() + this.y0;
    var arrayX = translatedX - this.offsets[translatedY];
    var arrayY = translatedY;
    return {x:arrayX, y:arrayY};
  }

  HexGrid.prototype.getHex = function(hexLoc){
    var internalLoc = this._getInternalHexRef(hexLoc);
    if (this.hexes[internalLoc.y]){
      return this.hexes[internalLoc.y][internalLoc.x];
    } else {
      return undefined;
    }
  }

  HexGrid.prototype.getVertex = function(hexLoc, dir){
    var hex = this.getHex(hexLoc);
    if(!hex){
      return null;
    }
    //check if surrounded by water
    if(!hex.isLand()){
      var dirnum = catan.models.map.VertexDirection(dir);
      var leftdir = nextDirectionCounterClockwise(dirnum);
      var leftneighbor = this.getHex(hexLoc.getNeighborLocation(leftdir));
      var rightneighbor = this.getHex(hexLoc.getNeighborLocation(dirnum));
      var both = true;
      if(leftneighbor){
        if(leftneighbor.isLand()){
          both = false;
        }
      }
      if(rightneighbor){
        if(rightneighbor.isLand()){
          both = false;
        }
      }
      if(both){
        return null;
      }
    }
    return hex.getVertex(dir);
  }
  
  HexGrid.prototype.getEdge = function(hexLoc, dir){
    var hex = getHex(hexLoc);
    if(!hex){
      return null;
    }
    //check if surrounded by water
    if(!hex.isLand()){
      var dirnum = catan.models.map.EdgeDirection(dir);
      var neighborloc = hexLoc.getNeighborLocation(dirnum);
      var neighborhex = this.getHex(neighborloc);
      if(!neighborhex){
        return null;
      }
      if(!neighbothex.isLand()){
        return null;
      }
    }
    return hex.getVertex(dir);
  }

  HexGrid.prototype.getEdgesFromVertex = function(hexLoc, dir){
    var hex = getHex(hexLoc);
    if(!hex){
      return null;
    }
    var vertex = hex.getVertex(dir);
    if(!vertex){
      return null;
    }
    var dirnum = catan.models.map.VertexDirection(dir);
    var right = hex.getEdge(dirnum);
    var edges;
    if(right){
      edges.push(right);
    }
    //edges.push(hex.getEdge((((dirnum-1)%6)+6)%6));
    var left = hex.getEdge(nextDirectionCounterClockwise(dirnum));
    if(left){
      edges.push(left);
    }


    var neighborloc = hexLoc.getNeighborLocation(dirnum);
    var neighborhex = this.getHex(neighborloc);
    if(neighborhex){
      //var oppdir = ((((dirnum+3)%6)+6)%6);
      var oppdir = getOppositeDirection(dirnum);
      var across = neighborhex.getEdge(nextDirectionCounterClockwise(oppdir));
      if(across){
        edges.push(across);
      }
    }
    //edges.push(neighbor.getEdge((((oppdir-1)%6)+6)%6));
    return edges;
  }

  HexGrid.prototype.getVertexesFromEdge = function(hexLoc, dir){
    var hex = getHex(hexLoc);
    if(!hex){
      return null;
    }
    var edge = hex.getEdge(dir);
    if(!edge){
      return null;
    }
    var dirnum = catan.models.map.EdgeDirection(dir);
    var vertexes;
    var right = hex.getVertex(dirnum);
    if(right){
      vertexes.push(right);
    }
    var left = hex.getVertex(nextDirectionClockwise(dirnum));
    if(left){
      vertexes.push(left);
    }
    
    return vertexes;
  }

  HexGrid.prototype.getAdjVertexes = function(hexLoc, dir){
    var hex = this.getHex(hexLoc);
    if(!hex){
      return null;
    }
    var vertex = hex.getVertex(dir);
    if(!vertex){
      return null;
    }
    var dirnum = catan.models.map.VertexDirection(dir);
    var right = hex.getVertex(nextDirectionClockwise(dirnum));
    var vertexes;
    if(right){
      vertexes.push(right);
    }
    var left = hex.getVertex(nextDirectionCounterClockwise(dirnum));
    if(left){
      vertexes.push(left);
    }

    var neighborloc = hexLoc.getNeighborLocation(dir);
    var neighborhex = this.getHex(neighborloc);
    if(neighborhex){
      var oppdir = getOppositeDirection(dirnum);
      var across = neighborhex.getVertex(nextDirectionCounterClockwise(oppdir));
      if(across){
        vertexes.push(across);
      }
    }
    return vertexes;
  }

  HexGrid.prototype.getAdjEdges = function(hexLoc, dir){
    var hex = this.getHex(hexLoc);
    if(!hex){
      return null;
    }
    var edge = hex.getEdge(dir);
    if(!edge){
      return null;
    }
    var dirnum = catan.models.map.EdgeDirection(dir);
    var edges;
    var right = hex.getEdge(nextDirectionClockwise(dirnum));
    if(right){
      edges.push(right);
    }
    var left = hex.getEdge(nextDirectionCounterClockwise(dirnum));
    if(left){
      edges.push(left);
    }

    var neighborloc = hexLoc.getNeighborLocation(dirnum);
    var neighborhex = this.getHex(neighborloc);
    if(neighborhex){
      var oppdir = getOppositeDirection(dirnum);
      var acrossleft = neighborhex.getEdge(nextDirectionCounterClockwise(oppdir));
      if(acrossleft){
        edges.push(acrossleft);
      }

      var acrossright = neighborhex.getEdge(nextDirectionClockwise(oppdir));
      if(acrossright){
        edges.push(acrossright);
      }
    }
    return edges;
  }

  function positiveModulo(lhs,rhs){
    // The inner paren makes the range -rhs to rhs
    // The addition puts it to 0 to 2rhs
    // The last modulo reduces it to 0 to rhs
    return ((lhs % rhs) + rhs) % rhs;
  }
  
  function getOppositeDirection(direction){
    return positiveModulo((direction + 3),6);
  }
  
  //Works on Hex, Edge and Vertex Directions
  function nextDirectionClockwise(direction){
    return positiveModulo((direction + 1),6);
  }
  
  //Works on Hex, Edge and Vertex Directions
  function nextDirectionCounterClockwise(direction){
    return positiveModulo((direction - 1),6);
  }
  return HexGrid;
}());

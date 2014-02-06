var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

  /**
    This represents a location of a hex on a hex grid.
   
    @class HexLocation
    @constructor
    @param {Integer} x
    @param {Integer} y
  */
catan.models.map.HexLocation = (function HexLocationClass(){

    function HexLocation(x, y)){
      this.x = x;
      this.y = y;
    }
    
    core.defineProperty(HexLocation.prototype,"x");
    core.defineProperty(HexLocation.prototype,"y");   
    
        
        /**
          This represents a location of a hex on a hex grid.
         
            @method equals
            @param {hexgrid.HexLocation} otherLocation
            @return boolean Returns true if the other location has the same x,y
        */
    HexLocation.prototype.equals =  function(otherLocation){
      return (this.getX() == otherLocation.getX() && otherLocation.getY() == this.getY()); 
    }
    
        /**
          This represents a location of a hex on a hex grid.
         
            @method getNeighborLocation
            @param {[HexDirection]} hexDirection
            @return hexgrid.HexLocation Returns a location next to this one, in the direction of the 'hexDirection' given
        */
    HexLocation.prototype.getNeighborLocation = function getNeighborLocation(hexDirection){
      var x,  y , z = 0;
        switch (hexDirection) {
        case HexDirection.SE:
          x = 1; y = 0; z = -1;
          break;
        case HexDirection.S:
          x = 0; y = 1; z = -1;
          break;
        case HexDirection.SW:
          x = -1; y = 1; z = 0;
          break;
        case HexDirection.NW:
          x = -1; y = 0; z = 1;
          break;
        case HexDirection.N:
          x = 0; y = -1; z = 1;
          break;
        case HexDirection.NE:
          x = 1; y = -1; z = 0;
          break;
        default:
          console.log(hexDirection,this);
          core.assert(false);
          throw new Error("Invalid Direction");
      }
      return new HexLocation(this.getX() + x,this.getY() + y);
    }
    
    return HexLocation;
  }());
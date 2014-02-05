var catan = catan || {};
catan.models = catan.models || {};

catan.models.ResourceList = (function() {

  var brick;
  var ore;
  var sheep;
  var wheat;
  var wood;
  
  /**
  @author Steve Allred
  The Resources object has a bunch of read-only variables that the controller can query.  It actually contains the data in local variables.
  Domain: 
    JSON: This should contain the initial values of the resources.
    
  Read-only:
    The player object on the client side is read-only.  The server will update the client side model with a new version.
    
  Constructor Specification:
    PRE: the JSON provides values for each of the resources.  All other data is either calculated or generated.
    
    POST: The constructor creates an object that has all of the following fields initialized:
      brick : integer
      ore : integer
      sheep : integer
      wheat : integer
      wood : integer
    
    @class Resources
    @constructor
    @param {JSON} the data containing the initialized objects
*/
  function ResourceList(json){
    this.Resources(json.brick, json.ore, json.sheep, json.wheat, json.wood);
  }
  /**
   *  Initializes a ResourceList to Zeroes
   */
  function Resources() {
    this.Resources(0,0,0,0,0);
  }
  /**
   * Initializes a ResourceList to the values passed in the correct order.
   */
  function Resources(br, or, sh, wh, wo) {
    this.brick = br;
    this.ore = or;
    this.sheep = sh;
    this.wheat = wh;
    this.wood = wo;
  }
  /**
  Gets the quantity of Brick resources in the Resources.

    @class Resources
    @return an integer of the desired property
  */
  Resources.prototype.getBrickCount = function() {
      return this.brick;
  };
  /**
  Gets the quantity of Ore in the Resources.

    @class Resources
    @return an integer of the desired property
  */
  Resources.prototype.getOreCount = function() {
    return this.ore;
  };
  /**
  Gets the quantity of Sheep in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getSheepCount = function() {
    return this.sheep;
  };
  /**
  Gets the quantity of Wheat in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getWheatCount = function() {
    return this.wheat;
  };
  /**
  Gets the quantity of Wood in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getWoodCount = function() {
    return this.wood;
};
/**
 * Checks to see if this resource list has at least the amount in the new resourceList.
 * @return true if this ResourceList has at least the same as the number of resources in the passed in ResourceList, false otherwise
 */
Resources.prototype.hasAtLeast = function(resources) {
    return !(this.brick < resources.brick || this.ore < resources.ore || this.sheep < resources.sheep || this.wheat < resources.wheat || this.wood < resources.wood);
}

/**
 * Checks to see if this resource list has at least the amount in the new resourceList.
 * @return true if this ResourceList has at least the same as the number of resources in the passed in ResourceList, false otherwise
 */
Resources.prototype.getTotalCount = function() {
    return this.getBrickCount() + this.getOreCount() + this.getSheepCount() + this.getWheatCount() + this.getWoodCount();
}

  return ResourceList;
})();
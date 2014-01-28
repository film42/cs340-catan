var catan = catan || {};
catan.models = catan.models || {};

catan.models.ResourceList = (function() {

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
  function Resources(json) {
    
  }
  /**
  Gets the quantity of Brick resources in the Resources.

    @class Resources
    @return an integer of the desired property
  */
  Resources.prototype.getBrickCount = function() {
      return null;
  };
  /**
  Gets the quantity of Ore in the Resources.

    @class Resources
    @return an integer of the desired property
  */
  Resources.prototype.getOreCount = function() {
    return null;
  };
  /**
  Gets the quantity of Sheep in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getSheepCount = function() {
    return null;
  };
  /**
  Gets the quantity of Wheat in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getWheatCount = function() {
    return null;
  };
  /**
  Gets the quantity of Wood in the Resources.

    @class Resources
    @return an integer of the desired property
  */
Resources.prototype.getWoodCount = function() {
    return null;
};

  return ResourceList;
})();
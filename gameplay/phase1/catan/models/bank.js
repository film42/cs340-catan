var catan = catan || {};
catan.models = catan.models || {};

catan.models.Bank = (function() {

  /**
  @author Steve Allred
  The Bank has a bunch of read-only variables that the controller can query.
  Domain: 
    JSON: This should contain the initial values of our resources.
    
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
    
    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
*/
  function Bank(json) {
    
  }
  /**
  Gets the quantity of Brick resources in the bank.

    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
  */
  Bank.prototype.getBrickCount = function() {
      return null;
  };
  /**
  Gets the quantity of Ore in the bank.

    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
  */
  Bank.prototype.getOreCount = function() {
    return null;
  };
  /**
  Gets the quantity of Sheep in the bank.

    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
  */
Bank.prototype.getSheepCount = function() {
    return null;
  };
  /**
  Gets the quantity of Wheat in the bank.

    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
  */
Bank.prototype.getWheatCount = function() {
    return null;
  };
  /**
  Gets the quantity of Wood in the bank.

    @class Bank
    @constructor
    @param {JSON} the data containing the initialized objects
  */
Bank.prototype.getWoodCount = function() {
    return null;
};
  
  return Bank;
})();
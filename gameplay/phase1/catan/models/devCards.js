var catan = catan || {};
catan.models = catan.models || {};

catan.models.DevCards = (function() {

  /**
  @author Steve Allred
  DevCards contains values of various development cards.
  Domain: 
    JSON: This should contain the initial values of our resources.
    
  Read-only:
    The player object on the client side is read-only.  The server will update the client side model with a new version.
    
  Constructor Specification:
    PRE: the JSON provides values for each of the resources.  All other data is either calculated or generated.
    
    POST: The constructor creates an object that has all of the following fields initialized:
      monopoly : number
      monument : number
      roadBuilding : number
      soldier : number
      yearOfPlenty : number
    
    @class DevCards
    @constructor
    @param {JSON} the data containing the initialized objects
*/
  function DevCards(json) {

  }
  /**
  Gets the Monopoly card count.

    @class DevCards
    @param {JSON} the data containing the initialized objects
  */
  DevCards.prototype.getMonopolyCardCount = function() {
      return null;
  };
  /**
  Gets the Monument card count.

    @class DevCards
    @param {JSON} the data containing the initialized objects
  */
  DevCards.prototype.getMonumentCardCount = function() {
      return null;
  };
  /**
  Gets the roadBuilding card count.

    @class DevCards
    @param {JSON} the data containing the initialized objects
  */
  DevCards.prototype.getRoadBuildingCardCount = function() {
      return null;
  };
  /**
  Gets the Soldier card count.

    @class DevCards
    @param {JSON} the data containing the initialized objects
  */
  DevCards.prototype.getSoldierCardCount = function() {
      return null;
  };
  /**
  Gets the year of plenty card count.

    @class DevCards
    @param {JSON} the data containing the initialized objects
  */
  DevCards.prototype.getYearOfPlentyCardCount = function() {
      return null;
  };

  return DevCards;
})();
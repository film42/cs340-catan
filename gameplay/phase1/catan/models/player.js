var catan = catan || {};
catan.models = catan.models || {};

catan.models.Player = (function() {

  var cities;
  var color;
  var discarded;
  var largestArmy;
  var longestRoad;
  var monuments;
  var name;
  var newDevCards;
  var oldDevCards;
  var orderNumber;
  var playedDevCard;
  var playerID;
  var resources;
  /**
    @author Steve Allred
    The Player class contains information about a player and has methods that query the state of the player.
    Domain: 
      userdata: The user's specific data that is used to create the Player object.  This will be in the form of JSON when it comes from the server.
      
    Read-only:
      The player object on the client side is read-only.  The server will update the client side model with a new version.
      
    Constructor Specification:
      PRE: userdata provides a name (string) and a color (string).  All other data is either calculated or generated.
      
      POST: The constructor creates an object that has all of the following fields initialized:
      -cities : number
      -color : string
      -discarded : boolean
      -largestArmy : boolean
      -longestRoad : boolean
      -monuments : number
      -name : string
      -newDevCards : DevCardList
      -oldDevCards : DevCardList
      -orderNumber : number
      -playedDevCard : boolean
      -playerID : integer
      -resources : ResourceList
      
      @class Player
      @constructor
      @param {Object} the data containing the user name and color
  */
  function Player(data) {
    return null;
  }

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can buy a dev card.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a dev card.
  @class Player  
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuydevelomentCard = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can buy a road.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a road.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuyRoad = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can buy a settlement.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a settlement.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuySettlement = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can buy a city.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a settlement.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuyCity = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can offer a trade.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can offer a trade.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canOfferTrade = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can accept a trade.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can accept a trade.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canAcceptTrade = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if the user needs to discard a card.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user needs to discard a card.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.needDiscardCard = function() {
    return null;
  };

  /**
  @author Steve Allred
    Checks with the internal data to find out if it can discard a card.
    
  Constructor Specification:
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can can discard a card.
  @class Player 
  No parameters are necessary to check this value. 
  */
  Player.prototype.discardCard = function() {
    return null;
  };

  return Player;
})();
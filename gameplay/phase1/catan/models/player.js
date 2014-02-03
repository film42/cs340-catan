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
    this.cities = 0;       
    this.color = data.color;     //need to figure out actual way to get the color from the JSON  
    this.discarded = null;    
    this.largestArmy = false;  
    this.longestRoad = false;  
    this.monuments = 0;    
    this.name = data.name;         
    this.newDevCards = data.newDevCards;  
    this.oldDevCards = data.oldDevCards;  
    this.orderNumber = data.orderNumber;  
    this.playedDevCard = false;
    this.playerID = data.playerID;     
    this.resources = new catan.models.resources(data.resources);    
  }

  /**
  
    Checks with the internal data to find out if it can buy a dev card.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a dev card.  
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuyDevelomentCard = function() {
    return !this.playedDevCard;
  };

  /**
  
    Checks with the internal data to find out if it can buy a road.
    
    PRE:  This object has already been initialized.
          The road location is open
          The road location is connected to another road
          The road location is not on water
          You have the resources (1 wood, 1 brick; 1 road)

    POST: The method returns whether the user can buy a road. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuyRoad = function() {
    var can = true
    // Figure out how to access the road location stuff
    if (!this.resources.hasAtLeast(new catan.models.resources(1,0,0,0,1)))
      can = false;
    return can;
  };

  /**
  
    Checks with the internal data to find out if it can buy a settlement.
    
    PRE:  This object has already been initialized.
          The settlement location is open
          The settlement location is not on water
          The settlement location is connected to one of your roads
          You have the resources (1 wood, 1 brick, 1 wheat, 1 sheep; 1 settlement)
    POST: The method returns whether the user can buy a settlement. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuySettlement = function() {
    var can = true
    // Figure out how to access the map location stuff
    if (!this.resources.hasAtLeast(new catan.models.resources(1,0,1,1,1)))
      can = false;
    return can;
  };

  /**
  
    Checks with the internal data to find out if it can buy a city.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a settlement. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canBuyCity = function() {
    var can = true
    // Figure out how to access the map location stuff
    if (!this.resources.hasAtLeast(new catan.models.resources(0,3,0,2,0)))
      can = false;
    return can;
  };

  /**
  
    Checks with the internal data to find out if it can offer a trade.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can offer a trade. 
    @param {resourceList} cardsTraded - cards the client wants to trade in 
  */
  Player.prototype.canOfferTrade = function(cardsTraded)
  
  };

  /**
  
    Checks with the internal data to find out if it can accept a trade.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can accept a trade. 
    @param {resourceList} cardsRecieved -cards the client will receive
  */
  Player.prototype.canAcceptTrade = function() {
    return null;
  };

  /**
  
    Checks with the internal data to find out if the user needs to discard a card.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user needs to discard a card. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.needDiscardCard = function() {
    return null;
  };

  /**
    
      Checks with the internal data to find out if the player has the given cards
      
      PRE:  This object has already been initialized.
      POST: The method returns whether the user can can discard a card.
    @method canDiscardCard
    @param {resourceList} cardsDiscarded -the cards to be discarded
  */
  Player.prototype.canDiscardCard = function(cardsDiscarded) {
    return null;
  };

  /**
    PRE: There are resources associated with the user (there can be no, but we have to at least be able to check).
    POST: The method returns whether the user has the resources in the list.

    @param {array} resourceList The list of resources that you want to check.
    @return {boolean}
  */
  Player.prototype.hasXResources = function(resourceList) {};

  return Player;
})();
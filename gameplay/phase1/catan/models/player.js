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
    this.cities = data.cities;       
    this.color = data.color;
    this.discarded = data.discarded;    
    this.largestArmy = data.largestArmy;  
    this.longestRoad = data.longestRoad;  
    this.monuments = data.monuments;    
    this.name = data.name;         
    this.newDevCards = data.newDevCards;  
    this.oldDevCards = data.oldDevCards;  
    this.orderNumber = data.orderNumber;  
    this.playedDevCard = data.playedDevCard;
    this.playerID = data.playerID;
    this.resources = new catan.models.ResourceList(data.resources);
  }
  /**
    Checks with the internal data to find out if it can use a dev card.
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can use a dev card.  
  */
  Player.prototype.canPlayDevCard = function(){
    return !this.playedDevCard;
  };
  
  //Here are the play dev card functions. Need to document this later
  
  Player.prototype.canPlayYearOfPlenty= function(){
    return this.oldDevCards.yearOfPlenty > 1 && this.canPlayDevCard();
  }
  Player.prototype.canPlayRoadBuilding = function(){
    return this.oldDevCards.roadBuilding > 1 && this.canPlayDevCard();
  }
  Player.prototype.canPlaySoldier = function(){
    return this.oldDevCards.soldier > 1 && this.canPlayDevCard();
  }
  Player.prototype.canPlayMonument = function(){
    return this.newDevCards.monument > 1 && this.canPlayDevCard();
  }
  Player.prototype.canPlayMonopoly = function(){
    return this.oldDevCards.monopoly > 1 && this.canPlayDevCard();
  }
  
  /**
    Checks with the internal data to find out if it can buy a dev card.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a dev card.  
  No parameters are necessary to check this value. 
  */
  Player.prototype.canAffordToBuyDevCard = function() {
    return this.resources.hasAtLeast(0,1,1,1,0);
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
  Player.prototype.canAffordToBuyRoad = function() {
    return this.resources.hasAtLeast(1,0,0,0,1);
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
  Player.prototype.canAffordToBuySettlement = function() {
    return this.resources.hasAtLeast(1,0,1,1,1);
  };

  /**
  
    Checks with the internal data to find out if it can buy a city.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can buy a settlement. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.canAffordToBuyCity = function() {
    return this.resources.hasAtLeast(0,3,0,2,0);
  };

  /**
  
    Checks with the internal data to find out if it can offer a trade.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can offer a trade. 
    @param {resourceList} cardsTraded - cards the client wants to trade in 
  */
  Player.prototype.canAffordToOfferTrade = function(cardsToGive) {
    return this.resources.hasAtLeast(cardsToGive.brick, cardsToGive.ore, cardsToGive.sheep, cardsToGive.wheat, cardsToGive.wood);
  };

  /**
  
    Checks with the internal data to find out if it can accept a trade.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user can accept a trade. 
    @param {resourceList} cardsToDiscard -cards the client will need to give
  */
  Player.prototype.canAcceptTrade = function(cardsToGive) {
    return this.resources.hasAtLeast(cardsToGive.brick, cardsToGive.ore, cardsToGive.sheep, cardsToGive.wheat, cardsToGive.wood);
  };

  /**
  
    Checks with the internal data to find out if the user needs to discard a card.
    
    PRE:  This object has already been initialized.
    POST: The method returns whether the user needs to discard a card. 
  No parameters are necessary to check this value. 
  */
  Player.prototype.hasMoreThan7Cards = function() {
    return this.resources.getTotalCount() > 7;
  };
  
  /**
    This functions is used to see if a player can be robbed.
  */
  Player.prototype.hasNoCards = function(){
    return this.resources.getTotalCount() <= 0;
  };
  
  /**
    
      Checks with the internal data to find out if the player has the given cards
      
      PRE:  This object has already been initialized.
      Assuming that the only time that the user can't discard a card is if they have none.
      The specs didn't clarify this well.
      POST: The method returns whether the user can can discard a card.
    @method canDiscardCard
    @param {resourceList} cardsToDiscard -the cards to be discarded
  */
  Player.prototype.canDiscardCards = function(cardsToDiscard) {
    return this.resources.hasAtLeast(cardsToDiscard.brick, cardsToDiscard.ore, cardsToDiscard.sheep, cardsToDiscard.wheat, cardsToDiscard.wood);
  };

  /**
    PRE: There are resources associated with the user (there can be none, but we have to at least be able to check).
    POST: The method returns whether the user has the resources in the list.

    @param {array} resourceList The list of resources that you want to check.
    @return {boolean}
  */
  Player.prototype.hasXResources = function(resourceList) {
    return this.resources.hasAtLeast(resourceList.brick, resourceList.ore, resourceList.sheep, resourceList.wheat, resourceList.wood);
  };
  
  /**
   * This is a getter that wraps up all of the resources in a single object (without the methods of resourceList)
   */
  Player.prototype.getResources = function() {
    //just pass out a ResourceList
    return this.resources;
    /* var resourcesOut = { 
        "brick" : this.resources.brick,
        "ore" : this.resources.ore,
        "sheep" : this.resources.sheep,
        "wheat" : this.resources.wheat,
        "wood" : this.resources.wood
    };
    return resourcesOut; */
  }

  return Player;
})();
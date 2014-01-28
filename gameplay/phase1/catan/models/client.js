var catan = catan || {};
catan.models = catan.models || {};

/**
  This module contains the top-level client model class

  @module                catan.models
  @namespace             catan.models
*/

catan.models.ClientModel = (function() {


  /**
    The model class for ClientModel. This Class and it's children are supposed to be immutable. This serves as the foundational model piece, through which all state is accessed. 

    <pre>
    PRE: A player ID
    PRE: A JavaScript Object from /game/model
    POST: A fully stocked ClientModel, ready to be used
    </pre>

    @class ClientModel
    @constructor
      
    @param {integer} playerId to the current logged in player
    @param {Object} json object from /game/model
  */
  function ClientModel(playerId, json) {
    this.currentUserId = playerId;
  }

  //
  // Getters
  //


  /**
    Get List of Players

    <pre>
    PRE: None
    POST: Returns a list of players
    </pre>
      
    @return {array} List of players
  */
  ClientModel.prototype.getPlayers = function() {};

  /**
    Get the ClientModel Bank

    <pre>
    PRE: None
    POST: Returns the Bank Model
    </pre>
      
    @return {Bank} the bank model
  */
  ClientModel.prototype.getBank = function() {};

  /**
    Get the ClientModel Deck, that is, the game Deck

    <pre>
    PRE: None
    POST: Returns the Deck
    </pre>
      
    @return {Deck} the deck model
  */
  ClientModel.prototype.getDeck = function() {};

  /**
    Get the Chat model, which contains all the chat logs from the game

    <pre>
    PRE: None
    POST: Returns a Chat model
    </pre>
      
    @return {Chat} the chat log
  */
  ClientModel.prototype.getChat = function() {};

  /**
    Get the current Turn model, the current Turn state

    <pre>
    PRE: None
    POST: Returns the Turn Model
    </pre>
      
    @return {Turn} the Turn model
  */
  ClientModel.prototype.getTurn = function() {};

  /**
    Get the any trade offers if availible

    <pre>
    PRE: None
    POST: Returns the TradeOffer Model
    </pre>
      
    @return {TradeOffer} the TradeOffer model
  */
  ClientModel.prototype.getTradeOffer = function() {};

  /**
    Get the Map model

    <pre>
    PRE: None
    POST: Returns the Map Model
    </pre>
      
    @return {Map} the Map model
  */
  ClientModel.prototype.getMap = function() {};

  //
  // Cans
  //

  /**
    Can the current user buy a development card?

    <pre>
    PRE: There is a playerId
    PRE: There is a Bank
    PRE: There are resouce cards in hand

    if (has wool, grain, ore)
      POST: Returns true (can buy dev card)
    else 
      POST: Returns false (cannot buy)
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canBuyDevelomentCard = function() {};

  /**
    Can the current user buy a road?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand
    PRE: Any location on the map

    if (has brick, lumber, and valid location)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canBuyRoad = function(location) {};

  /**
    Can the current user buy a settlement?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand
    PRE: Any location on the map

    if (has brick, lumber, wool, grain and valid location)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canBuySettlement = function(location) {};


  /**
    Can the current user buy a city?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (has 3 ore, 2 grain and valid location (a settlement as well) ) 
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canBuyCity = function() {};

  /**
    Can offer a trade?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (player has trade offer in hand)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canOfferTrade = function() {};

  /**
    Can accept a trade offer?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (player has trade request in hand)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canAcceptTrade = function() {};

  /**
    Does the player need to discard cards?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (greater than 7 cards)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.needDiscardCard = function() {};

  /**
    Can the current player place a road at a location?

    <pre>
    PRE: There is a playerId
    PRE: Any location

    if (valid location)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canPlaceRoad = function(location) {};

  /**
    Can the current player place a settlement at a location?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (valid location)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canPlaceSettlement = function() {};

  /**
    Can the current player place a city at a location?

    <pre>
    PRE: There is a playerId
    PRE: There are resouce cards in hand

    if (location is a settlement owned by the user)
      POST: Returns true
    else 
      POST: Returns false
    </pre>
      
    @return {boolean}
  */
  ClientModel.prototype.canPlaceCity = function() {};

  return ClientModel;
})();
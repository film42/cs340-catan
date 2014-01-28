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
    Domain:
      - JSON has valid ClinetModel Schema referencing the TA provided spec
      - Has a player Id that's an integer

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

  /**
    Get the player id for longest road

    <pre>
    PRE: None
    POST: Returns the player id for longest road or none
    </pre>
      
    @return {integer}
  */
  ClientModel.prototype.getLongestRoadId = function() {};

  /**
    Get the player id for biggest army

    <pre>
    PRE: None
    POST: Returns the player id for biggest army or none
    </pre>
      
    @return {integer}
  */
  ClientModel.prototype.getBiggestArmyId = function() {};


  /**
    Get the winning player id

    <pre>
    PRE: None
    POST: Returns the winning player id or none
    </pre>
      
    @return {integer}
  */
  ClientModel.prototype.getWinnderId = function() {};

  //
  // Cans
  //

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

    @param {Locaion} location 
      
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

    @param {Locaion} location 
      
    @return {boolean}
  */
  ClientModel.prototype.canPlaceSettlement = function(location) {};

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

    @param {Locaion} location 
      
    @return {boolean}
  */
  ClientModel.prototype.canPlaceCity = function(location) {};

  return ClientModel;
})();
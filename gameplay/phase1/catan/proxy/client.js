var catan = catan || {};
catan.proxy = catan.proxy || {};

/**
  This module contains the proxy
  
  @module   catan.proxy
  @namespace catan.proxy
*/

catan.proxy.ClientProxy = (function() {

  /**
    The model class for ClientProxy 

    <pre>
    PRE: A string URL, ex: http://myserver/
    </pre>

    @class ClientProxy
    @constructor
      
    @param {String} url to the server
  */
  function ClientProxy(url) {
    this.url = url;
  }

  /**
    Sends a chat message to server
    <pre>
    PRE: A Valid player ID
    PRE: Message is any string, even empty
    PRE: Caller provides a callback
    POST: Caller always calls callback
    </pre>
    
    @param {integer} playerId The player who is broadcasting the chat
    @param {string} message The string that you would like to broadcast
    @param {function} callback The response callback
     
    @method sendChat
    @return {function(err)} callback
  */
  ClientProxy.prototype.sendChat = function(playerId, message, callback) {};

  /**
    Accept a trade or reject
    <pre>
    PRE: A Valid player ID
    PRE: A status of true or false indicating acceptance or rejection
    PRE: Caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is responding to trade request
    @param {boolean} status The status: accept or reject the trade
    @param {function} callback The response callback
     
    @method acceptTrade
    @return {function(err)} callback
  */
  ClientProxy.prototype.acceptTrade = function(playerId, status, callback) {};

  /**
    Offer a trade to another user
    <pre>
    PRE: A valid userId
    PRE: A valid otherUserId
    PRE: A valid offer, list of resource cards
    PRE: A valid request, list of resource cards
    PRE: Caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is offering the trade request
    @param {integer} otherPlayerId The OTHER player who will respond to the trade request
    @param {array} offer Resouces that the player is offering
    @param {array} offer Resouces that the player is requesting from the trade
    @param {function} callback The response callback
     
    @method acceptTrade
    @return {function(err)} callback
  */
  ClientProxy.prototype.offerTrade = function(playerId, otherPlayerId, offer, request, callback) {};

  /**
    Discard a number of playing cards
    <pre>
    PRE: A Valid player ID
    PRE: A list of cards
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is discarding cards
    @param {array} cards The resource cards the player will discard
    @param {function} callback The response callback
     
    @method discardCards
    @return {function(err)} callback
  */
  ClientProxy.prototype.discardCards = function(playerId, cards, callback) {};

  /**
    Build a road at a map Location
    <pre>
    PRE: A valid Player ID
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is building a road
    @param {location} location A map location object
    @param {function} callback The response callback
     
    @method buildRoad
    @return {function(err)} callback
  */
  ClientProxy.prototype.buildRoad = function(playerId, location, callback) {};


  /**
    Build a settlement at a map Location
    <pre>
    PRE: A valid player ID
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is building a settlement
    @param {location} location A map location object
    @param {function} callback The response callback
     
    @method buildSettlement
    @return {function(err)} callback
  */
  ClientProxy.prototype.buildSettlement = function(playerId, location, callback) {};

  /**
    Build a city at a map Location
    <pre>
    PRE: A valid player ID
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is building a city
    @param {location} location A map location object
    @param {function} callback The response callback
     
    @method buildCity
    @return {function(err)} callback
  */
  ClientProxy.prototype.buildCity = function(playerId, location, callback) {};

  /**
    Buy a development card
    <pre>
    PRE: A valid player ID
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is buying a development card
    @param {function} callback The response callback
     
    @method buyDevelopmentCard
    @return {function(err)} callback
  */
  ClientProxy.prototype.buyDevelopmentCard = function(playerId, callback) {};

  /**
    Play the year of plenty card
    <pre>
    PRE: A valid player ID
    PRE: Valid Resource Card 1
    PRE: Valid Resource Card 2
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player using the year of plenty card
    @param {string} resource1 The first resource being used
    @param {string} resource2 The second resource being used
    @param {function} callback The response callback
     
    @method playYearOfPlenty
    @return {function(err)} callback
  */
  ClientProxy.prototype.playYearOfPlenty = function(playerId, resource1, resource2, callback) {};

  /**
    Play road building card
    <pre>
    PRE: A valid player ID
    PRE: Valid Location 1
    PRE: Valid Location 2
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player using the road building card
    @param {Location} location1 The first location on which the player wants to build a road
    @param {Location} location2 The second location on which the player wants to build a road
    @param {function} callback The response callback
     
    @method playRoadBuilding
    @return {function(err)} callback
  */
  ClientProxy.prototype.playRoadBuilding = function(playerId, location1, location2, callback) {};

  /**
    Play soldier card
    <pre>
    PRE: Valid player ID
    PRE: Valid victim ID
    PRE: Valid Location
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is using the soldier card
    @param {integer} victimId The victim who is getting owned by soldier card
    @param {Location} location2 The second location on which the player wants to place a soldier
    @param {function} callback The response callback
     
    @method playSoldier
    @return {function(err)} callback
  */
  ClientProxy.prototype.playSoldier = function(playerId, victimId, location, callback) {};

  /**
    Play a Monopoly card
    <pre>
    PRE: Valid player ID
    PRE: Valid resource card
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player using the monopoly card

    @param {function} callback The response callback
     
    @method playMonopoly
    @return {function(err)} callback
  */
  ClientProxy.prototype.playMonopoly = function(playerId, resource, callback) {};

  /**
    Play a Monument card
    <pre>
    PRE: Valid player ID
    PRE: Valid resource card
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is using the monopoly card
    @param {string} resource The resource card the player wants to collect
    @param {function} callback The response callback
     
    @method playMonument
    @return {function(err)} callback
  */
  ClientProxy.prototype.playMonument = function(playerId, resource, callback) {};

  /**
    Tell the server that a turn is now finished.
    <pre>
    PRE: A valid player ID
    POST: Caller always calls callback
    </pre>

    @param {integer} playerId The player who is finishing their turn
    @param {function} callback The response callback
     
    @method finishTurn
    @return {function(err)} callback
  */
  ClientProxy.prototype.finishTurn = function(playerId, callback) {};

  return ClientProxy;
})();
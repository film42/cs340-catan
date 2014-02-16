var catan = catan || {};
catan.core = catan.core || {};

/**
  This module contains the core classes, including: Game

  @module                catan.core
  @namespace             catan.core
*/


catan.core.Game = (function() {

  /**
    The Game class, the big mama! This class contains all server communication, client models. It is also responsible for real time persistence. The Interface is designed to be extremely powerful for the development of Catan.

    <pre>
    PRE: A string URL, ex: http://myserver/
    </pre>

    @property {ClientModel} model
    @property {ClientProxy} proxy

    @class Game
    @constructor
      
    @param {String} url to the server
  */
  function Game(url) {
    this.model = {};
    this.proxy = new catan.proxy.ClientProxy();
    this.observers = [];
  }


  /**
    Add an observer to the Game for updates

    @param {function} observer The function (closure) that will be called when updates happen
      
    @return {void}
  */
  Game.prototype.addObserver = function(obs) {
    this.observers.push(obs);
  };

  /**
    Trigger all observers. A quick way to fire changes across the game.
      
    @return {void}
  */
  Game.prototype.triggerObservers = function() {
    this.observers.forEach(function(obs) {
      obs();
    });
  };

  /**
    INIT Game method tells the proxy to get a client model, refreshUI, and then established the 2000ms persistence loop with the server.

    <pre>
    PRE: There is an internet connection
    PRE: The whole UI is waiting to be rendered
    PRE: Caller provides a callback
    POST: The caller handles the callback
    </pre>
      
    @return {ClientModel}
  */
  Game.prototype.startGame = function(callback) {
    this.getState(function(err, resp) {
      model = new catan.models.ClientModel(resp);
      callback();
    });

    // Refresh every 2000 seconds
    var self = this;
    setInterval(function() {
      self.proxy.getState(function(err, resp) {
        if(err) return callback(err);

        // Override the model as we update.
        // This could give us some collisions.
        self.model = new catan.models.ClientModel(resp);
        self.refreshUI();
        callback(err);
      });
    }, 2000);
  };

  /**
    Refresh the UI with the current Client Model

    <pre>
    PRE: A ClientModel exists
    PRE: The Caller provides a callback
    POST: The caller handles the callback
    </pre>
      
    @return {ClientModel}
  */
  Game.prototype.refreshUI = function(callback) {};

  /**
    Get the model that's hooked up to this GAME class

    <pre>
    PRE: None
    POST: The Game's model
    </pre>
      
    @return {ClientModel}
  */
  Game.prototype.getModel = function() {
    return this.model;
  };

  /**
    Get the proxy that's hooked up to this GAME class

    <pre>
    PRE: None
    POST: The Game's proxy
    </pre>
      
    @return {ClientProxy}
  */
  Game.prototype.getProxy = function() {
    return this.proxy;
  };

  /**
    Get the ID of the current player based on the cookie set by the game server.

    <pre>
    PRE: None
    POST: The player ID set in the cookie
    </pre>
      
    @return {integer}
  */
  Game.prototype.getCurrentPlayerId = function() {
    return $.cookie('catan.user');
  };

  /**
    Get the current player model

    <pre>
    PRE: A valid current user id exists
    POST: The player model
    </pre>
      
    @return {Player}
  */
  Game.prototype.getCurrentPlayer = function() {
    var currentUserId = this.getCurrentPlayerId();
    return this.model.getCurrentPlayerWithId(currentUserId);
  };


  /////////////////////////////////////////////
  /////////////////////////////////////////////
  // Prettier Core API Interface with State  //
  /////////////////////////////////////////////
  /////////////////////////////////////////////

  /**
    Sends a chat message to server
    <pre>
    PRE: Message is any string, even empty
    PRE: Caller provides a callback
    POST: Caller always calls callback
    </pre>
    
    @param {string} message The string that you would like to broadcast
    @param {function} callback The response callback
     
    @method sendChat
    @return {function(err)} callback
  */
  Game.prototype.sendChat = function(message, callback) {
    var playerId = this.getCurrentPlayerId();
    this.proxy.sendChat(playerId, message, callback);
  };

  /**
    Accept a trade or reject
    <pre>
    PRE: A status of true or false indicating acceptance or rejection
    PRE: Caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {boolean} status The status: accept or reject the trade
    @param {function} callback The response callback
     
    @method acceptTrade
    @return {function(err)} callback
  */
  Game.prototype.acceptTrade = function(status, callback) {
    var playerId = this.getCurrentPlayerId();
    var canAcceptTrade = this.model.canAcceptTrade();

    if(!canAcceptTrade) return callback({error: "Cannot accept trade with these rescources."});

    this.proxy.acceptTrade(playerId, status, callback);
  };

  /**
    Offer a trade to another user
    <pre>
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
  Game.prototype.offerTrade = function(otherPlayerId, offer, callback) {
    var playerId = this.getCurrentPlayerId();
    var canOfferTrade = this.model.canOfferTrade(offer);

    if(!canOfferTrade) return callback({error: "Cannot offer trade!"});

    this.proxy.offerTrade(playerId, otherPlayerId, offer, callback);
  };

  /**
    <pre>
    PRE: The client has the cards he wants to trade at a port with
    POST: caller always calls callback
    </pre>
     
    @method maritimeTrade
    @param {resourceList} cardTraded cards the client wants to trade in
    @param {resourceList} cardRecieved cards the client will recieve
  */
  Game.prototype.maritimeTrade = function(cardTraded, cardRecieved, ratio, callback) {
    var playerId = this.getCurrentPlayerId();
    var canMaritimeTrade = this.model.canMaritimeTrade(cardTraded, rato, cardRecieved);

    if(!canMaritimeTrade) return callback({error: "Cannot maritime trade!"});

    this.proxy.maritimeTrade(playerId, cardTraded, cardRecieved, ratio, callback);
  };

  /**
    Discard a number of playing cards
    <pre>
    PRE: A list of cards
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {resourceList} cards The resource cards the player will discard
    @param {function} callback The response callback
     
    @method discardCards
    @return {function(err)} callback
  */
  Game.prototype.discardCards = function(cards, callback) {
    var playerId = this.getCurrentPlayerId();
    var canDiscard = this.model.canDiscardCards(cards);

    if(!canDiscard) return callback({ error: "Cannot Discard!" });

    this.proxy.discardCards(playerId, cards, callback);
  };

  /**
    Build a road at a map Location
    <pre>
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {HexLocation} location A map location object
    @param {string} direction N, SW etc. The location direction on the hex
    @param {function} callback The response callback
     
    @method buildRoad
    @return {function(err)} callback
  */
  Game.prototype.buildRoad = function(location, direction, callback) {
    var playerId = this.getCurrentPlayerId();
    var canBuildRoad = this.model.canBuildRoad(location, direction);
    var free = this.model.getTurn().isSetupPhase();

    if(!canBuildRoad) return callback({error: "Cannot build Road!"});

    this.proxy.buildRoad(playerId, location, direction, free, callback);
  };


  /**
    Build a settlement at a map Location
    <pre>
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {HexLoaction} location A map location object
    @param {string} direction N, SW etc. The location direction on the hex
    @param {function} callback The response callback
     
    @method buildSettlement
    @return {function(err)} callback
  */
  Game.prototype.buildSettlement = function(location, direction, callback) {
    var playerId = this.getCurrentPlayerId();
    var canBuildSettlement = this.model.canBuildSettlement(location, direction);
    var free = this.model.getTurn().isSetupPhase();

    if(!canBuildSettlement) return callback({error: "Cannot build settlement!"});

    this.proxy.buildSettlement(playerId, location, direction, free, callback);
  };

  /**
    Build a city at a map Location
    <pre>
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {location} location A map location object
    @param {function} callback The response callback
     
    @method buildCity
    @return {function(err)} callback
  */
  Game.prototype.buildCity = function(location, callback) {
    var playerId = this.getCurrentPlayerId();
    var canBuildCity = this.model.canBuildCity(location, direction);
    var free = this.model.getTurn().isSetupPhase();

    if(!canBuildCity) return callback({error: "Cannot build city!"});

    this.proxy.buildCity(playerId, location, direction, free, callback);
  };

  /**
    Buy a development card
    <pre>
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method buyDevelopmentCard
    @return {function(err)} callback
  */
  Game.prototype.buyDevelopmentCard = function(callback) {
    var playerId = this.getCurrentPlayerId();
    var canBuyDevelopmentCard = this.model.canBuyDevelopmentCard();

    if(!canBuyDevelopmentCard) return callback({error: "Cannot buy development card!"});

    this.proxy.buyDevelopmentCard(playerId, callback);
  };

  /**
    Play the year of plenty card
    <pre>
    PRE: Valid Resource Card 1
    PRE: Valid Resource Card 2
    POST: Caller always calls callback
    </pre>

    @param {string} resource1 The first resource being used
    @param {string} resource2 The second resource being used
    @param {function} callback The response callback
     
    @method playYearOfPlenty
    @return {function(err)} callback
  */
  Game.prototype.playYearOfPlenty = function(resource1, resource2, callback) {
    var playerId = this.getCurrentPlayerId();
    var canPlayYearOfPlenty = this.model.canPlayYearOfPlenty(resource1, resource2);

    if(!canPlayYearOfPlenty) return callback({error: "Cannot play year of plenty card!"});

    this.proxy.playYearOfPlenty(playerId, resource1, resource2, callback);
  };

  /**
    Play road building card
    <pre>
    PRE: Valid Location 1
    PRE: Valid Location 2
    POST: Caller always calls callback
    </pre>

    @param {Location} location1 The first location on which the player wants to build a road
    @param {string} direction1 The direction of the first location
    @param {Location} location2 The second location on which the player wants to build a road
    @param {string} direction2 The direction of the second location
    @param {function} callback The response callback
     
    @method playRoadBuilding
    @return {function(err)} callback
  */
  Game.prototype.playRoadBuilding = function(location1, direction1, location2, direction2, callback) {
    var playerId = this.getCurrentPlayerId();
    var canPlayRoadBuilding = this.model.canPlayRoadBuilding(location1, direction1, location2, direction2);

    if(!canPlayRoadBuilding) return callback({error: "Cannot play year of plenty card!"});

    this.proxy.playRoadBuilding(playerId, location1, direction1, location2, direction2, callback);
  };

  /**
    Play soldier card
    <pre>
    PRE: Valid victim ID
    PRE: Valid Location
    POST: Caller always calls callback
    </pre>

    @param {integer} victimId The victim who is getting owned by soldier card
    @param {Location} location2 The second location on which the player wants to place a soldier
    @param {function} callback The response callback
     
    @method playSoldier
    @return {function(err)} callback
  */
  Game.prototype.playSoldier = function(victimId, location, callback) {
    var playerId = this.getCurrentPlayerId();
    var canPlaySoldier = this.model.canPlaySoldier(victimId, location);

    if(!canPlaySoldier) return callback({error: "Cannot play soldier!"});

    this.proxy.playSoldier(playerId, victimId, location, callback);
  };

  /**
    Play a Monopoly card
    <pre>
    PRE: Valid resource card
    POST: Caller always calls callback
    </pre>

    @param {string} resource The resource to collect
    @param {function} callback The response callback
     
    @method playMonopoly
    @return {function(err)} callback
  */
  Game.prototype.playMonopoly = function(resource, callback) {
    var playerId = this.getCurrentPlayerId();
    var canPlayMonopoly = this.client.canPlayMonopoly();

    if(!canPlayMonopoly) return callback({ error: "Cannot play Monopoly!" });

    this.proxy.playMonopoly(playerId, resource, callback);
  };

  /**
    Play a Monument card
    <pre>
    PRE: Valid resource card
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method playMonument
    @return {function(err)} callback
  */
  Game.prototype.playMonument = function(callback) {
    var playerId = this.getCurrentPlayerId();
    var canPlayMonument = this.client.canPlayMonument();

    if(!canPlayMonument) return callback({ error: "Cannot play Monument!" });

    this.proxy.playMonument(playerId, callback);
  };

  /**
    Tell the server we're starting a turn and rolling for the number
    <pre>
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method rollDice
    @return {function(err)} callback
  */
  Game.prototype.rollDice = function(callback) {
    var playerId = this.getCurrentPlayerId();
    var canRoll = this.client.canRoll();

    if(!canRoll) return callback({ error: "Cannot roll, it's not your turn!" });

    var number1 = catan.util.dice.rollDie();
    var number2 = catan.util.dice.rollDie();

    this.proxy.rollNumber(playerId, number1 + number2, callback);
  };

  /**
    Tell the server that a turn is now finished.
    <pre>
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method finishTurn
    @return {function(err)} callback
  */
  Game.prototype.finishTurn = function(callback) {
    var playerId = this.getCurrentPlayerId();
    var isMyTurn = this.client.isMyTurn();

    if(!isMyTurn) return callback({ error: "Cannot play finish turn, it's not your turn!" });

    this.proxy.finishTurn(playerId, callback);
  };

  return Game;
})();
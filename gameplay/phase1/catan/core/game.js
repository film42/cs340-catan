var catan = catan || {};
catan.core = catan.core || {};

catan.core.Game = (function() {

  // Constructor 
  function Game() {
    this.model = {};
    this.proxy = {};
  }

  Game.prototype.startGame = function(callback) {
    this.getState(function(err, resp) {
      model = new catan.models.ClientModel(resp);
      callback();
    });
    // Refresh every 2000 seconds
    setInterval(this.getState, 2000);
  };

  Game.prototype.refreshUI = function(callback) {};

  Game.prototype.getState = function(callback) {
    var that = this;
    proxy.getState(function(err, resp) {
      if(err) return callback(err);

      that.model = new catan.models.ClientModel(resp);
      that.refreshUI();
      callback(err);
    });
  };

  Game.prototype.getModel = function() {
    return this.model;
  };

  Game.prototype.getProxy = function() {
    return this.proxy;
  };

  Game.prototype.getCurrentUser = function() {
    return $.cookie('catan.user');
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
    var playerId = this.getCurrentUser();
    this.proxy.sendChat(playerId, message, function(err) {
      if(!err) callback(null);
      else callback(err);
    });
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
    var playerId = this.getCurrentUser();
    // TODO: Make sure this works fine with debugger
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
  Game.prototype.offerTrade = function(otherPlayerId, offer, request, callback) {};

  /**
    Discard a number of playing cards
    <pre>
    PRE: A list of cards
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {array} cards The resource cards the player will discard
    @param {function} callback The response callback
     
    @method discardCards
    @return {function(err)} callback
  */
  Game.prototype.discardCards = function(cards, callback) {};

  /**
    Build a road at a map Location
    <pre>
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
  Game.prototype.buildRoad = function(location, callback) {};


  /**
    Build a settlement at a map Location
    <pre>
    PRE: Any location on the map
    PRE: The caller provides a callback
    POST: Caller always calls callback
    </pre>

    @param {location} location A map location object
    @param {function} callback The response callback
     
    @method buildSettlement
    @return {function(err)} callback
  */
  Game.prototype.buildSettlement = function(location, callback) {};

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
  Game.prototype.buildCity = function(location, callback) {};

  /**
    Buy a development card
    <pre>
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method buyDevelopmentCard
    @return {function(err)} callback
  */
  Game.prototype.buyDevelopmentCard = function(callback) {};

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
  Game.prototype.playYearOfPlenty = function(resource1, resource2, callback) {};

  /**
    Play road building card
    <pre>
    PRE: Valid Location 1
    PRE: Valid Location 2
    POST: Caller always calls callback
    </pre>

    @param {Location} location1 The first location on which the player wants to build a road
    @param {Location} location2 The second location on which the player wants to build a road
    @param {function} callback The response callback
     
    @method playRoadBuilding
    @return {function(err)} callback
  */
  Game.prototype.playRoadBuilding = function(location1, location2, callback) {};

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
  Game.prototype.playSoldier = function(victimId, location, callback) {};

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
  Game.prototype.playMonopoly = function(resource, callback) {};

  /**
    Play a Monument card
    <pre>
    PRE: Valid resource card
    POST: Caller always calls callback
    </pre>

    @param {string} resource The resource card the player wants to collect
    @param {function} callback The response callback
     
    @method playMonument
    @return {function(err)} callback
  */
  Game.prototype.playMonument = function(resource, callback) {};

  /**
    Tell the server that a turn is now finished.
    <pre>
    POST: Caller always calls callback
    </pre>

    @param {function} callback The response callback
     
    @method finishTurn
    @return {function(err)} callback
  */
  Game.prototype.finishTurn = function(callback) {};

  return Game;
})();
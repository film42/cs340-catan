var catan = catan || {};
catan.core = catan.core || {};

catan.core.Game = (function() {

  // Constructor 
  function Game() {
    this.model = {};
    this.proxy = {};
  }

  Game.prototype.startGame = function(cb) {
    this.getState(function(err, resp) {
      model = new catan.models.ClientModel(resp);
      cb();
    });
    // Refresh every 2000 seconds
    setInterval(this.getState, 2000);
  };

  Game.prototype.refreshUI = function(cb) {};

  Game.prototype.getState = function(cb) {
    var that = this;
    proxy.getState(function(err, resp) {
      that.model = new catan.models.ClientModel(resp);
      that.refreshUI();
      cb();
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

  return Game;
})();
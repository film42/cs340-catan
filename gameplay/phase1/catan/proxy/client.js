var catan = catan || {};
catan.proxy = catan.proxy || {};

catan.proxy.ClientProxy = (function() {

    // Constructor 
    function ClientProxy() {

    }

    ClientProxy.prototype.sendChat = function(message, callback) {};

    ClientProxy.prototype.acceptTrade = function(status, callback) {};

    ClientProxy.prototype.offerTrade = function(offer, callback) {};

    ClientProxy.prototype.discardCards = function(cards, callback) {};

    ClientProxy.prototype.buildRoad = function(location, callback) {};

    ClientProxy.prototype.buildSettlement = function(location, callback) {};

    ClientProxy.prototype.buildCity = function(location, callback) {};

    ClientProxy.prototype.buyDevelopmentCard = function(callback) {};

    ClientProxy.prototype.yearOfPlenty = function(res1, res2, callback) {};

    ClientProxy.prototype.roadBuilding = function(loc1, loc2, callback) {};

    ClientProxy.prototype.soldier = function(vicitim, location, callback) {};

    ClientProxy.prototype.monopoly = function(resource, callback) {};

    ClientProxy.prototype.monument = function(resource, callback) {};

    ClientProxy.prototype.finishTurn = function(callback) {};

    return ClientProxy;
})();
/**
  This module contains the top-level client model class

  @module                catan.models
  @namespace             catan.models
*/

var catan = catan || {};
catan.models = catan.models || {};

catan.models.ClientModel = (function() {

  // Constructor 
  function ClientModel(json) {

  }

  //
  // Getters
  //

  ClientModel.prototype.getPlayers = function() {};

  ClientModel.prototype.getBank = function() {};

  ClientModel.prototype.getDeck = function() {};

  ClientModel.prototype.getChat = function() {};

  ClientModel.prototype.getTurn = function() {};

  ClientModel.prototype.getTradeOffer = function() {};

  ClientModel.prototype.getMap = function() {};

  //
  // Cans
  //

  ClientModel.prototype.canBuydevelomentCard = function() {};

  ClientModel.prototype.canBuyRoad = function() {};

  ClientModel.prototype.canBuySettlement = function() {};

  ClientModel.prototype.canBuyCity = function() {};

  ClientModel.prototype.canBuyResources = function() {};

  ClientModel.prototype.canOfferTrade = function() {};

  ClientModel.prototype.canAcceptTrade = function() {};

  ClientModel.prototype.needDiscardCard = function() {};

  ClientModel.prototype.canPlaceRood = function() {};

  ClientModel.prototype.canPlaceSettlement = function() {};

  ClientModel.prototype.canPlaceCity = function() {};

  return ClientModel;
})();
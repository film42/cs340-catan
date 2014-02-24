/**
 * This is the namespace for domestic trading
 * 
 * @module catan.trade
 * @submodule catan.trade.domestic
 * @namespace domestic
 */

var catan = catan || {};
catan.trade = catan.trade || {};
catan.trade.domestic = catan.trade.domestic || {};

catan.trade.domestic.Controller = (function trade_namespace() {

  var Controller = catan.core.BaseController;
  var Definitions = catan.definitions;
  var ResourceTypes = Definitions.ResourceTypes;
  var State;
  var DomesticController = (function DomesticController_Class() {

    /**
     * @class DomesticController
     * @constructor
     * @extends misc.BaseController
     * @param {domestic.View}
     *          view
     * @param {misc.WaitOverlay}
     *          waitingView
     * @param {domestic.AcceptView}
     *          acceptView
     * @param {core.Game}
     *          game
     */
    function DomesticController(view, waitingView, acceptView, game) {
      Controller.call(this, view, game);
      this.waitingView = waitingView;
      this.acceptView = acceptView;
      this.game = game;
      this.view = view;
      // Setup player stuff -- Include all players BUT the current player
      this.player = this.game.getCurrentPlayer();
      var self = this;
      var tradeablePlayers = this.game.getModel().getPlayers().filter(function(p) {
        if (p.getOrderNumber() != self.player.getOrderNumber()){
          p.index = p.getOrderNumber(); // the view expects this
          return true;
        }
        return false;
      });
      
      view.setPlayers(tradeablePlayers);
      


      // TODO: This needs to be worked on so THIS is preserved.
      this.game.addObserver(this, this.OnUpdatedModel);
    };
    DomesticController.prototype = core.inherit(Controller.prototype);
    
    DomesticController.prototype.OnUpdatedModel = function(err){
      if (err) {
        console.log(err);
        return;// The trade failed somehow
      }
      
      if(this.game.model.isMyTurn() && this.game.model.getTurn().isPlayingPhase()){
        this.view.setPlayerSelectionEnabled(true);
        this.view.setResourceSelectionEnabled(true);
        this.updateState();
      } else if (!this.game.model.isMyTurn()){
        this.view.setPlayerSelectionEnabled(false);
        this.view.setResourceSelectionEnabled(false);
        this.view.setTradeButtonEnabled(false);
      }
    };

    core.defineProperty(DomesticController.prototype, "resourceToSend");// int
    core.defineProperty(DomesticController.prototype, "resourceToReceive");// int
    core.defineProperty(DomesticController.prototype, "otherPlayer");
    core.defineProperty(DomesticController.prototype, "receiveQty");// int
    core.defineProperty(DomesticController.prototype, "sendQty");// int

    /** ****** Methods called by the Domestic View ******** */

    /**
     * @method setResourceToSend
     * @param{String} resource the resource to send
     *                ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    DomesticController.prototype.setResourceToSend = function(resource) {
      console.log("Setting resource to SEND");
      var self = this;
      if (self.sendQty != undefined){
        this.getView().setResourceAmountChangeEnabled(self.resourceToSend, false, false);
        this.getView().setResourceAmount(self.resourceToSend, undefined);
        this.getView().clearTradeViewForResource(self.resourceToSend);
      } else if (self.setResourceToReceive == resource){
        this.getView().setResourceAmountChangeEnabled(self.resourceToReceive, false, false);
        this.getView().setResourceAmount(self.resourceToReceive, undefined);
        this.getView().clearTradeViewForResource(self.resourceToReceive);
      }
      self.sendQty = 0;
      var quickJson = { 
          "brick" : 0,
          "ore" : 0,
          "sheep" : 0,
          "wheat" : 0,
          "wood" : 0
      };
      quickJson[resource] = self.sendQty + 1;
      var shouldIncrease = this.player.hasXResources(new catan.models.ResourceList(quickJson));
      var shouldDecrease = false; // Can't offer negative numbers
      this.resourceToSend = resource;
      this.getView().setResourceAmountChangeEnabled(resource, shouldIncrease,shouldDecrease);
      this.getView().setResourceAmount(resource, self.sendQty);
      
      this.updateState();
    };

    /**
     * @method setResourceToReceive
     * @param{String} resource the resource to receive
     *                ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    DomesticController.prototype.setResourceToReceive = function(resource) {
      console.log("Setting resource to RECEIVE");
      // TODO: figure out how to set other radio buttons to null if they are on RECEIVE
      var self = this;
      if (self.receiveQty != undefined){
        this.getView().setResourceAmountChangeEnabled(self.resourceToReceive, false, false);
        this.getView().setResourceAmount(self.resourceToReceive, undefined);
        this.getView().clearTradeViewForResource(self.resourceToReceive);
      } else if (self.setResourceToSend == resource){
        this.getView().setResourceAmountChangeEnabled(self.resourceToSend, false, false);
        this.getView().setResourceAmount(self.resourceToSend, undefined);
        this.getView().clearTradeViewForResource(self.resourceToSend);
      }    
      self.receiveQty = 0;
      var shouldIncrease = true; // You can demand all you want
      var shouldDecrease = false; // Can't demand negative numbers
      this.resourceToReceive = resource;
      this.getView().setResourceAmountChangeEnabled(resource, shouldIncrease,shouldDecrease);
      this.getView().setResourceAmount(resource, self.receiveQty);
      
      this.updateState();
    };

    /**
     * @method unsetResource
     * @param{String} resource the resource to clear
     *                ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    DomesticController.prototype.unsetResource = function(resource) {
      if (this.resourceToReceive == resource){
        this.resourceToRecieve = "null";
        this.receiveQty = undefined;
      } else if (this.resourceToSend == resource){
        this.resourceToSend = "null";
        this.sendQty = undefined;
      }

      // make it disappear
      this.getView().setResourceAmountChangeEnabled(resource, false, false);
      this.getView().setResourceAmount(resource, undefined);
    };

    /**
     * @method setPlayerToTradeWith
     * @param{int} playerNumber the player to trade with
     * @return void
     */
    DomesticController.prototype.setPlayerToTradeWith = function(playerNumber) {
      if (playerNumber == undefined){
        playerNumber = undefined;
      } else if (playerNumber != -1)
        this.otherPlayer = this.ClientModel.getModel().getPlayerWithOrder(playerNumber);
      else
        this.otherPlayer = undefined;
      this.updateState();
    };

    /**
     * Increases the amount to send or receive of a resource
     * 
     * @method increaseResourceAmount
     * @param{String} resource ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    DomesticController.prototype.increaseResourceAmount = function(resource) {
      var shouldDecrease;
      var shouldIncrease;
      var amount;
      var self = this;

      if (this.resourceToReceive == resource) {
        this.receiveQty++;
        shouldIncrease = true;
        shouldDecrease = self.receiveQty != 0;
        amount = self.receiveQty;
      } else if (this.resourceToSend == resource) {
        this.sendQty++;
        var quickJson = { 
            "brick" : 0,
            "ore" : 0,
            "sheep" : 0,
            "wheat" : 0,
            "wood" : 0
        };
        quickJson[resource] = self.sendQty + 1;
        shouldIncrease = this.player.hasXResources(new catan.models.ResourceList(quickJson));
        shouldDecrease = self.sendQty != 0;
        amount = self.sendQty;
      } else
        return;

      this.getView().setResourceAmountChangeEnabled(resource, shouldIncrease,
          shouldDecrease);
      this.getView().setResourceAmount(resource, amount);
    };

    /**
     * Decreases the amount to send or receive of a resource
     * 
     * @method decreaseResourceAmount
     * @param{String} resource ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    DomesticController.prototype.decreaseResourceAmount = function(resource) {
      var shouldDecrease;
      var shouldIncrease;
      var amount;
      var self = this;
      
      if (this.resourceToReceive == resource) {
        this.receiveQty--;
        shouldIncrease = true;
        shouldDecrease = self.receiveQty != 0;
        amount = self.receiveQty;
      } else if (this.resourceToSend == resource) {
        this.sendQty--;
        var quickJson = { 
            "brick" : 0,
            "ore" : 0,
            "sheep" : 0,
            "wheat" : 0,
            "wood" : 0
        };
        quickJson[resource] = self.sendQty + 1;
        shouldIncrease = this.player.hasXResources(new catan.models.ResourceList(quickJson));
        shouldDecrease = self.sendQty != 0;
        amount = self.sendQty;
      } else
        return;

      this.getView().setResourceAmountChangeEnabled(resource, shouldIncrease,
          shouldDecrease);
      this.getView().setResourceAmount(resource, amount);
    };
    
    DomesticController.prototype.updateState = function(){
      if (this.resourceToSend == undefined || this.resourceToReceive == undefined){
        this.getView().setStateMessage("Select a resource to send and receive");
        this.getView().setTradeButtonEnabled(false);
      } else if (this.otherPlayer == undefined){
        this.getView().setStateMessage("Select a player to trade with");
        this.getView().setTradeButtonEnabled(false);
      } else {
        this.getView().setStateMessage("TRADE!");
        this.getView().setTradeButtonEnabled(true);
      }
      
    };

    /**
     * Sends the trade offer to the accepting player
     * 
     * @method sendTradeOffer
     * @return void
     */
    DomesticController.prototype.sendTradeOffer = function() {
      // prepare our trade offer (in the form of a ResourceList)
      var brick = 0;
      var sheep = 0;
      var wheat = 0;
      var ore = 0;
      var wood = 0;

      if (this.resourceToReceive === "brick")
        brick = this.receiveQty;
      else if (this.resourceToSend === "brick")
        brick = -this.sendQty;

      if (this.resourceToReceive === "sheep")
        sheep = this.receiveQty;
      else if (this.resourceToSend === "sheep")
        sheep = -this.sendQty;

      if (this.resourceToReceive === "wheat")
        wheat = this.receiveQty;
      else if (this.resourceToSend === "wheat")
        wheat = -this.sendQty;

      if (this.resourceToReceive === "ore")
        ore = this.receiveQty;
      else if (this.resourceToSend === "ore")
        ore = -this.sendQty;

      if (this.resourceToReceive === "wood")
        wood = this.receiveQty;
      else if (this.resourceToSend === "wood")
        wood = -this.sendQty;

      var list = new catan.models.ResourceList({ 
        "brick" : brick,
        "ore" : ore,
        "sheep" : sheep,
        "wheat" : wheat,
        "wood" : wood
    });
      this.game.offerTrade(this.otherPlayer.getOrderNumber(), list, this.dissappearWaitingModal);
      this.waitingView.showModal();
    };

    DomesticController.prototype.dissappearWaitingModal = function(){
      this.waitingView.hideModal();
      this.onUpdatedModel();
    }
    /** ***************** Methods called by the Accept Overlay ************ */

    /**
     * Finalizes the trade between players
     * 
     * @method acceptTrade
     * @param{Boolean} willAccept
     * @return void
     */
    DomesticController.prototype.acceptTrade = function(willAccept) {
      this.getGame().acceptTrade(willAccept, onUpdatedModel);
    };

    return DomesticController;
  }());

  return DomesticController;
}());

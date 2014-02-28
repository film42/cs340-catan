/**
    This is the namespace for development cards
    @module catan.devCards
    @namespace devCards
*/

var catan = catan || {};
catan.devCards = catan.devCards || {};

catan.devCards.Controller = (function(){

  var Controller = catan.core.BaseController;
  var Definitions = catan.definitions;

  var DevCardController = (function card_namespace(){

    core.forceClassInherit(DevCardController,Controller);

    core.defineProperty(DevCardController.prototype, "BuyView");

    /**
     * @class DevCardController
     * @constructor
     * @extends misc.BaseController
     * @param {devCards.DevCardView} view
     * @param {devCards.BuyCardView} buyView
     * @param {models.game} game
     * @param {function} soldierAction
     * @param {function} roadAction
     */
    function DevCardController(view, buyView, game, soldierAction, roadAction){
      Controller.call(this,view,game);
      this.setBuyView(buyView);
      this.view = view;
      this.buyView = buyView;
      this.game = game;
      this.roadAction = roadAction;
      this.soldierAction = soldierAction;

      this.game.addObserver(this, this.onUpdateModel);
    }

    DevCardController.prototype.setLegalActions = function(){
      var model = this.game.getModel();
      var player = this.game.getCurrentPlayer();

      this.view.setCardEnabled("soldier",      player.canPlaySoldier());
      this.view.setCardEnabled("yearOfPlenty", player.canPlayYearOfPlenty());
      this.view.setCardEnabled("monopoly",     player.canPlayMonopoly());
      this.view.setCardEnabled("roadBuilding", player.canPlayRoadBuilding());
      this.view.setCardEnabled("monument",     player.canPlayMonument());

      this.view.updateAmount("soldier",      player.getNewSoldierCount());
      this.view.updateAmount("yearOfPlenty", player.getNewYearOfPlentyCount());
      this.view.updateAmount("monopoly",     player.getNewMonopolyCount());
      this.view.updateAmount("roadBuilding", player.getNewRoadBuildingCount());
      this.view.updateAmount("monument",     player.getNewDevCardCount());
    };

    DevCardController.prototype.onUpdateModel = function(){
        this.setLegalActions();
    };

    /**
     * Called when the player buys a development card
     * @method buyCard
     * @return void
     */
    DevCardController.prototype.buyCard = function(){
      this.game.buyDevelopmentCard(function() {});
      this.view.closeModal();
    };

    /**
     * Called when the player plays a year of plenty card
     * @method useYearOfPlenty
     * @param {String} resource1 The first resource to obtain
     * @param {String} resource2 The second resource to obtain
     * @return void
     */
    DevCardController.prototype.useYearOfPlenty = function(resource1, resource2){
      this.game.playYearOfPlenty(resource1, resource2, function() {});
      this.view.clearView();
      this.view.closeModal();
    };

    /**
     * Called when the player plays a monopoly card
     * @method useMonopoly
     * @param {String} resource the resource to obtain
     * @return void
     */
    DevCardController.prototype.useMonopoly = function(resource){
      this.game.playMonopoly(resource, function() {});
      this.view.clearView();
      this.view.closeModal();
    };

    /**
     * Called when the player plays a monument card
     * @method useMonument
     * @return void
     */
    DevCardController.prototype.useMonument = function(){
      this.game.playMonument(function() {});
      this.view.clearView();
      this.view.closeModal();
    };

    /**
     * Called when the player plays a soldier card
     * @method useSoldier
     * @return void
     */
    DevCardController.prototype.useSoldier = function(){
      this.soldierAction();
      this.view.clearView();
      this.view.closeModal();
    };

    /**
     * Called when the player plays the road building card
     * @method useRoadBuild
     * @return void
     */
    DevCardController.prototype.useRoadBuild = function(resource){
      this.roadAction();
      this.view.clearView();
      this.view.closeModal();
    };

    return DevCardController;
  }());

  return DevCardController;
}());

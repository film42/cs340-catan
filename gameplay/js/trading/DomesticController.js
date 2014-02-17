/**
    This is the namespace for domestic trading
    @module catan.trade
    @submodule catan.trade.domestic
    @namespace domestic
*/

var catan = catan || {};
catan.trade = catan.trade ||{};
catan.trade.domestic = catan.trade.domestic ||{};

catan.trade.domestic.Controller= (function trade_namespace(){

	var Controller = catan.core.BaseController;
	var Definitions = catan.definitions;
	var ResourceTypes = Definitions.ResourceTypes;
    
	var DomesticController = ( function DomesticController_Class() {
    
		/** 
		@class DomesticController
		@constructor 
		@extends misc.BaseController
		@param {domestic.View} view
		@param {misc.WaitOverlay} waitingView
		@param {domestic.AcceptView} acceptView
		@param {models.ClientModel} clientModel
		*/
		function DomesticController(view,waitingView,acceptView,clientModel){
			Controller.call(this,view,clientModel);
			this.waitingView = waitingView;
			this.acceptView = acceptView;
		};
        
		DomesticController.prototype = core.inherit(Controller.prototype);
		core.defineProperty(DomesticController.prototype, "resourceToSend");//int (Enum of ResourceEnum)
		core.defineProperty(DomesticController.prototype, "resourceToReceive");//int (Enum of ResourceEnum)
		core.defineProperty(DomesticController.prototype, "otherPlayer");//int (PlayerID)
		core.defineProperty(DomesticController.prototype, "receiveQty");//int
		core.defineProperty(DomesticController.prototype, "sendQty");//int
		
		
		/******** Methods called by the Domestic View *********/
        
        /**
        * @method setResourceToSend
        * @param{String} resource the resource to send ("wood","brick","sheep","wheat","ore")
        * @return void
        */
		DomesticController.prototype.setResourceToSend = function(resource){
		  // Make sure that we aren't setting the send and receive to the same resource
		  if (!this.resourceToReceive === resource)
		    this.resourceToSend = resource; 
		};
        
		/**
		 * @method setResourceToReceive
		 * @param{String} resource the resource to receive ("wood","brick","sheep","wheat","ore")
		 * @return void
		 */
		 DomesticController.prototype.setResourceToReceive = function(resource){
	    if (!this.resourceToSend === resource)
	        this.resourceToReceive = resource;
		};
        
		/**
		  * @method unsetResource
		  * @param{String} resource the resource to clear ("wood","brick","sheep","wheat","ore")
		  * @return void
		  */
		DomesticController.prototype.unsetResource = function(resource){
      if (!this.resourceToReceive === resource)
        this.resourceToRecieve = "null";
      if (!this.resourceToSend === resource)
        this.resourceToSend = "null";
		};
        
		/**
		 * @method setPlayerToTradeWith
		 * @param{int} playerNumber the player to trade with
		 * @return void
		 */
		DomesticController.prototype.setPlayerToTradeWith = function(playerNumber){
		  this.otherPlayer = playerNumber;
		};
        
		/**
		* Increases the amount to send or receive of a resource
		* @method increaseResourceAmount
		* @param{String} resource ("wood","brick","sheep","wheat","ore")
		* @return void
		*/
		DomesticController.prototype.increaseResourceAmount = function(resource){
		  if (!this.resourceToReceive === resource)
        this.receiveQty++;
      if (!this.resourceToSend === resource)
        this.sendQty++;
		};
        
		/**
		 * Decreases the amount to send or receive of a resource
		 * @method decreaseResourceAmount
		 * @param{String} resource ("wood","brick","sheep","wheat","ore")
		 * @return void
		 */
		DomesticController.prototype.decreaseResourceAmount = function(resource){
      if (!this.resourceToReceive === resource)
        this.receiveQty--;
      if (!this.resourceToSend === resource)
        this.sendQty--;
		};
        
		/**
		  * Sends the trade offer to the accepting player
		  * @method sendTradeOffer
		  * @return void
		  */
		DomesticController.prototype.sendTradeOffer = function(){
		  //prepare our trade offer (in the form of a ResourceList)
		  var brick = 0;
	    var sheep = 0;
	    var wheat = 0;
	    var ore   = 0;
	    var wood  = 0;
	    
	    if(this.resourceToReceive === "brick")
	      brick = this.recieveQty;
	    else if (this.resourceToSend === "brick")
	      brick = this.sendQty;
	    
	    if(this.resourceToReceive === "sheep")
        sheep = this.recieveQty;
      else if (this.resourceToSend === "sheep")
        sheep = this.sendQty;
      
	    if(this.resourceToReceive === "wheat")
        wheat = this.recieveQty;
      else if (this.resourceToSend === "wheat")
        wheat = this.sendQty;
      
	    if(this.resourceToReceive === "ore")
        ore = this.recieveQty;
      else if (this.resourceToSend === "ore")
        ore = this.sendQty;
      
	    if(this.resourceToReceive === "wood")
        wood = this.recieveQty;
      else if (this.resourceToSend === "wood")
        wood = this.sendQty;
	    
	    var game = this.getGame();
	    var list = new catan.models.ResourceList({});
      list.setResourceListItems(brick, ore, sheep, wheat, wood);
      
		  game.offerTrade(this.otherPlayer, list, onUpdateModel);
		};
        
        
		/******************* Methods called by the Accept Overlay *************/
		 
        /**
        * Finalizes the trade between players
        * @method acceptTrade
        * @param{Boolean} willAccept
        * @return void
		*/
		DomesticController.prototype.acceptTrade = function(willAccept){
		  this.getGame().acceptTrade(willAccept,onUpdateModel);
		};
            
		DomesticController.prototype.onUpdateModel(err){
		  if (err){
		    console.log(err);
		    return;//The trade failed somehow
		  }
		  console.log("The trade was successful");
		  // Success (not sure what goes here that doesn't belong in the acceptTrade())
		  // this.acceptTrade(true);
		}
		
		
		return DomesticController;
    }());
			
	return DomesticController;
}());



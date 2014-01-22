 /**
    This is the namespace for domestic trading
    @module catan.trade
    @submodule catan.trade.domestic
    @namespace domestic
*/       

var catan = catan || {};
catan.trade = catan.trade || {};
catan.trade.domestic = catan.trade.domestic || {};
 
catan.trade.domestic.View = (function(){

	var DisplayElement = catan.definitions.DisplayElement;
	var Definitions = catan.definitions;
	var SELECTOR_LABEL_TEXT = "player to trade with:"
	var GROUP_NAME = Definitions.GroupNames.domesticTrade;
	var ResourceTypes = Definitions.ResourceTypes;
	var NULL_LABEL = "none";
    var SEND_LABEL = "send";
    var RECEIVE_LABEL = "receive";
	
	var DomesticView = (function(){
		
		/**
		 * A view for setting up trades between players.	
		@class DomesticView 
		@constructor
		**/
		function DomesticView(){
			this.setDisplayElems({});
			this.players = undefined;
		};
        
		core.defineProperty(DomesticView.prototype, "Controller");
		core.defineProperty(DomesticView.prototype, "SelectorElem");
		core.defineProperty(DomesticView.prototype, "TradeButton");
		core.defineProperty(DomesticView.prototype, "DisplayElems");
		core.defineProperty(DomesticView.prototype, "MessageElem");
		core.defineProperty(DomesticView.prototype, "players");
				
		/**
		 Attaches the controller to the view and builds the view on the page.
		 @method setController
		 @param {domestic.Controller} controller
		 @return void
		 */
        DomesticView.prototype.setController = function(controller){
			this.Controller = controller;
			buildView.call(this);
		};
		
		/**
		 * Sets the opponents to trade with for the player in the view. 
		 * This should be called on initializing the controller, and only called once.
		 * @method setPlayers
		 * @param {Array} players The player's three opponents. Each must be of the form
		 	{
				name: the name of the player,
				color: the player's game color,
				index: the gameplay index of the player
			}
         * @return void
		 */
		DomesticView.prototype.setPlayers = function(players){
			if(this.players == undefined)
				this.players = players;
		}
        
		/**
		 * Turns on or off the ability to trade resources
		 * @method setResourceSelectionEnabled
		 * @param {Boolean} shouldEnable whether the player can trade resources or not
		 * @return void
		 */
		DomesticView.prototype.setResourceSelectionEnabled = function(shouldEnable){
			//--for off-turn times
			//hide all the selectors and all the amountChangers
			for(index in ResourceTypes){
				var dElem = this.getDisplayElems()[ResourceTypes[index]];
				if(shouldEnable){
					dElem.getChooserElem().show();
				}else{
					dElem.getChooserElem().hide();
				}
			}
		}
        
		/**
		 * Turns on or off the ability to select a player to trade with
		 * @method setPlayerSelectionEnabled
		 * @param {Boolean} shouldEnable whether the player can trade or not
		 * @return void
		 */
		DomesticView.prototype.setPlayerSelectionEnabled = function(shouldEnable){
			if(shouldEnable)
				this.getSelectorElem().show();
			else
				this.getSelectorElem().hide();				
		}
        
		/**
		 * Turns on or off the ability to set amounts to trade for a reosurce
		 * @method setResourceAmountChangeEnabled
		 * @param {String} resource the element to change (a resource: "wood","brick","sheep","wheat","ore")
		 * @param {Boolean} shouldIncrease whether the player can set more of the resource
		 * @param {Boolean} shouldDecrease whether the player can set less of the resource
		 * @return void
		 */
		DomesticView.prototype.setResourceAmountChangeEnabled = function(resource, shouldIncrease, shouldDecrease){
		 	if(!shouldIncrease && !shouldDecrease){
				this.getDisplayElems()[resource].getAmountChangeElem().hide();
			}
			else{
				this.getDisplayElems()[resource].getAmountChangeElem().show(shouldIncrease, shouldDecrease);
			}
		}
        
		/**
		 * Displays the amount for a resource
		 * @method setResourceAmount
		 * @param {String} resource the element to change (a resource: "wood","brick","sheep","wheat","ore")
		 * @param {int} amount the current amount the player wants to trade
		 * @return void
		 */
		DomesticView.prototype.setResourceAmount= function(resource, amount){
			if(amount == undefined)
				amount = "";
			this.getDisplayElems()[resource].getAmountChangeElem().displayAmount(amount);
		}
        
		/**
		 * Turns on or off the ability to trade
		 * @method setTradeButtonEnabled
		 * @param {Boolean} shouldEnable whether the player can trade or not
		 * @return void
		 */
		DomesticView.prototype.setTradeButtonEnabled = function(shouldEnable){
			if(shouldEnable)
				this.getTradeButton().enable();
			else
				this.getTradeButton().disable();
		}
        
		/**
		 * Displays the message indicating the state of the trade
		 * @method setStateMessage
		 * @param {String} message the message to display
		 * @return void
		 */
		DomesticView.prototype.setStateMessage = function(message){
			this.getTradeButton().setMessage(message);
		}
        
		/**
		 * Resets the view
		 * @method clearTradeView
		 * @return void
		 */
		DomesticView.prototype.clearTradeView = function(){
			for(index in ResourceTypes)
				this.getDisplayElems()[ResourceTypes[index]].resetView();
			this.getSelectorElem().reset();
		}
		
		//for the chooser element            
                		
		var constructChooserResourceOptions = function(resourceType){
			var options = new Array();
			var ctrl = this.getController();
			options.push({
					label:"send",
					action: core.makeAnonymousAction(ctrl, ctrl.setResourceToSend, [resourceType])
				});
			options.push({
					label:NULL_LABEL,
					action:core.makeAnonymousAction(ctrl, ctrl.unsetResource, [resourceType]),
					selected :true
				});
			options.push({
					label:"receive",
					action:core.makeAnonymousAction(ctrl, ctrl.setResourceToReceive, [resourceType])
				});
			return options;
		}
		
		var constructChooserPeopleOptions = function(){
			var options = new Array();
			var ctrl = this.getController();
			var players = this.getPlayers();
			if(players == undefined)
				players = [];	
			
			//NULL_PLAYER_OPTION
			options.push({
				label:NULL_LABEL,
				color:"white",
				action :core.makeAnonymousAction(ctrl, ctrl.setPlayerToTradeWith, [-1]),
				selected :true
			});
			
			//real players
			for(index in players){
				options.push({
					label: players[index].name,
					color: players[index].color,
					action: core.makeAnonymousAction(ctrl, ctrl.setPlayerToTradeWith, 
														[players[index].index])
				});
			}
			return options;
		}
                
		//method to construct the table
		var buildView = function(){
			var parentDiv = document.getElementById(Definitions.PageViewIDs.domesticArea);
			var tradeArea = document.createElement("div");
			tradeArea.setAttribute("class","trade-display");

			var people = new DisplayElement.ChooserElement("people", constructChooserPeopleOptions.call(this));
			this.setSelectorElem(people);
						
			var tradeDiv = buildTradeElements.call(this);
				tradeArea.appendChild(tradeDiv);
				
			var buttonAction = core.makeAnonymousAction(this.getController(), this.getController().sendTradeOffer);
			var button = new DisplayElement.ButtonArea(buttonAction);
				this.setTradeButton(button);
				
			var selectDiv = document.createElement("div");
				selectDiv.appendChild(people.getView());
				selectDiv.appendChild(button.getView());
				tradeArea.appendChild(selectDiv);
			
			parentDiv.appendChild(tradeArea);
		};
		
		//private: make an empty table
		var buildTradeElements = function(){
			var tradeArea = document.createElement("div");
				tradeArea.setAttribute("class","domestic-resource-display-area");
				
			var groupName = GROUP_NAME;
			var ctrl = this.getController();
			
			for(index in ResourceTypes){
				var resource = ResourceTypes[index];
				var chooser = new DisplayElement.ChooserElement(resource, 
														constructChooserResourceOptions.call(this, resource));
				var amountChanger = new DisplayElement.AmountChangeElement(resource,
											core.makeAnonymousAction(ctrl, ctrl.increaseResourceAmount, [resource]),
											core.makeAnonymousAction(ctrl, ctrl.decreaseResourceAmount, [resource]));
				var displayElem = new DisplayElement.ComboElement(groupName, resource, 
																	undefined, amountChanger,chooser);					
				this.getDisplayElems()[resource] = displayElem;
				tradeArea.appendChild(displayElem.getView());//here too...
			}
			return tradeArea;
		};
		
		return DomesticView;
	}());
        
	return DomesticView;
}());


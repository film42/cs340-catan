/**
    This is the namespace for maritime trading
    @module catan.trade
    @submodule catan.trade.maritime
    @namespace maritime
*/

var catan = catan || {};
catan.trade = catan.trade || {};
catan.trade.maritime = catan.trade.maritime || {};

catan.trade.maritime.Controller = (function trade_namespace(){
    
  var Definitions = catan.definitions;
  var ResourceTypes = Definitions.ResourceTypes;
    
  var MaritimeController = ( function MaritimeController_Class() {

    var Controller = catan.core.BaseController;
        
        /**
    @class MaritimeController
    @constructor 
    @extends misc.BaseController
    @param {maritime.View} view
    @param {models.ClientModel} clientModel
    */
    function MaritimeController(view,game){
      Controller.call(this,view,clientModel);

      this.game.addObserver(OnUpdatedModel);
      state = selGiveState;
    }

    MaritimeController.prototype = core.inherit(Controller.prototype);

    // Because Controller is too vague
    var DiscardController = catan.discard.Controller;
    core.defineProperty(DiscardController.prototype,"typeToGet");
    core.defineProperty(DiscardController.prototype,"giveAmount");
    core.defineProperty(DiscardController.prototype,"typeToGive");
    core.defineProperty(DiscardController.prototype,"state");
    core.defineProperty(DiscardController.prototype,"ratios");


    
    /**
    --------Maritime Trade State Controller--------
    States:
      1. GiveSelState
        a. setGiveValue
      2. GetSelState
        a. setGetValue
        b. unsetGiveValue
      3. AcceptState
        a. makeTrade
        b. unsetGetValue
        c. unsetGiveValue
    */
    var selGiveState = {
      setGiveValue: function(resource){
        typeToGive = resource;
        this.view.selectGiveOption(resource, ratios[resource]);
        this.view.hideGiveOptions();
        if(!typeToGet){
          state = selGetState;
          state.OnUpdatedModel();
        }else{
          state = acceptState;
          state.OnUpdatedModel();
          this.view.enableTradeButton(true);
        }
      },
      OnUpdatedModel: function(){
        var types = catan.definitions.ResourceTypes;
        if(handleTurn()){
          var canTradeResources = [];
          var ratios = {};
          for(var i=0; i<types.length; i++){
            var ratio = canTrade(types[i]);
            if(ratio > 0){
              ratios[types[i]] = ratio;
              canTradeResources.append(types[i]);
            }
          }
          if(canTradeResources.length > 0){
            this.view.showGiveOptions(canTradeResources);
          }else{
            this.view.hideGiveOptions();
            this.view.hideGetOptions();
            this.view.setMessage("Not Enough Resources To Trade");
          }
        }
      }
      //leave all other state methods undefined 
    };
    var selGetState = {
      setGetValue : function(resource){
        typeToGet = resource;
        //for some reason this has a port amount parameter as well???
        selectGetOption(resource, 1);
        state = acceptState;
        this.view.hideGetOptions();
        this.view.enableTradeButton(true);
        this.view.setMessage("Trade?");
      },
      unsetGiveValue: unsetGiveValueFromState,
      OnUpdatedModel: function(){
        var types = catan.definitions.ResourceTypes;
        if(handleTurn()){
          var canGetResources = [];
          for(var i=0; i<types.length; i++){
            if(this.game.getClientModel().getBank()[types[i]] > 0){
              canGetResources.append(types[i]);
            }
          }
          if(canGetResources.length > 0){
            this.view.showGiveOptions(canGetResources);
          }else{
            this.view.hideGiveOptions();
            this.view.hideGetOptions();
            this.view.setMessage("The Bank is broke! Someone roll a 7.");
          }
        }
      }
    };
    var acceptState = {
      makeTrade : function(){
        this.game.maritimeTrade(typeToGive, ratios[typeToGive], typeToGet);
      },
      unsetGiveValue : unsetGiveValueFromState,
      unsetGetValue : function(){
        typeToGet = undefined;
        this.view.enableTradeButton(false);
        state = selGetState;
        state.OnUpdatedModel();
      },
      OnUpdatedModel: function(){
        handleTurn();
        this.view.hideGiveOptions();
        this.view.hideGetOptions();
      }
    };

    var unsetGiveValueFromState = function(){
      typeToGive = undefined;
      state = selGiveState;
      state.OnUpdatedModel();
      this.view.enableTradeButton(false);
    };

    var handleTurn = function(){
      if(!this.game.getClientModel().isMyTurn()){
        this.view.hideGiveOptions();
        this.view.hideGetOptions();
        this.view.setMessage("It is not your turn");
        return false;
      }
      return true;
    };

    /**
    Private function
    checks a given resource to see if the player can trade it.
    */
    var canTrade = function(resourceType, has3to1Port){
      var model = this.game.getClientModel();
      var player = this.game.getCurrentPlayer();
      var playerId = this.game.getCurrentPlayerId();
      var map = model.getMap();
      var resources = player.getResources();
      if(resources[resourceType] >= 2){
        if(map.canMaritimeTrade(playerId, 2 ,resourceType))
          return 2;
      }
      if(resources[resourceType] >= 3){
        if(has3to1Port)
          return 3;
      }
      if(resources[resourceType] >= 4){
        return 4;
      }
      return -1;
      

    };


    var OnUpdatedModel = function(){
      if(state.OnUpdatedModel)
        state.OnUpdatedModel();
    };

    /**
         * Called by the view when the player "undoes" their give selection
     * @method unsetGiveValue
     * @return void
     */
    MaritimeController.prototype.unsetGiveValue = function(){
      if(state.unsetGiveValue)
        state.unsetGiveValue();
    };
        
    /**
         * Called by the view when the player "undoes" their get selection
     * @method unsetGetValue
     * @return void
     */
    MaritimeController.prototype.unsetGetValue = function(){
      if(state.unsetGetValue)
        state.unsetGetValue();
    };
        
    /**
         * Called by the view when the player selects which resource to give
     * @method setGiveValue
     * @param{String} resource The resource to trade ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    MaritimeController.prototype.setGiveValue = function(resource){
      if(state.setGiveValue)
        state.setGiveValue(resource);
    };
        
    /**
         * Called by the view when the player selects which resource to get
     * @method setGetValue
     * @param{String} resource The resource to trade ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    MaritimeController.prototype.setGetValue = function(resource){
      if(state.setGetValue)
        state.setGetValue(resource);
    };
        
    /** Called by the view when the player makes the trade
     * @method makeTrade
     * @return void
     */
    MaritimeController.prototype.makeTrade= function(){
      if(state.makeTrade)
        state.makeTrade();
    };

    return MaritimeController;
  }());

  return MaritimeController;
}());



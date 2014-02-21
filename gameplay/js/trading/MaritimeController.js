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
      Controller.call(this,view,game);

      this.game = game;

      // TODO: This needs to be worked on so THIS is preserved.
      this.game.addObserver(this, this.OnUpdatedModel);
      this.state = selGiveState;
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
        this.OnUpdatedModel();
        typeToGive = resource;
        this.getView().selectGiveOption(resource, this.ratios[resource]);
        this.getView().hideGiveOptions();
        if(!typeToGet){
          this.state = selGetState;
          this.state.OnUpdatedModel();
        }else{
          this.state = acceptState;
          this.state.OnUpdatedModel();
          this.getView().enableTradeButton(true);
        }
      },
      OnUpdatedModel: function(controller){
        //console.log("hitting selGiveState.OnUpdatedModel");
        var types = catan.definitions.ResourceTypes;
        if(controller.handleTurn()){
          var canTradeResources = [];
          controller.ratios = {};
          for(var i=0; i<types.length; i++){
            var ratio = controller.canTrade(types[i]);
            if(ratio > 0){
              controller.ratios[types[i]] = ratio;
              canTradeResources.append(types[i]);
            }
          }
          if(canTradeResources.length > 0){
            controller.getView().showGiveOptions(canTradeResources);
          }else{
            controller.getView().hideGiveOptions();
            controller.getView().hideGetOptions();
            controller.getView().setMessage("No trades are possible. Come back later.");
          }
        }
      }
      //leave all other state methods undefined 
    };
    var selGetState = {
      setGetValue : function(resource){
        typeToGet = resource;
        //for some reason this has a port amount parameter as well???
        this.selectGetOption(resource, 1);
        this.state = acceptState;
        this.getView().hideGetOptions();
        this.getView().enableTradeButton(true);
        this.getView().setMessage("Trade?");
      },
      unsetGiveValue: this.unsetGiveValueFromState,
      OnUpdatedModel: function(controller){
        var types = catan.definitions.ResourceTypes;
        if(controller.handleTurn()){
          var canGetResources = [];
          for(var i=0; i<types.length; i++){
            if(controller.game.getModel().getBank()[types[i]] > 0){
              canGetResources.append(types[i]);
            }
          }
          if(canGetResources.length > 0){
            controller.getView().showGiveOptions(canGetResources);
          }else{
            controller.getView().hideGiveOptions();
            controller.getView().hideGetOptions();
            controller.getView().setMessage("The Bank is broke! Someone roll a 7.");
          }
        }
      }
    };
    var acceptState = {
      makeTrade : function(){
        this.game.maritimeTrade(typeToGive, this.ratios[typeToGive], typeToGet);
      },
      unsetGiveValue : this.unsetGiveValueFromState,
      unsetGetValue : function(){
        typeToGet = undefined;
        this.getView().enableTradeButton(false);
        this.state = selGetState;
        this.state.OnUpdatedModel();
      },
      OnUpdatedModel: function(controller){
        this.handleTurn();
        this.getView().hideGiveOptions();
        this.getView().hideGetOptions();
      }
    };

    MaritimeController.prototype.unsetGiveValueFromState = function(){
      typeToGive = undefined;
      this.state = selGiveState;
      this.state.OnUpdatedModel();
      this.getView().enableTradeButton(false);
    };

    MaritimeController.prototype.handleTurn = function(){
      if(!this.game.getModel().isMyTurn()){
        this.getView().hideGiveOptions();
        this.getView().hideGetOptions();
        this.getView().setMessage("It is not your turn");
        return false;
      }
      return true;
    };

    /**
    Private function
    checks a given resource to see if the player can trade it.
    */
    MaritimeController.prototype.canTrade = function(resourceType, has3to1Port){
      var model = this.game.getModel();
      var player = this.game.getCurrentPlayer();
      var playerOrder = this.game.getCurrentPlayerOrder();
      var map = model.getMap();
      var resources = player.getResources();
      if(resources[resourceType] >= 2){
        if(map.canMaritimeTrade(playerOrder, 2 ,resourceType))
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


    MaritimeController.prototype.OnUpdatedModel = function(){
      if(this.state.OnUpdatedModel) {
        this.state.OnUpdatedModel(this);
      }
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
      if(this.state.unsetGetValue)
        this.state.unsetGetValue();
    };
        
    /**
         * Called by the view when the player selects which resource to give
     * @method setGiveValue
     * @param{String} resource The resource to trade ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    MaritimeController.prototype.setGiveValue = function(resource){
      if(this.state.setGiveValue)
        this.state.setGiveValue(resource);
    };
        
    /**
         * Called by the view when the player selects which resource to get
     * @method setGetValue
     * @param{String} resource The resource to trade ("wood","brick","sheep","wheat","ore")
     * @return void
     */
    MaritimeController.prototype.setGetValue = function(resource){
      if(this.state.setGetValue)
        this.state.setGetValue(resource);
    };
        
    /** Called by the view when the player makes the trade
     * @method makeTrade
     * @return void
     */
    MaritimeController.prototype.makeTrade= function(){
      if(this.state.makeTrade)
        this.state.makeTrade();
    };

    return MaritimeController;
  }());

  return MaritimeController;
}());



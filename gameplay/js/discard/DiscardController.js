/**
    This is the namespace for discarding cards
    @module catan.discard
    @namespace discard
*/

var catan = catan || {};
catan.discard = catan.discard || {};

catan.discard.Controller = (function discard_namespace(){

  var Controller = catan.core.BaseController;
    
  var Definitions = catan.definitions;
  var ResourceTypes = catan.definitions.ResourceTypes;
  
    /**
     * @class DiscardController
     * @constructor
     * @extends misc.BaseController
     * @param{discard.DiscardView} view
     * @param{misc.WaitOverlay} waitingView
     * @param{models.Game} game
     */
  var DiscardController = (function DiscardController_Class(){
      
    function DiscardController(view, waitingView, game){
        
      Controller.call(this,view,game);

      view.setController(this);
      
      waitingView.setController(this);
      this.setWaitingView(waitingView);
      this.state = false;
      this.game = game;
      this.view = view;

      this.game.addObserver(this, onUpdatedModel);

    }

    core.forceClassInherit(DiscardController,Controller);

    core.defineProperty(DiscardController.prototype,"waitingView");
    core.defineProperty(DiscardController.prototype,"selected");
    core.defineProperty(DiscardController.prototype,"numToDiscard");
    core.defineProperty(DiscardController.prototype,"state");

    /**
    This is the callback function passed into the game in order to update
    the views with the new model data.
    */
    var onUpdatedModel = function(){
      //check if already displayed
      
      var client = this.game.getModel();
      if(!client.getTurn().isDiscardingPhase()){
        this.view.closeModal();
        this.waitingView.closeModal();
        return;
      }
      if(this.state)
        return;
      this.state = true;
      var curPlayer = this.game.getCurrentPlayer();
      if(curPlayer.hasMoreThan7Cards()){
        var resources = curPlayer.getResources().getResourceArray();
        //catan.definitions
        //ResourceTypes: ["wood","brick","sheep","wheat","ore"]
        var sum = 0;
        for(var i=0; i< 5; i++){
          this.view.setResourceMaxAmount(
            catan.definitions.ResourceTypes[i],resources[i]);
          this.view.setResourceAmount(catan.definitions.ResourceTypes[i], 0);
          this.view.setResourceAmountChangeEnabled(
            catan.definitions.ResourceTypes[i], resources[i] > 0, false);
          sum += resources[i];
        }
        this.numToDiscard = sum / 2;
        this.view.setStateMessage("0/" + this.numToDiscard);
        this.view.setDiscardButtonEnabled(false);
        this.selected = {"wood":0,"brick":0,"wool" :0,"wheat":0,"ore":0};
        this.view.showModal();
      }else{
        this.waitingView.showModal();
      }
    
    };

    /**
     Called by the view when the player clicks the discard button.
         It should send the discard command and allow the game to continue.
     @method discard
     @return void
     */
    DiscardController.prototype.discard = function(){
      var resourceList = new catan.models.ResourceList({});
      resourceList.setResourceListItems(selected.brick, selected.ore,
              selected.sheep, selected.wheat, selected.wood);
      this.getGame().discardCards(resourceList, function(){
        this.state = false;
        this.view.closeModal();
      });
    };
        
    /**
     Called by the view when the player increases the amount to discard for a single resource.
     @method increaseAmount
     @param {String} resource the resource to discard
     @return void
     */
    DiscardController.prototype.increaseAmount = function(resource){
      this.selected[resource]++;
      this.view.setResourceAmount(resource, this.selected[resource]);
      var sum = 0;
      for(var sel in selected){
        sum += selected[sum];
      }
      if(this.numToDiscard <= sum){
        disableAllIncrease();
        this.view.setDiscardButtonEnabled(true);
      }else{
        var decrease = false;
        if(this.selected[resource] > 0){
          decrease = true;
        }
        var increase = canIncrease(resource);
        this.view.setResourceAmountChangeEnabled(resource, increase, decrease);
      }
      this.view.setStateMessage(""+sum+"/" + this.numToDiscard);
    };
        

    /**
     Called by the view when the player decreases the amount to discard for a single resource.
     @method decreaseAmount
     @param {String} resource the resource to discard
     @return void
     */
    DiscardController.prototype.decreaseAmount = function(resource){
      this.view.setDiscardButtonEnabled(true);
      this.selected[resource]--;
      this.view.setResourceAmount(resource, this.selected[resource]);
      var sum = 0;
      for(var sel in selected){
        sum += selected[sum];
      }
      //if was just at maximum, then re-enable all the resources
      if(this.numToDiscard <= sum +1){
        enableAllIncrease();
      }
      var decrease = false;
      if(this.selected[resource] > 0){
        decrease = true;
      }
      var increase = canIncrease();
      this.view.setResourceAmountChangeEnabled(sel, increase, decrease);

      this.view.setStateMessage(""+sum+"/" + this.numToDiscard);

    };

    /**
      Used when you hit the number of cards you need to discard.
        */
    var disableAllIncrease = function(){
      for(var sel in selected){
        var decrease = false;
        if(this.selected[resource] > 0){
          decrease = true;
        }
        this.view.setResourceAmountChangeEnabled(sel, false, decrease);
      }
    };

    var enableAllIncrease = function(){
      for(var sel in selected){
        var decrease = false;
        if(this.selected[resource] > 0){
          decrease = true;
        }
        var increase = canIncrease(resource);
        this.view.setResourceAmountChangeEnabled(sel, increase, decrease);
      }
    };

    /**
    private 
    Used to determine if the function can increase
    @return {boolean} whether or not the resourse can be increased
    */
    var canIncrease = function(resouce){
      var client = this.getGame().getClientModel();
      var playerResources = client.getCurrentPlayer().getResourceArray();
      return playerResources[catan.definitions.ResourceEnum[resource]] > 0 &&
        playerResources[catan.definitions.ResourceEnum[resource]] > this.selected[resource];
    };

    return DiscardController;
  }());
  
    return DiscardController;
}());


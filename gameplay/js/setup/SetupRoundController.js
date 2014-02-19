/**
    This is the namespace for the intitial game round
    @module catan.setup
    @namespace setup
*/

var catan = catan || {};
catan.setup= catan.setup || {};

catan.setup.Controller = (function(){
  
  var Controller = catan.core.BaseController;
    
  /** 
    @class SetupRoundController
    @constructor 
    @extends misc.BaseController
    @param {models.ClientModel} clientModel
    @param {map.MapController} mapController
  */
  var SetupRoundController = (function (){

    
    var SetupRoundController = function (game, mapController){
      
      this.mapController = mapController;
      this.game = game;
      this.state = Waiting;

      Controller.call(this,undefined,game);
      this.game.addObserver(this, this.onUpdate);
   };
        
   core.forceClassInherit(SetupRoundController,Controller);
   core.defineProperty(SetupRoundController.prototype, "state");
  /**
  setup road and setlement in setup phase.
  <pre>
    PRE: Less than two roads and two setlement
    POST: build one more road and one more setlement
  </pre>      
    @return {void}
  */
  SetupRoundController.prototype.setupRound = function(){
   
    var client = this.game.getModel();
    var currentPlayer = client.getPlayerWithId(this.game.getCurrentPlayerId());
    

    if (currentPlayer.getRoads() < 2){
       this.mapController.startMove("road", true, true);
    }

    if(currentPlayer.getSettlements() < 2){
      this.mapController.startMove("settlement", true, true);
    }
  };
  /**
    This is the callback function passed into the game in order to update
    <pre>
    PRE: current player's turn
    PRE: is in setup phase
    PRE: is less then two round
    POST: set up two rounds(eatch round has one road and one setlement)
    POST: forward to Cantan.html if finished setup two rounds. 
    </pre>
    @return {None}
    */
  SetupRoundController.prototype.onUpdate = function(){
    
    var turnTracker = this.game.getModel().getTurn(); 

    if (!turnTracker.isFirstSetup() && !turnTracker.isSecondSetup()){
      window.location = "/catan.html";
      return;
    }

    if(this.state.onUpdateModel)
      this.state.onUpdateModel(this);



    //console.log( turnTracker.getTurnPlayerId() + "=" + this.game.getCurrentPlayerId());
    //check if my turn
    
    
    //if it alread has two rounds, the setup is finished
    
  };
    
    /**
      SetupRound State Classes
      A. c: Checks if is my turn and setupRound, then set state to BuildRoad
      D. BuildSettlement: Initiate build settlement, set state to WaitForSettlement
      E. WaitForSettlement: checks original settlement count against new settlement count, 
                            if different then set to FinishTurn.
      D. BuildRoad: Initiate Build road, set state to WaitForRoad
      E. WaitForRoad: checks original road count against new Road count, if different then set to FinishTurn
      F. FinishTurn: calls this.game.FinishTurn() and sets state to Settlement
    */
    var Waiting = {
      onUpdateModel: function(controller){
        var turnTracker = controller.game.getModel().getTurn();
        if(turnTracker.getTurnPlayerId() != controller.game.getCurrentPlayerId())
          return;
        //console.log("is setupPhase " + turnTracker.isSetupPhase())
        
        //check if setup phase
        if (turnTracker.isFirstSetup() || turnTracker.isSecondSetup()){
          curDisplayed = true;
          controller.setState(BuildSettlement);
          if(controller.getState().execute)
            controller.getState().execute(controller);
          else{
            alert("THIS SHOULD NEVER HAPPEN");
          }
        }else{
          return
        }
      }
    };
    var BuildSettlement = {
      execute: function(controller){

      }
    };
    var WaitForSettlement = {
      onUpdateModel: function(){

      }
    };
    var BuildRoad = {
      execute: function(){

      }
    };
    var WaitForRoad = {
      onUpdateModel: function(){

      }
    };
    var FinishTurn = {
      execute: function(){

      }
    };
    


    return SetupRoundController;
  }());
    
  return SetupRoundController;
}());


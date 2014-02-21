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
   core.defineProperty(SetupRoundController.prototype, "numOfRoads");
   core.defineProperty(SetupRoundController.prototype, "numOfSettlements");
    core.defineProperty(SetupRoundController.prototype, "mapController");
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
    //console.log(turnTracker);
    if (!turnTracker.isFirstSetup() && !turnTracker.isSecondSetup()){
      //console.log("Trying to change pages");
      window.location = "/catan.html";
      return;
    }

    if(this.state.onUpdateModel)
      this.state.onUpdateModel(this);
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
        if (!turnTracker.isFirstSetup() && !turnTracker.isSecondSetup())
           return;

        var client = controller.game.getModel();
        var currentPlayer = client.getPlayerWithId(controller.game.getCurrentPlayerId());
        controller.setState(BuildSettlement);

        if(controller.getState().execute)
          controller.getState().execute(controller);
      }
    };
    var BuildSettlement = {
      execute: function(controller){ 
         controller.mapController.startMove("settlement", true, true);
         controller.setNumOfSettlements(controller.game.getModel().getSettlementCount());
         controller.state = WaitForSettlement;
       }
    };

    var WaitForSettlement = {
      onUpdateModel: function(controller){
        var updatedSettlementCount = controller.game.getModel().getSettlementCount();
        //this is hacky... wish they gave us a better way.
        //checks if the number of settlements has changed before going on.
        if(controller.getNumOfSettlements() > updatedSettlementCount){
          controller.setNumOfSettlements(0);
          controller.setState(BuildRoad);
          if(controller.getState().execute){
            controller.getState().execute(controller);
          }
        }
      }
    };

    var BuildRoad = {
      execute: function(controller){
      controller.mapController.startMove("road", true, true);
	  controller.setNumOfRoads(controller.game.getModel().getRoadCount());
      controller.state = WaitForRoad;
      }
    };
    var WaitForRoad = {
      onUpdateModel: function(controller){
        var updatedRoadCount = controller.game.getModel().getRoadCount();
        if(controller.getNumOfRoads() > updatedRoadCount){
          controller.setNumOfRoads(0);
          controller.setState(FinishTurn);
          if(controller.getState().execute){
            controller.getState().execute(controller);
          }
        }
      }
    };
    var FinishTurn = {
      execute: function(controller){
        controller.game.finishTurn(function(){
          controller.setState(Waiting);
        });
      }
    };
    


    return SetupRoundController;
  }());
    
  return SetupRoundController;
}());


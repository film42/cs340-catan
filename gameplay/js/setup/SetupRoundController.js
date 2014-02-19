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
      this.round = 1;

      Controller.call(this,undefined,game);
      this.game.addObserver(this, this.onUpdate);
   };
        
   core.forceClassInherit(SetupRoundController,Controller);
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
    //keep track of round 
    this.round++;
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

    //console.log( turnTracker.getTurnPlayerId() + "=" + this.game.getCurrentPlayerId());
    //check if my turn
    if(turnTracker.getTurnPlayerId() != this.game.getCurrentPlayerId())
      return;
    
    //console.log("is setupPhase " + turnTracker.isSetupPhase())
    
    //check if setup phase
    //if (!turnTracker.isSetupPhase())
    //  return;
    
    //if it alread has two rounds, the setup is finished
    if (this.round > 2){
      window.location = "/catan.html";
      return;
    }

    this.setupRound();
    
  };
      
    return SetupRoundController;
  }());
    
  return SetupRoundController;
}());


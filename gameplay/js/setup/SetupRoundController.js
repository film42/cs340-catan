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
      this.clientModel = clientModel;
      
       
      Controller.call(this,undefined,game);
      
      var currentPlayerID = game.getCurrentPlayer();
      var client = game.getClientModel();
      var turnTracker = client.getTurn();
      var currentPlayer = client.getPlayerWithId(currentPlayerID);

      if (turnTracer.isSetupPhase){
        if (currentPlayer.getRoads() == 0  || (currentPlayer.getRoads == 1 && currentPlayer.getSettlements() == 1)){
          this.mapController.startMove("road", true, true);
        }
        else if ((currentPlayer.getRoads() == 1 && currentPlayer.getSettlements() == 0) ||
                 (currentPlayer.getRoads() == 2 && currentPlayer.getSettlements() == 1)){
          this.mapController.startMove("settlement", true, true);
        }
    }
    else{
         window.location("catan.html");
       }
   };
        
   core.forceClassInherit(SetupRoundController,Controller);
      
    return SetupRoundController;
  }());
    
  return SetupRoundController;
}());


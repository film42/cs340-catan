/**
The namespace for the turn tracker

@module catan.turntracker
@namespace turntracker
**/

var catan = catan || {};
catan.turntracker = catan.turntracker || {};

catan.turntracker.Controller = (function turntracker_namespace() {

  var Controller = catan.core.BaseController;
    
  /**
    The controller class for the Turn Tracker
    @class TurnTrackerController 
    @extends misc.BaseController
    @param {turntracker.View} view The view for this object to control.
    @param {models.ClientModel} clientModel The clientModel for this object to control.
    @constructor
  **/
  var TurnTrackerController = (function TurnTrackerController_Class(){
  
    function TurnTrackerController(view, clientModel){
      Controller.call(this,view,clientModel);
            
      // TODO: This constructor should configure its view by calling view.setClientColor and view.initializePlayer
      // NOTE: The view.updateViewState and view.updatePlayer will not work if called from here.  Instead, these
      //          methods should be called later each time the client model is updated from the server.

      this.view = view;

      var players = game.getPlayers();

      players.forEach(function(player) {

        var color = player.getColor();
        var id = player.getplayerID();
        var name = player.getName();

        view.initializePlayer(id, name, color);

      });

      game.addObserver(this.onUpdatedModel);
    }

    core.forceClassInherit(TurnTrackerController,Controller);

    TurnTrackerController.prototype.onUpdatedModel = function() {

    };

    /**
     * Called by the view when the local player ends their turn.
     * @method endTurn
     * @return void
     */
    TurnTrackerController.prototype.endTurn = function(){
      game.finishTurn(function() {});
    };
    
    return TurnTrackerController;
  } ());

  return TurnTrackerController;
} ());


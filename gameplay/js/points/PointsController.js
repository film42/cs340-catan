var catan = catan || {};
catan.points = catan.points || {};
catan.points.Controller = catan.points.Controller || {};

/**
    This is the namespace for point display
    @module catan.points
    @namespace points
*/

catan.points.Controller = (function VPController_Class(){

  var Controller = catan.core.BaseController;

  PointController.prototype = core.inherit(Controller.prototype);

  core.defineProperty(PointController.prototype, "GameFinishedView");

  /** 
    @class PointController
    @constructor 
    @extends misc.BaseController
    @param {points.View} view
    @param {misc.GameFinishedView} gameFinishedView
    @param {models.Game} game
  */
  function PointController(view, gameFinishedView, game){
    this.setGameFinishedView(gameFinishedView);
    Controller.call(this,view,game);
    
    gameFinishedView.setController(this);
    var player = game.getCurrentPlayer();
    view.setPoints(player.getPoints());
    if (view.getTotalPoints() > view.MAX_POINTS){
      //display the end-game view
      
    }
    
    console.log("PointController Constructor Called");
  }

  
  
  return PointController;
}());
// STUDENT-REMOVE-END


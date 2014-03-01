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
  var MAX_POINTS = 10;
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
    Controller.call(this,view,game);
    if (!gameFinishedView) // undefined if in setup controller
      return;
    this.setGameFinishedView(gameFinishedView);
    gameFinishedView.setController(this);
    this.game = game;
    this.view = view;
    this.player = game.getCurrentPlayer();
    view.setPoints(this.player.getPoints());

    this.game.addObserver(this, this.OnUpdatedModel);
  }
  
  PointController.prototype.OnUpdatedModel = function(){
    var theresaWinner = false
    var winnerName;
    var isYou;
    var self = this;
    this.view.setPoints(this.game.getCurrentPlayer().getPoints());
    this.game.getModel().getPlayers().forEach(function(p){
      if (p.getPoints() == this.view.MAX_POINTS){
        theresaWinner = true;
        winnerName  = p.getName();
        isYou = winnerName == self.player.getName();
      }
    });
    if (theresaWinner){
      this.getGameFinishedView().setWinner(winnerName, isYou);
      this.getGameFinishedView().showModal();
    }
  };

  
  
  return PointController;
}());
// STUDENT-REMOVE-END


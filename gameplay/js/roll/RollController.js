/**
    This is the namespace the rolling interface
    @module catan.roll
    @namespace roll
*/

var catan = catan || {};
catan.roll = catan.roll || {};

catan.roll.Controller = (function roll_namespace(){

	var Controller = catan.core.BaseController;
    
	/**
		 * @class RollController
		 * @constructor
		 * @extends misc.BaseController
		 * @param{roll.View} view
		 * @param{roll.ResultView} resultView
		 * @param{models.ClientModel} clientModel
		 */

	var RollController = (function RollController_Class(){
		
		core.forceClassInherit(RollController,Controller);
 
		core.defineProperty(RollController.prototype,"rollResultView");
    core.defineProperty(RollController.prototype,"displayFlag");
    core.defineProperty(RollController.prototype,"game");
		
		function RollController(view,resultView, game){
			this.setRollResultView(resultView);
			Controller.call(this,view,game);

      this.game = game;
      this.displayFlag = false;
			this.timerStarted = false;
			this.timerID = null;
      this.game.addObserver(this, this.onUpdate);
     
		};
        
    RollController.prototype.onUpdate = function(){
      //check flag if already displaying
      if(this.displayFlag)
        return;
      //check if my turn
      var turnTracker = this.game.getModel().getTurn();
      console.log("Turn:"+turnTracker.getTurnPlayerId());
      console.log("playerId:" + this.game.getCurrentPlayerId());
      if(turnTracker.getTurnPlayerId() != this.game.getCurrentPlayerId())
        return;
      //check if roll phase
      if(!turnTracker.isRollingPhase())
        return;
      //set flag to true
      this.displayFlag = true;
      //display view
      this.getView().showModal();
      //startTimer
      this.initTimer();
    }
		/**
		 * This is called from the roll result view.  It should close the roll result view and allow the game to continue.
		 * @method closeResult
		 * @return void
		**/
		RollController.prototype.closeResult = function(){
       this.rollResultView.closeModal();
       this.displayFlag = false;
		}
		
		/**
		 * This method generates a dice roll
		 * @method rollDice
		 * @return void
		**/
		RollController.prototype.rollDice = function(){
      //stop timer
      this.StopTimer();
      //calculate result
      var rolledNumber = catan.util.dice.rollDie() + catan.util.dice.rollDie();
      
      //hide view modal
			this.getView().closeModal();
      //set result modal message
      this.rollResultView.setAmount(rolledNumber);
      //show result modal
			this.rollResultView.showModal();
      //send the server request
      this.game.rollDice(rolledNumber, function() {});

		};

    RollController.prototype.initTimer = function(){
     // Set the length of the timer, in seconds
     this.secs = 15;
     this.StopTimer();
     this.StartTimer();
    }

   RollController.prototype.StopTimer = function(){
   
     if(this.timerStarted)
        clearTimeout(this.timerID);
     this.timerStarted = false;
   }


  RollController.prototype.StartTimer = function(){

   if (this.secs==0)
    {
    	 this.rollDice();
    }
    else
    {
      this.getView().changeMessage("Click roll. Auto Rolling in " + this.secs + " seconds");
      self.status = this.secs;
      this.secs = this.secs - 1;
      this.rollInterval = true;
      this.timerID = self.setTimeout(this.StartTimer(), 1000);
    }
}
		
		return RollController;
	}());
	
	return RollController;

}());


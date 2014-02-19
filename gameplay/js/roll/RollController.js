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
    /**
    * This method will begin to roll phase only if current user is in isRollingPhase() 
    * @method onUpdate
    * @return void
    **/
    RollController.prototype.onUpdate = function(){
      //check flag if already displaying
      if(this.displayFlag)
        return;
      //check if my turn
      var turnTracker = this.game.getModel().getTurn();
     // console.log("Turn:"+turnTracker.getTurnPlayerId());
      //console.log("playerId:" + this.game.getCurrentPlayerId());
      if(turnTracker.getTurnPlayerId() != this.game.getCurrentPlayerId())
        return;
      //check if roll phase
      if(!turnTracker.isRollingPhase())
        return;
      //set flag to true
      this.displayFlag = true;
      //display view
      this.getView().showModal();
      
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
      // init timer
      this.initTimer();
      //start roll
      this.startRoll();
      this.timerStarted = true;   
		};

    /**
     * This method is called after finishing dice roll
     * @method finishedRoll
     * @return void
    **/
    RollController.prototype.finishedRoll = function(){
      //stop time
      this.stopTimer();
      this.timeStarted = false;

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
    /**
     * This method set the length of timer to 3 seconds
     * @method initTimer
     * @return void
    **/
    RollController.prototype.initTimer = function(){
     // Set the length of the timer, in seconds
     this.secs = 3;
     this.stopTimer();
    }

   /**
    * This method clear timeout
    * @method stopTimer
    * @return void
    **/
   RollController.prototype.stopTimer = function(){ 
     if(this.timerStarted)
        clearTimeout(this.timerID);
   }

  /**
  * This method automatically start to roll time for 3 seconds 
  * @method startRoll
  * @return void
  **/
  RollController.prototype.startRoll = function(){
      var that = this;
      this.getView().changeMessage("Click roll. Auto Rolling in " + this.secs + " seconds");
     
      this.secs = this.secs - 1;
      
      var rolling = function(){
        that.startRoll();
      }

      if (this.secs==0){
        this.finishedRoll();
      }
      else{  
        this.timerID = window.setTimeout(rolling, 1000);
      } 
  }	
		return RollController;
	}());
	
	return RollController;

}());


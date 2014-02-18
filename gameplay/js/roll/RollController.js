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
		
		function RollController(view,resultView, game){
			this.setRollResultView(resultView);
			Controller.call(this,view,game);

      this.game = game;
      this.view = view;
      this.resultView = resultView;

      this.game.addObserver(this, this.rollDice);

			this.rollInterval = false;
			this.showRollResult = false;
			this.rolledNumber = 0; 
			this.currentView = view;
			this.secs = 3;
			this.timerID = null;
      this.view.showModal();
		};
        
		/**
		 * This is called from the roll result view.  It should close the roll result view and allow the game to continue.
		 * @method closeResult
		 * @return void
		**/
		RollController.prototype.closeResult = function(){
       this.resultView.closeModal();
       this.showRollResult = false;

       var playerId = this.game.getCurrentPlayerId;
 			 this.game.getProxy().rollNumber(playerId, this.rolledNumber, function() {});

       if (this.rolledNumber == 7){
          discardResource();   // other player
          moveRobber();  //roller move robber
          stealResouce(); // roller steal resource
       }
       else{
       	 // continue the game;
       }   
		}
		
		/**
		 * This method generates a dice roll
		 * @method rollDice
		 * @return void
		**/
		RollController.prototype.rollDice = function(){
      
      var turn =  this.game.getModel().getTurn();
      if (!turn.isRollingPhase())
        return;

      var canRoll = this.game.getModel().canRoll();
      if(!canRoll) 
         return;

     if (this.showRollResult == true)
        return;
      
      this.showRollResult = true;

			//this.rollingDice();
			this.view.closeModal();
      this.resultView.setAmount(this.rolledNumber);
			this.resultView.showModal();

		};

    RollController.prototype.rollingDice = function(){
    
     // Set the length of the timer, in seconds
     this.secs = 3;
     this.StopTimer();
     this.StartTimer();
    }

   RollController.prototype.StopTimer = function(){
   
     if(this.rollInterval)
        clearTimeout(this.timerID);
     this.rollInterval = false;
   }


  RollController.prototype.StartTimer = function(){

   if (this.secs==0)
    {
    	 this.rolledNumber = catan.util.dice.rollDie() + catan.util.dice.rollDie();
       this.StopTimer();
    }
    else
    {
        self.status = this.secs;
        this.secs = this.secs - 1;
        this.rollInterval = true;
        this.timerID = self.setTimeout(this.StartTimer(), 1000);
    }
}

		/**
			if rooling 7 and activating the robber, each player with more than 7 resource cards, must 
			choose to and discard half of them.
    */
		var discardResource = function(){
			var discardView = new catan.discard.View();
			this.currentView = discardView;
			discardView.showModal();	
		};

   /**
		 Move the robber to the number token of any other terrain hex( or to the desert hex). 
    */
		var moveRobber = function(){
      this.currentView.closeModal();
			var robView = new catan.views.overlays.RobOverlay();
			this.currentView = robView();
			robView.showMode();
			
		};

   /**
		 steal 1 resource card at the random from a player who has settlement or city adjacent to this new hex. 
    */
		var stealresource = function(){

			
		};
		
		return RollController;
	}());
	
	return RollController;

}());


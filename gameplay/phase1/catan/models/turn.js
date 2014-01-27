var catan = catan || {};
catan.models = catan.models || {};

catan.models.Turn = (function() {
/**
        The model class keeps current turn state and its player's ID
        <pre>      
        Domain:
            currentTurn: Player's id, Number
            status:  Status, Stirng
         
        Constructor Specification:
            PRE: !isNaN(playerID)
            PRE: !isNaN(playStatus)
            POST: getPlayer() == playerID
            POST: getStatus() == status
        </pre>

        @class catan.models.Turn
        @constructor
        
        @param {Number} playerID 
        @param {String} Status
    */
  function Turn(playerID, status) {
		this.setCurrentTurn(playerID);
		this.setStatus(status);

  }
  
  /**
		Sets current turn to the player
	    <pre>
        PRE: !isNaN(playID)
        </pre>
         
        @method setCurrentTurn
        @param {Number} playerID The player's ID
	*/
	
	Turn.prototype.setCurrentTurn = function(playerID) {
		this.currentTurn = playerID;
	};
	
	 /**
        Returns current player's ID
		<pre>
        PRE: None
        </pre>
         
        @method getCurrentTurn
        @return {Number} player's ID    
    */
	Turn.prototype.getCurrentTurn = function() {
      return this.currentTurn;
	};

	/**
		Sets the playing state
		<pre>
        PRE: !isNaN(status)
        </pre>
         
        @method setStatus
        @param {String} Status The playing status
    */
	
	Turn.prototype.setStatus = function(status) {
		this.status = status;
	};
	
	 /**
        Returns the playing status
        <pre>
        PRE: None
        </pre>
         
        @method getStatus
        @return {String } status
    */
    Turn.prototype.getStatus = function() {
      return this.status;
	};
	
	 /**
        Returns if a player is currently playing
        <pre>
        PRE: !isNaN(playerID)
        </pre>
         
        @method isPlayingPhase
        @return {boolean} true or false
    */
    Turn.prototype.isPlayingState() = function(playID) {
      
	};
	
	
	

  return Turn;
})();
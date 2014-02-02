var catan = catan || {};
catan.models = catan.models || {};

catan.models.Turn = (function() {
/**
  The model class keeps current turn of player's playing phase and its player's ID
  <pre>      
  Domain:

  var phases =
  {
 
    Playing: "playing",
    Rolling: "rolling",
    Discarding: "discarding",
    Robbing: "robbing",
 
  };

  playerID: current player's id, Number
  phase:  phase, Stirng
         
  Constructor Specification:
  PRE: !isNaN(playerID)
  PRE: !isNaN(phase) 
  POST: getPlayer() == playerID
  POST: getPlayingPhase() == phase
  </pre>

  @class catan.models.Turn
  @constructor
        
  @param {Number} playerID 
  @param {String} phase
*/

  var phases =
  {
 
    Playing: "playing",
    Rolling: "rolling",
    Discarding: "discarding",
    Robbing: "robbing",
 
  };

  function Turn(playerID, phase) {
    this.playerID = setCurrentTurn(playerID);
    this.phase = setPhase(phase);

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
    this.playerID = playerID;
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
    return this.playerID;
  };

  /**
  Sets the phase of current turn
  <pre>
  PRE: !isNaN(phase)
  </pre>
         
  @method setPhase
  @param {String} phase The playing phase
  */
	
  Turn.prototype.setPhase = function(phase) {
    this.phase == phases.toLowerCase();
  };
	
  /**
    Returns current turn phase
    <pre>
    PRE: None
    </pre>
         
    @method getphase
    @return {String } phase
  */
  Turn.prototype.getphase = function() {
    return this.phase;
  };
	
  /**
    Returns ture if the phase is playing phase otherwise, false.

    @method isPlayingPhase
    @return {boolean} true or false
  */
  Turn.prototype.isPlayingPhase() = function() {
    if (this.phase == phases.Playing )
      return true;
    else
      return false;
  };

  /**
    Returns ture if the phase is rolling phase otherwise, false.
         
    @method isRollingPhase
    @return {boolean} true or false
  */
  Turn.prototype.isRollingPhase() = function() {
    if (this.phase == phases.Rolling )
      return true;
    else
      return false;
  };
	
  /**
    Returns ture if the phase is discard Phase otherwise, false.
         
    @method isDiscardingPhase
    @return {boolean} true or false
  */
  Turn.prototype.isDiscardingPhase() = function() {
    if (this.phase == phases.Discarding )
      return true;
    else
      return false;
      
  };
  
  /**
    Returns ture if the phase is robbing Phase otherwise, false.
         
    @method isRobbingPhase
    @return {boolean} true or false
  */
  Turn.prototype.isRobbingPhase() = function() {
    if (this.phase == phases.Robbing )
      return true;
    else
      return false;
      
  };

  return Turn;
})();
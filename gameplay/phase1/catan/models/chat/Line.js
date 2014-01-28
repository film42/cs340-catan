var catan = catan || {};
catan.models = catan.models || {};
catan.models.chat = catan.models.chat || {};

catan.models.chat.Line = (function() {

    /**
        The model class for the message line of Chad  
        <pre>      
        Domain:
			message: Chat message, String
			source: The player name attached to chat, string
        
        Constructor Specification:
            PRE: !isNaN(messasge)
            PRE: !isNaN(name)
            POST: getMessage() == message
            POST: getSource() == name
        </pre>

        @class catan.models.chat.Line
        @constructor
        
        @param {String} message The chat message 
        @param {String} source The player name attached to message
	*/
	
	function Line(message, name) {
		this.setMessasge(message);
		this.setSource(name);

	}

	/**
		Sets message
	    <pre>
        PRE: !isNaN(message)
        </pre>
         
        @method setMessage
        @param {String} message The chat message
    */
	
	ChatMessage.prototype.setMessage = function(message) {
		this.message = message;
	};
	
	 /**
        Returns the message 
        <pre>
        PRE: None
        </pre>
         
        @method getMessage
        @return {String } The chat message   
    */
	ChatMessage.prototype.getMessage = function() {
      return this.message;
	};

	/**
		Sets the source, the player's name
	    <pre>
        PRE: !isNaN(name)
        </pre>
         
        @method setsource
        @param {String} name The player's name attached to the message
    */
	
	ChatMessage.prototype.setSource = function(name) {
		this.source = name;
	};
	
	 /**
        Returns the source, the player's name  
        <pre>
        PRE: None
        </pre>
         
        @method getSource
        @return {String } The player's name   
    */
	ChatMessage.prototype.getSource = function() {
      return this.source;
	};
	
  return ChatMessage;
})();
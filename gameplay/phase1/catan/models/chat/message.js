var catan = catan || {};
catan.models = catan.models || {};
catan.models.chat = catan.models.chat || {};

catan.models.chat.Message = (function() {

  /**
    The model class for the message Message of Chad  
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

    @class Message
    @constructor
    
    @param {String} message The chat message 
    @param {String} source The player name attached to message
  */
  function Message(message, name) {
    this.setMessasge(message);
    this.setSource(name);
  }
  
  /**
    Returns the message 
    <pre>
    PRE: None
    </pre>
     
    @method getMessage
    @return {String } The chat message   
  */
  Message.prototype.getMessage = function() {
    return this.message;
  };

  
  /**
    Returns the source, the player's name  
    <pre>
    PRE: None
    </pre>
     
    @method getSource
    @return {String } The player's name   
  */
  Message.prototype.getSource = function() {
    return this.source;
  };
  
  return Message;
})();
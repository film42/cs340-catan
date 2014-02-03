var catan = catan || {};
catan.models = catan.models || {};
catan.models.chat = catan.models.chat || {};

catan.models.chat.Message = (function() {

  /**
    The model class for the message Message of Chad  
    <pre>      
    Domain:

      source: The player name attached to chat, string
      message: Chat message, String
      
    Constructor Specification:
        PRE: !isNaN(messasge)
        PRE: !isNaN(source)
        POST: getMessage() == message
        POST: getSource() == source
    </pre>

    @class Message
    @constructor
    
    @param {String} message The chat message 
    @param {String} source The player name attached to message
  */
  function Message(source,message) {
    this.source(source);
    this.messasge(message);  
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
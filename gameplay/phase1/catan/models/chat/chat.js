var catan = catan || {};
catan.models = catan.models || {};
catan.models.chat = catan.models.chat || {};

catan.models.chat.Chat = (function() {

  /**
    The model class for Chat 
   
    @class catan.models.chat.Chat
    @constructor
    
    @param {array} messages A list of objects with source(the player name attached to the event), message(the contents of the line)
  */
  function Chat(messages) {
    this.messages = messages;
  }
  
  /**
    Returns the array of messages 
    <pre>
    PRE: None
    </pre>
     
    @method getmessages
    @return {array} The array of message objects   
  */
  Chat.prototype.getMessages = function() {
    return this.messages;
  };
  
  /**
    Add a message object to array of messages
    <pre>
      PRE: !isNaN()
    </pre>

    @method addLine
    @param {object} message object which contain message and source 
  */
  Chat.prototype.addMessage = function (message) {};
    
  /**
    Returns the message object for given source 
    <pre>
      PRE: !isNaN(source)
    </pre>
       
    @method getMessage
    @return {object } message object which contain message and source for given source 
  */
  Chat.prototype.getMessage = function (source) {
    return null;
  };

  return Chat;
})();


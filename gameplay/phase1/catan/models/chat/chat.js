var catan = catan || {};
catan.models = catan.models || {};
catan.models.chat = catan.models.chat || {};

catan.models.chat.Chat = (function() {

  /**
    The model class for Chat 
   
    @class catan.models.chat.Chat
    @constructor
    
    @param {array} lines A list of objects with source(the player name attached to the event), message(the contents of the line)
  */
  function Chat(lines) {
    this.lines = lines;
  }
  
  /**
    Returns the array of lines 
    <pre>
    PRE: None
    </pre>
     
    @method getLines
    @return {array} The array of line objects   
  */
  Chat.prototype.getLines = function() {
    return this.lines;
  };
  
  /**
    Add a line object to array of lines
    <pre>
      PRE: !isNaN()
    </pre>

    @method addLine
    @param {object} line object which contain message and source 
  */
  Chat.prototype.addLine= function (line) {};
    
  /**
    Returns the line object for given source 
    <pre>
      PRE: !isNaN(source)
    </pre>
       
    @method getLine
    @return {object } line object which contain message and source for given source 
  */
  Chat.prototype.getLine = function (source) {
    return null;
  };

  return Chat;
})();


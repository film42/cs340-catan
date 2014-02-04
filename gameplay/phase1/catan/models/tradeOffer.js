var catan = catan || {};
catan.models = catan.models || {};

catan.models.TradeOffer = (function() {

  var senderID;//int
  var receiverID;//int
  var resources
  /**
  @author Steve Allred
  A trade offer is a container object that contains values specific to a trade offer.
  Domain: 
    JSON: This should contain the initial values of our resources.
    
  Read-only:
    The player object on the client side is read-only.  The server will update the client side model with a new version.
    
  Constructor Specification:
    PRE: the JSON provides values for each of the resources.  All other data is either calculated or generated.
    
    POST: The constructor creates an object that has all of the following fields initialized:
      -sender: integer
      -reciever: integer
      -offer: resourceList

    
    @class DevCards
    @constructor
    @param {JSON} the data containing the initialized objects
*/
  function TradeOffer(json) {
    senderID = json.senderID;
    receiverID = json.receiverID;
    resources = json.resources;
  }

  /**
  Gets the Sender from this object.

    @class Resources
    @return An integer that represents the Sender id
  */
Resources.prototype.getSender = function() {
    return this.senderID;
  };
  /**
  Gets the Sender from this object.

    @class Resources
    @return An integer that represents the Receiver id
  */
Resources.prototype.getReceiver = function() {
    return this.receiverID;
  };
  /**
Gets the offer (a resource list) from this object.

    @class Resources
    @return A resource list
  */
Resources.prototype.getOffer = function() {
    return this.resources;
  };

  return TradeOffer;
})();
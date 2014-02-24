var catan = catan || {};
catan.models = catan.models || {};

catan.models.TradeOffer = (function() {

  var senderID;//int
  var receiverID;//int
  var resources;
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
    senderID = json.sender;
    receiverID = json.receiver;
    resources = new catan.models.ResourceList(json.offer);
  }

  /**
  Gets the Sender from this object.

    @class Resources
    @return An integer that represents the Sender id
  */
  TradeOffer.prototype.getSender = function() {
    return this.senderID;
  };
  /**
  Gets the Sender from this object.

    @class Resources
    @return An integer that represents the Receiver id
  */
  TradeOffer.prototype.getReceiver = function() {
    return this.receiverID;
  };
  /**
  Gets the offer (a resource list) from this object.

    @class Resources
    @return A resource list
  */
  TradeOffer.prototype.getOffer = function() {
    return this.resources;
  };

  TradeOffer.prototype.getCardsOffered = function() {
    var brick = 0;
    var sheep = 0;
    var wheat = 0;
    var ore   = 0;
    var wood  = 0;
    
    if(this.resources.getBrick() > 0)
      brick = this.resources.getBrick();
    
    if(this.resources.getWood() > 0)
      wood = this.resources.getWood();
    
    if(this.resources.getSheep() > 0)
      sheep = this.resources.getSheep();
    
    if(this.resources.getOre() > 0)
      ore = this.resources.getOre();
    
    if(this.resources.getWheat() > 0)
      wheat = this.resources.getWheat();
  
    var ret = new catan.models.ResourceList({});
    ret.setResourceListItems(brick, ore, sheep, wheat, wood);
    return ret;
  };
  
  TradeOffer.prototype.getCardsAskedFor = function() {
    var brick = 0;
    var sheep = 0;
    var wheat = 0;
    var ore = 0;
    var wood = 0;
    
    if(this.resources.getBrick() < 0)
      brick = -this.resources.getBrick();
    
    if(this.resources.getWood() < 0)
      wood = -this.resources.getWood();
    
    if(this.resources.getSheep() < 0)
      sheep = -this.resources.getSheep();
    
    if(this.resources.getOre() < 0)
      ore = -this.resources.getOre();
    
    if(this.resources.getWheat() < 0)
      wheat = -this.resources.getWheat();
  
    var ret = new catan.models.ResourceList({});
    ret.setResourceListItems(brick, ore, sheep, wheat, wood);
    return ret;
  };
  
  
  return TradeOffer;
})();
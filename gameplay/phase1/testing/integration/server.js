test( "get model test", function() {
  ok( 1 == "1", "Passed!" );
});

test("catan.proxy.ClientProxy.getModel", function() {
  stop();
  var proxy = new catan.proxy.ClientProxy();

  proxy.getModel(function(err, data) {
    equal(null, err, "There should not be an error");
    notEqual(null, data, "The data should not be null");
    // Attrubtes
    notEqual(null, data.deck, "There is a deck");
    notEqual(null, data.map, "There is a map");
    notEqual(null, data.players, "There are players");
    notEqual(null, data.log, "There is a log");
    notEqual(null, data.chat, "There is a chat");
    notEqual(null, data.bank, "There is a bank");
    notEqual(null, data.turnTracker, "There is a turn tracker");
    notEqual(null, data.biggestArmy, "There is a biggestArmy");
    notEqual(null, data.longestRoad, "There is a longestRoad");
    notEqual(null, data.winner, "There is a winner");
    start();
  });
});
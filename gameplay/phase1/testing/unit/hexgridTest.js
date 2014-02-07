//This file is for testing the hexgrid class and the underlying classes.
var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

test("vertex.getValue", function(){
  var vertex = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[][].vertexes[5]);
  var value = vertex.getValue();
  ok(value.getBuildSite() == 1 && value.getOwnerID() == 2, "Occupied by Blue(ownerID = 2) Settlement");
});

test("vertex.isOccupied", function(){
  var vertex = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[][].vertexes[5]);
  ok(vertex.isOccupied(), "Occupied by a player 2");
});

test("edge.getValue", function(){

});

test("edge.isOccupied", function(){

});

test("hex.getLocation", function(){

});

test("hex.getEdge", function(){

});

test("hex.getLocationNum", function(){

});

test("hex.getEdgeNum", function(){

});

test("hexgrid.getHex", function(){

});

test("hexgrid.getVertex", function(){

});

test("hexgrid.getEdge", function(){

});

test("hexgrid.getEdgesFromVertex", function(){

});

test("hexgrid.getVertexesFromEdge", function(){

});

test("hexgrid.getAdjEdges", function(){

});

test("hexgrid.getAdjVertexes", function(){

});
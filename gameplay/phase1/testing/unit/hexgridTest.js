//This file is for testing the hexgrid class and the underlying classes.
var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

test("vertex.getValue", function(){
  var vertex = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].vertexes[5]);
  var value = vertex.getValue();
  ok(value.getBuildSite() == 1 && value.getOwnerID() == 2, "Occupied by Blue(ownerID = 2) Settlement(worth = 1)");
});

test("vertex.isOccupied", function(){
  var vertex = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].vertexes[5]);
  ok(vertex.isOccupied(), "True: Occupied by a player 2");
  var vertex2 = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].vertexes[0]);
  ok(!vertex2.isOccupied(), "False: unoccupied");
});

test("edge.getValue", function(){
  var edge = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].edges[4]);
  var value = edge.getValue();
  ok(value.getOwnerID() == 2, "Occupied by Blue(ownerID = 2) road");
});

test("edge.isOccupied", function(){
  var edge = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].edges[4]);
  ok(edge.isOccupied(), "True: Occupied by a player 2");
  var edge2 = new catan.models.map.Vertex(modelJson.map.hexGrid.hexes[3][3].edges[0]);
  ok(!edge2.isOccupied(), "False: unoccupied");
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
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
  var hex = new catan.models.map.Hex(modelJson.map.hexGrid.hexes[3][3]);
  var location = hex.getLocation();
  ok(location.getX() == 0 && location.getY() == 0, "[3][3] in Json corresponds to x:0 y:0 on actual map");
});

test("hex.getEdge", function(){
  var hex = new catan.models.map.Hex(modelJson.map.hexGrid.hexes[3][3]);
  var edge = hex.getEdge("S");
  ok(edge.getValue().getOwnerID() == 2, "Got south edge that belongs to player 2  at (0,0)");
 
});

test("hex.getEdgeNum", function(){
  var hex = new catan.models.map.Hex(modelJson.map.hexGrid.hexes[3][3]);
  var edgenum = hex.getEdgeNum(4);
  ok(edgenum.getValue().getOwnerID() == 2, "Got 4th(=South) edge that belong to player 2  at (0,0)")
});

test("hex.getVertex", function(){
  var hex = new catan.models.map.Hex(modelJson.map.hexGrid.hexes[3][3]);
  var vertex = hex.getVertex("SW");
  ok(vertex.getValue().getOwnerID() == 2, "Got southwest vertex that belongs to player 2  at (0,0)");
 
});

test("hex.getVertexNum", function(){
  var hex = new catan.models.map.Hex(modelJson.map.hexGrid.hexes[3][3]);
  var vertexnum = hex.getVertexNum(5);
  ok(vertexnum.getValue().getOwnerID() == 2, "Got 5th(=Southwest) vertex that belong to player 2 at (0,0)")
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
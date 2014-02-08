test("clientModel catan.models.ClientModel", function(){
  
  var client = new catan.models.ClientModel(0, clientModelCanTestModel);
  var resourceList = catan.models.ResourceList(clientModelCanTestModel.players[0].resources);
  ok(client.canDiscardCard(resourceList), "canDiscardCard() return true correctly ");

  var client1 = new catan.models.ClientModel(1, clientModelCanTestModel);
  var resourceList1 = catan.models.ResourceList(clientModelCanTestModel.players[1].resources);
  ok(client1.canDiscardCard(resourceList1), "canDiscardCard() return true correctly ");
      
 });
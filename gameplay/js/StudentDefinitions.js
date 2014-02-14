/**
 * Definitions for object names to pass between controllers and views.
 * you reference these by calling catan.definitions.ResourceTypes and the like.
 */
var catan = catan||{};
catan.definitions = {
	//resources
	ResourceTypes: ["wood","brick","sheep","wheat","ore"],
	ResourceEnum: ["wood"=0,"brick"=1,"wool" =2,"wheat"=3,"ore"=4],
	
	HexTypes: ["desert","water"],//and each of the resourceTypes
	WATER_HEX: "water",
	DESERT_HEX: "desert",

	//buyables
	ROAD: "Roads",
	SETTLEMENT: "Settlements",
	CITY: "Cities",
	BUY_CARD: "BuyCard",
	PLAY_CARD: "DevCards",
	ARMY: "Soldiers",
	
	BuyableTypes: ["Roads","Settlements","Cities", "BuyCard","DevCards","Soldiers"],

	//development cards
	SOLDIER: "soldier",
	YEAR_OF_PLENTY: "yearOfPlenty",
	MONOPOLY: "monopoly",
	ROAD_BUILD: "roadBuilding",
	MONUMENT: "monument",
	
	CardTypes: ["soldier","yearOfPlenty","monopoly","roadBuilding", "monument"],
}


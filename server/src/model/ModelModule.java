package model;

import model.base.*;
import model.map.EdgeImpl;
import model.map.EdgeValueImpl;
import model.map.HexGridImpl;
import model.map.HexImpl;
import model.map.LocationImpl;
import model.map.MapImpl;
import model.map.RobberImpl;
import model.map.VertexImpl;
import model.map.VertexValueImpl;
import model.messaging.ChatImpl;
import model.messaging.LineImpl;
import model.messaging.LogImpl;
import model.users.UserImpl;
import modelInterfaces.base.*;
import modelInterfaces.map.Edge;
import modelInterfaces.map.EdgeValue;
import modelInterfaces.map.Hex;
import modelInterfaces.map.HexGrid;
import modelInterfaces.map.Location;
import modelInterfaces.map.Map;
import modelInterfaces.map.Robber;
import modelInterfaces.map.Vertex;
import modelInterfaces.map.VertexValue;
import modelInterfaces.messaging.Chat;
import modelInterfaces.messaging.Line;
import modelInterfaces.messaging.Log;
import modelInterfaces.users.User;

import com.google.inject.AbstractModule;


public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(User.class).to(UserImpl.class);
        bind(Game.class).to(GameImpl.class);
        bind(GameInfo.class).to(GameInfoImpl.class);
        bind(Player.class).to(PlayerImpl.class);
        bind(Resources.class).to(ResourcesImpl.class);
        bind(TurnTracker.class).to(TurnTrackerImpl.class);
        bind(Chat.class).to(ChatImpl.class);
        bind(Line.class).to(LineImpl.class);
        bind(Log.class).to(LogImpl.class);
        bind(User.class).to(UserImpl.class);
        bind(Deck.class).to(DeckImpl.class);
		bind(TradeOffer.class).to(TradeOfferImpl.class);
    }

}

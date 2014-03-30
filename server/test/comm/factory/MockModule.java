package comm.factory;

import model.base.*;
import model.messaging.ChatImpl;
import model.messaging.LineImpl;
import model.messaging.LogImpl;
import model.users.UserImpl;
import modelInterfaces.base.*;
import modelInterfaces.messaging.Chat;
import modelInterfaces.messaging.Line;
import modelInterfaces.messaging.Log;
import modelInterfaces.users.User;

import com.google.inject.AbstractModule;


public class MockModule extends AbstractModule {

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
		bind(TradeOffer.class).to(TradeOfferImpl.class);

		// Here's dependency injection!
		bind(Deck.class).to(DeckMock.class);
    }

}
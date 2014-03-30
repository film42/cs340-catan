package model.base;

import modelInterfaces.base.Deck;

public class DeckMock extends DeckImpl {

	/*
	 * Always grabs a monument card
	 */
	@Override
	public String getDevCard() {
		monument--;
		return Deck.MONUMENT;
	}

}


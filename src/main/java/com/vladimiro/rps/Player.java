package com.vladimiro.rps;

public interface Player {

	Symbol askForSymbol();

	void notifyOpponentSymbol(Symbol symbol);

}

package com.vladimiro.rps;

public class HumanPlayer implements Player {
	
	private final SymbolSource source;

	public HumanPlayer(SymbolSource source){
		this.source = source;
	}

	@Override
	public Symbol askForSymbol() {
		return source.readSymbol();
	}

	@Override
	public void notifyOpponentSymbol(Symbol symbol) {
		//do nothing
	}

}

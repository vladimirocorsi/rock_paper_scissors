package com.vladimiro.rps.core;

import static java.util.Objects.requireNonNull;

public class Game {
	
	private final SymbolComparator comparator;
	private final Player player1;
	private final Player player2;
	private int score1;
	private int score2;
	
	public Game(Player p1, Player p2){
		this.player1 = requireNonNull(p1);
		this.player2 = requireNonNull(p2);
		comparator = new SymbolComparator();
	}
	
	public Symbol[] play(){
		//NOTE: if planning to refactor for adding more than two players, consider implementing the following lines
		//(asking players for symbols) via asynchronous calls (e.g. Java Futures in executor).
		final Symbol symbol1 = player1.askForSymbol();
		final Symbol symbol2 = player2.askForSymbol();
		if(comparator.compare(symbol1, symbol2) > 0){
			score1++;
		}
		else if(comparator.compare(symbol1, symbol2) < 0){
			score2++;
		}
		else{
			//tie
			score1++;
			score2++;
		}
		
		//notify symbol
		player1.notifyOpponentSymbol(symbol2);
		player2.notifyOpponentSymbol(symbol1);
		return new Symbol[]{symbol1, symbol2};
	}

	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}
}

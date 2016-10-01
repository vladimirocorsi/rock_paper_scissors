package com.vladimiro.rps.core;

/**
 * A strategy for generating symbols for an {@link ComputerPlayer}.
 * It may be fed with symbols previously played by the opponent.
 * These symbols may be used for influencing the generation strategy.
 * 
 * @author vcorsi
 *
 */
public interface ComputerStrategy {

	/**
	 * @return the symbol to be played.
	 */
	Symbol generateSymbol();

	/**
	 * Input a symbol which has been previously played by the opponent.
	 * 
	 * @param symbol
	 */
	void addOpponentSymbol(Symbol symbol);

}

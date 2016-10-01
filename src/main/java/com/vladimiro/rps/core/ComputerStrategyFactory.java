package com.vladimiro.rps.core;

/**
 * Factory for creating {@link ComputerStrategy} to generate {@link Symbol}.
 * 
 * @author vladimiro
 *
 */
public class ComputerStrategyFactory {
	
	private ComputerStrategyFactory(){}

	public static ComputerStrategy randomStrategy() {
		return new RandomStrategy();
	}
	
}

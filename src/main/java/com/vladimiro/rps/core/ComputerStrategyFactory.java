package com.vladimiro.rps.core;

public class ComputerStrategyFactory {
	private ComputerStrategyFactory(){}

	public static ComputerStrategy randomStrategy() {
		return new RandomStrategy();
	}
}

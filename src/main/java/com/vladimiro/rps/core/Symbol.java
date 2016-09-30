package com.vladimiro.rps.core;

/**
 * A symbol, or weapon, in the Rock, Paper, Scissors game.
 * 
 * @author vcorsi
 *
 */
public enum Symbol {
	
	ROCK("R", "Rock"), PAPER("P", "Paper"), SCISSORS("S", "Scissors");

	private final String shortName;
	private final String label;

	private Symbol(String shortName, String label){
		this.shortName = shortName;
		this.label = label;
	}
	
	public String getShort() {
		return shortName;
	}

	public String getLabel() {
		return label;
	}

}

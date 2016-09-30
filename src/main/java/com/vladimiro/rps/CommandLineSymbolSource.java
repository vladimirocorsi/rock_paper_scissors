package com.vladimiro.rps;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineSymbolSource implements SymbolSource {

	private final List<Symbol> symbols = Arrays.asList(Symbol.values());

	@Override
	public Symbol readSymbol() {
		System.out.println("Choose a symbol to play:");
		for(Symbol s : symbols){
			System.out.println(s.getShort() + " for " + s.getLabel());
		}
		String symbol = null;
		final Scanner scanner = new Scanner(System.in);
		boolean equals = symbols.stream().map(s -> s.getShort()).equals(symbol);
		while(equals){
			
		}
		return null;
	}

}

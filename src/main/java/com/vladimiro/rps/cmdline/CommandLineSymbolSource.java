package com.vladimiro.rps.cmdline;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vladimiro.rps.core.Symbol;
import com.vladimiro.rps.core.SymbolSource;

/**
 * A {@link SymbolSource} which uses system input and output to get symbols
 * 
 * @author vcorsi
 *
 */
class CommandLineSymbolSource implements SymbolSource {

	private final List<Symbol> symbols = Arrays.asList(Symbol.values());

	@Override
	public Symbol readSymbol() {
		Symbol symbol = null;
		final Scanner scanner = new Scanner(System.in);
		while (symbol == null) {
			printQuestion();
			final String inputSymbol = scanner.nextLine();
			final Optional<Symbol> foundSymbol = symbols.stream().filter(s -> {
				return s.getShort().equalsIgnoreCase(inputSymbol);
			}).findFirst();
			symbol = foundSymbol.orElse(null);
		}
		return symbol;
	}

	private void printQuestion() {
		System.out.println("Choose a symbol to play:");
		for (Symbol s : symbols) {
			System.out.println(s.getShort() + " for " + s.getLabel());
		}
	}

}

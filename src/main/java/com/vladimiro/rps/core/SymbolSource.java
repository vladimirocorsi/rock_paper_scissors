package com.vladimiro.rps.core;

/**
 * Used by a {@link HumanPlayer} to obtain symbols from I/O system.
 * 
 * @author vcorsi
 *
 */
public interface SymbolSource {

	Symbol readSymbol();

}

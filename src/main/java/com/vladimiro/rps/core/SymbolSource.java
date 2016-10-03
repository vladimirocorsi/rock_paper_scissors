package com.vladimiro.rps.core;

/**
 * Used by a {@link HumanPlayer} to obtain symbols from I/O system.
 * 
 * @author vcorsi
 *
 */
@FunctionalInterface
public interface SymbolSource {

  Symbol read();

}

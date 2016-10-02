package com.vladimiro.rps.core;

import static java.util.Objects.requireNonNull;

/**
 * A {@link Player} who takes symbols from an I/O system masked by a {@link SymbolSource}.
 * 
 * @author vcorsi
 *
 */
public class HumanPlayer implements Player {

  private final SymbolSource source;

  public HumanPlayer(SymbolSource source) {
    this.source = requireNonNull(source);
  }

  @Override
  public Symbol askForSymbol() {
    return source.readSymbol();
  }

  @Override
  public void notifyOpponentSymbol(Symbol symbol) {
    // do nothing
  }

  @Override
  public String getLabel() {
    return "Human";
  }

}

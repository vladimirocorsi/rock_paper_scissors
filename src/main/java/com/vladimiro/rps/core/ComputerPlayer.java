package com.vladimiro.rps.core;

import static java.util.Objects.requireNonNull;

/**
 * A {@link Player} who generates symbols automatically based on a {@link ComputerStrategy}.
 * 
 * @author vcorsi
 *
 */
public class ComputerPlayer implements Player {

  private final ComputerStrategy strategy;
  private final String label;

  public ComputerPlayer(ComputerStrategy strategy, String name) {
    this.strategy = requireNonNull(strategy);
    this.label = name;
  }

  @Override
  public Symbol askForSymbol() {
    return strategy.generateSymbol();
  }

  @Override
  public void notifyOpponentSymbol(Symbol symbol) {
    strategy.addOpponentSymbol(symbol);
  }

  @Override
  public String getLabel() {
    return label;
  }

}

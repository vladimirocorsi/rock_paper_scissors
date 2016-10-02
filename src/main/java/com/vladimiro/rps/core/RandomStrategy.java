package com.vladimiro.rps.core;

import java.util.Random;

/**
 * A {@link ComputerStrategy} which provides random symbols.
 * 
 * @author vcorsi
 *
 */
class RandomStrategy implements ComputerStrategy {

  private final Random random;
  private final Symbol[] symbols;

  public RandomStrategy() {
    this.random = new Random();
    this.symbols = Symbol.values();
  }

  @Override
  public Symbol generateSymbol() {
    return symbols[random.nextInt(symbols.length)];
  }

  @Override
  public void addOpponentSymbol(Symbol symbol) {
    // do nothing
  }

}

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

  private Symbol(String shortName, String label) {
    this.shortName = shortName;
    this.label = label;
  }

  public String getShort() {
    return shortName;
  }

  public String getLabel() {
    return label;
  }

  /**
   * Defines the order (wins > loses) between all the couples of symbols.
   * 
   * @param opponent
   * @return 1,0,-1 if this symbol wins over, ties or defeats opponent.
   */
  public int fight(Symbol opponent) {
    if (this == opponent) {
      return 0;
    }

    if (this == SCISSORS) {
      // scissors wins
      if (opponent == PAPER) {
        return 1;
      }
      // rock wins
      if (opponent == ROCK) {
        return -1;
      }
      throw new IllegalArgumentException();
    }

    if (this == PAPER) {
      // paper wins
      if (opponent == ROCK) {
        return 1;
      }
      // scissors wins
      if (opponent == SCISSORS) {
        return -1;
      }

      throw new IllegalArgumentException();
    }

    if (this == ROCK) {
      // rock wins
      if (opponent == SCISSORS) {
        return 1;
      }
      // paper wins
      if (opponent == PAPER) {
        return -1;
      }

      throw new IllegalArgumentException();
    }

    throw new IllegalArgumentException();
  }

}

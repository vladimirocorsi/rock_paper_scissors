package com.vladimiro.rps.core;

/**
 * Allows communication between the {@link Game} and players.
 * 
 * @author vcorsi
 *
 */
public interface Player {

  /**
   * Called by the {@link Game} to ask the player for a symbol.
   * 
   * @return
   */
  Symbol askForSymbol();

  /**
   * Called by the {@link Game} to notify the symbol played by the opponent.
   * 
   * @param symbol
   */
  void notifyOpponentSymbol(Symbol symbol);

  /**
   * @return a string representation of this player.
   */
  String getLabel();

}

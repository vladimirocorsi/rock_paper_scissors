package com.vladimiro.rps.core;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

  private final static Logger LOGGER = Logger.getLogger(Game.class.getName());

  private final Player player1;
  private final Player player2;
  private int score1;
  private int score2;
  private final ExecutorService executor;

  /**
   * A game with two players. This class is not thread-safe: methods are designed to be called
   * sequentially.
   * 
   * @param player1
   * @param player2
   */
  public Game(Player player1, Player player2) {
    this(player1, player2, null);
  }

  /**
   * A game with two players and an {@link ExecutorService} to parallelize the request for symbols.
   * Can be useful when playing computer vs computer with time consuming {@link ComputerStrategy}.
   * 
   * @param player1
   * @param player2
   * @param executor
   */
  public Game(Player player1, Player player2, ExecutorService executor) {
    this.player1 = requireNonNull(player1);
    this.player2 = requireNonNull(player2);
    this.executor = executor;
  }

  public Symbol[] play() {
    final Symbol symbol1;
    final Symbol symbol2;

    if (executor != null) {
      final Future<Symbol> f1 = executor.submit(() -> player1.askForSymbol());
      final Future<Symbol> f2 = executor.submit(() -> player2.askForSymbol());
      try {
        symbol1 = f1.get();
        symbol2 = f2.get();
      } catch (InterruptedException | ExecutionException e) {
        LOGGER.log(Level.SEVERE, "Error getting symbols", e);
        throw new RuntimeException(e);
      }
    } else {
      symbol1 = player1.askForSymbol();
      symbol2 = player2.askForSymbol();
    }

    final int fight1Versus2 = symbol1.fight(symbol2);
    if (fight1Versus2 > 0) {
      score1++;
    } else if (fight1Versus2 < 0) {
      score2++;
    } else {
      // tie
      score1++;
      score2++;
    }

    // notify symbol
    player1.notifyOpponentSymbol(symbol2);
    player2.notifyOpponentSymbol(symbol1);
    return new Symbol[] { symbol1, symbol2 };
  }

  public int getScore1() {
    return score1;
  }

  public int getScore2() {
    return score2;
  }
}

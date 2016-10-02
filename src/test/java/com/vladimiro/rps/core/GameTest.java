package com.vladimiro.rps.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

  @Mock
  private Player player1;

  @Mock
  private Player player2;

  private Game game;
  private Game gameWithExecutor;

  @Before
  public void init() {
    game = new Game(player1, player2);
    gameWithExecutor = new Game(player1, player2, Executors.newFixedThreadPool(4));
  }

  @Test
  public void test_paper_vs_rock() {
    when(player1.askForSymbol()).thenReturn(Symbol.PAPER);
    doNothing().when(player1).notifyOpponentSymbol(Matchers.any());
    when(player2.askForSymbol()).thenReturn(Symbol.ROCK);
    doNothing().when(player2).notifyOpponentSymbol(Matchers.any());
    Symbol[] symbols = game.play();
    assertEquals(Symbol.PAPER, symbols[0]);
    assertEquals(Symbol.ROCK, symbols[1]);
    assertEquals(1, game.getScore1());
    assertEquals(0, game.getScore2());
    verify(player1).notifyOpponentSymbol(Matchers.any());
    verify(player2).notifyOpponentSymbol(Matchers.any());

    // play again
    symbols = game.play();
    assertEquals(Symbol.PAPER, symbols[0]);
    assertEquals(Symbol.ROCK, symbols[1]);
    assertEquals(2, game.getScore1());
    assertEquals(0, game.getScore2());
    verify(player1, times(2)).notifyOpponentSymbol(Matchers.any());
    verify(player2, times(2)).notifyOpponentSymbol(Matchers.any());
  }

  @Test
  public void test_scissors_vs_rock() {
    when(player1.askForSymbol()).thenReturn(Symbol.SCISSORS);
    doNothing().when(player1).notifyOpponentSymbol(Matchers.any());
    when(player2.askForSymbol()).thenReturn(Symbol.ROCK);
    doNothing().when(player2).notifyOpponentSymbol(Matchers.any());
    Symbol[] symbols = game.play();
    assertEquals(Symbol.SCISSORS, symbols[0]);
    assertEquals(Symbol.ROCK, symbols[1]);
    assertEquals(0, game.getScore1());
    assertEquals(1, game.getScore2());
    verify(player1).notifyOpponentSymbol(Matchers.any());
    verify(player2).notifyOpponentSymbol(Matchers.any());
  }

  @Test
  public void test_scissors_vs_scissors_with_executor() {
    when(player1.askForSymbol()).thenReturn(Symbol.SCISSORS);
    doNothing().when(player1).notifyOpponentSymbol(Matchers.any());
    when(player2.askForSymbol()).thenReturn(Symbol.SCISSORS);
    doNothing().when(player2).notifyOpponentSymbol(Matchers.any());
    Symbol[] symbols = gameWithExecutor.play();
    assertEquals(Symbol.SCISSORS, symbols[0]);
    assertEquals(Symbol.SCISSORS, symbols[1]);
    assertEquals(1, gameWithExecutor.getScore1());
    assertEquals(1, gameWithExecutor.getScore2());
    verify(player1).notifyOpponentSymbol(Matchers.any());
    verify(player2).notifyOpponentSymbol(Matchers.any());
  }
}

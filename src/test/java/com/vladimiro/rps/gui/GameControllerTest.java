package com.vladimiro.rps.gui;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import com.vladimiro.rps.core.Symbol;

public class GameControllerTest {

  private GameController controller;

  @Before
  public void init() {
    this.controller = new GameController();
  }

  @Test
  public void test_start_many_games() {
    controller.startComputerGame();
    controller.startHumanGame();
    controller.startComputerGame();
  }

  @Test
  public void test_start_and_play_computer() {
    controller.startComputerGame();
    controller.playComputer();
    controller.playComputer();
    controller.startHumanGame();
  }

  @Test
  public void test_start_and_play_human() {
    // play 3 rounds and calculate score
    AtomicInteger oppScore = new AtomicInteger();
    AtomicInteger myScore = new AtomicInteger();

    controller.startHumanGame();

    PlayResultDto dto1 = controller.playPaper();
    int opponentWins = dto1.getPlayedSymbol2().fight(Symbol.PAPER);
    updateScore(oppScore, myScore, opponentWins);

    PlayResultDto dto2 = controller.playScissors();
    opponentWins = dto2.getPlayedSymbol2().fight(Symbol.SCISSORS);
    updateScore(oppScore, myScore, opponentWins);

    PlayResultDto dto3 = controller.playRock();
    opponentWins = dto3.getPlayedSymbol2().fight(Symbol.ROCK);
    updateScore(oppScore, myScore, opponentWins);

    // verify the score in controller
    assertEquals(myScore.get(), dto3.getScorePlayer1());
    assertEquals(oppScore.get(), dto3.getScorePlayer2());
  }

  private void updateScore(AtomicInteger oppScore, AtomicInteger myScore, int opponentWins) {
    if (opponentWins > 0) {
      oppScore.incrementAndGet();
    } else if (opponentWins < 0) {
      myScore.incrementAndGet();
    } else {
      oppScore.incrementAndGet();
      myScore.incrementAndGet();
    }
  }

  @Test(expected = IllegalStateException.class)
  public void test_start_human_play_computer() {
    controller.startHumanGame();
    controller.playComputer();
  }

  @Test(expected = IllegalStateException.class)
  public void test_start_computer_play_rock() {
    controller.startComputerGame();
    controller.playRock();
  }

  @Test(expected = IllegalStateException.class)
  public void test_start_computer_play_paper() {
    controller.startComputerGame();
    controller.playPaper();
  }

  @Test(expected = IllegalStateException.class)
  public void test_start_computer_play_scissors() {
    controller.startComputerGame();
    controller.playScissors();
  }

}

package com.vladimiro.rps.gui;

import com.vladimiro.rps.core.Symbol;

/**
 * A POJO carrying information about a played round of a game.
 * 
 * @author vladimiro
 *
 */
class PlayResultDto {

  private String labelPlayer1;
  private String labelPlayer2;
  private int scorePlayer1;
  private int scorePlayer2;
  private Symbol playedSymbol1;
  private Symbol playedSymbol2;

  public String getLabelPlayer1() {
    return labelPlayer1;
  }

  public void setLabelPlayer1(String labelPlayer1) {
    this.labelPlayer1 = labelPlayer1;
  }

  public String getLabelPlayer2() {
    return labelPlayer2;
  }

  public void setLabelPlayer2(String labelPlayer2) {
    this.labelPlayer2 = labelPlayer2;
  }

  public int getScorePlayer1() {
    return scorePlayer1;
  }

  public void setScorePlayer1(int scorePlayer1) {
    this.scorePlayer1 = scorePlayer1;
  }

  public int getScorePlayer2() {
    return scorePlayer2;
  }

  public void setScorePlayer2(int scorePlayer2) {
    this.scorePlayer2 = scorePlayer2;
  }

  public void setPlayedSymbol1(Symbol symbol) {
    this.playedSymbol1 = symbol;
  }

  public void setPlayedSymbol2(Symbol symbol) {
    this.playedSymbol2 = symbol;
  }

  public Symbol getPlayedSymbol1() {
    return playedSymbol1;
  }

  public Symbol getPlayedSymbol2() {
    return playedSymbol2;
  }

}

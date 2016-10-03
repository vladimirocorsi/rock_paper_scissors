package com.vladimiro.rps.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HumanPlayerTest {

  @Mock
  private SymbolSource source;
  private HumanPlayer player;

  @Before
  public void init() {
    player = new HumanPlayer(source);
  }

  @Test
  public void test_readSymbol() {
    when(source.read()).thenReturn(Symbol.ROCK);
    assertEquals(Symbol.ROCK, player.askForSymbol());
    verify(source).read();
  }

}

package com.vladimiro.rps.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComputerPlayerTest {
	
	@Mock
	private ComputerStrategy strategy;
	
	private ComputerPlayer player;
	
	@Before
	public void init(){
		player = new ComputerPlayer(strategy, "testPlayer");
	}

	@Test
	public void test_askForSymbol() {
		doNothing().when(strategy).addOpponentSymbol(Mockito.any());
		Mockito.when(strategy.generateSymbol()).thenReturn(Symbol.PAPER);
		assertEquals(Symbol.PAPER, player.askForSymbol());
	}
	
	@Test
	public void test_notifyOpponentSymbol(){
		player.notifyOpponentSymbol(Symbol.ROCK);
		verify(strategy).addOpponentSymbol(Mockito.any());
	}

}

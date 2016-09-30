package com.vladimiro.rps.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SymbolComparatorTest {
	
	private SymbolComparator comparator;

	@Before
	public void init(){
		this.comparator = new SymbolComparator();
	}

	/**
	 * Test order between the couples of the classic 3-symbol game
	 */
	@Test
	public void test_symbol_ordering_for_3_symbol_game() {
		assertEquals(0, comparator.compare(Symbol.PAPER, Symbol.PAPER));
		assertEquals(1, comparator.compare(Symbol.PAPER, Symbol.ROCK));
		assertEquals(-1, comparator.compare(Symbol.PAPER, Symbol.SCISSORS));
		
		assertEquals(0, comparator.compare(Symbol.ROCK, Symbol.ROCK));
		assertEquals(1, comparator.compare(Symbol.ROCK, Symbol.SCISSORS));
		assertEquals(-1, comparator.compare(Symbol.ROCK, Symbol.PAPER));
		
		assertEquals(0, comparator.compare(Symbol.SCISSORS, Symbol.SCISSORS));
		assertEquals(1, comparator.compare(Symbol.SCISSORS, Symbol.PAPER));
		assertEquals(-1, comparator.compare(Symbol.SCISSORS, Symbol.ROCK));
	}
	
	/**
	 * Assure that every couple is comparable
	 */
	@Test
	public void testPermutations() {
		for(Symbol s1 : Symbol.values()){
			for(Symbol s2 : Symbol.values()){
				comparator.compare(s1, s2);
			}
		}
	}

}

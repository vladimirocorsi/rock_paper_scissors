package com.vladimiro.rps.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SymbolTest {

  /**
   * Test order between the couples of the classic 3-symbol game
   */
  @Test
  public void test_symbol_ordering_for_3_symbol_game() {
    assertEquals(0, Symbol.PAPER.fight(Symbol.PAPER));
    assertEquals(1, Symbol.PAPER.fight(Symbol.ROCK));
    assertEquals(-1, Symbol.PAPER.fight(Symbol.SCISSORS));

    assertEquals(0, Symbol.ROCK.fight(Symbol.ROCK));
    assertEquals(1, Symbol.ROCK.fight(Symbol.SCISSORS));
    assertEquals(-1, Symbol.ROCK.fight(Symbol.PAPER));

    assertEquals(0, Symbol.SCISSORS.fight(Symbol.SCISSORS));
    assertEquals(1, Symbol.SCISSORS.fight(Symbol.PAPER));
    assertEquals(-1, Symbol.SCISSORS.fight(Symbol.ROCK));
  }

  /**
   * Assure that every couple is comparable
   */
  @Test
  public void testPermutations() {
    for (Symbol s1 : Symbol.values()) {
      for (Symbol s2 : Symbol.values()) {
        s1.fight(s2);
      }
    }
  }

}

package com.vladimiro.rps.core;

import static java.util.Objects.nonNull;

import java.util.Comparator;

/**
 * This class defines the order (wins > loses) between all the couples of symbols.
 *  
 * @author vcorsi
 *
 */
public class SymbolComparator implements Comparator<Symbol>{

	@Override
	public int compare(Symbol o1, Symbol o2) {
		nonNull(o1);
		nonNull(o2);
		
		if(o1.equals(o2)){
			return 0;
		}
		if(Symbol.SCISSORS.equals(o1)){
			//scissors wins
			if(Symbol.PAPER.equals(o2)){
				return 1;
			}
			//rock wins
			if(Symbol.ROCK.equals(o2)){
				return -1;
			}
			throw new IllegalArgumentException();
		}
		
		if(Symbol.PAPER.equals(o1)){
			//paper wins
			if(Symbol.ROCK.equals(o2)){
				return 1;
			}
			//scissors wins
			if(Symbol.SCISSORS.equals(o2)){
				return -1;
			}
			
			throw new IllegalArgumentException();
		}
		
		if(Symbol.ROCK.equals(o1)){
			//rock wins
			if(Symbol.SCISSORS.equals(o2)){
				return 1;
			}
			//paper wins
			if(Symbol.PAPER.equals(o2)){
				return -1;
			}
			
			throw new IllegalArgumentException();
		}
		
		throw new IllegalArgumentException();
	}

}

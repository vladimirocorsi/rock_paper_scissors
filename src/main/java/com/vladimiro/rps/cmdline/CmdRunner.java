package com.vladimiro.rps.cmdline;

import java.util.Scanner;

import com.vladimiro.rps.core.ComputerPlayer;
import com.vladimiro.rps.core.ComputerStrategyFactory;
import com.vladimiro.rps.core.Game;
import com.vladimiro.rps.core.HumanPlayer;
import com.vladimiro.rps.core.Player;
import com.vladimiro.rps.core.Symbol;

/**
 * A command line version of the game.
 * 
 * @author vcorsi
 *
 */
public class CmdRunner implements Runnable {
	
	@Override
	public void run() {
		String playersType = null;
		final Scanner scanner = new Scanner(System.in);
		while (true) {
			while (!("P".equalsIgnoreCase(playersType) || "C".equalsIgnoreCase(playersType))) {
				System.out.println("Play against computer (P) or play computer vs computer (C) or quit (Q)");
				playersType = scanner.nextLine();
				if("Q".equalsIgnoreCase(playersType)){
					System.exit(0);
				}
			}

			final Player player1;
			final Player player2;

			if (playersType.equalsIgnoreCase("P")) {
				player1 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer");
				player2 = new HumanPlayer(new CommandLineSymbolSource());
			} else {
				player1 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer 1");
				player2 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer 2");
			}

			final Game game = new Game(player1, player2);

			boolean cont = true;
			String continueOrNewGame;
			while (cont) {
				Symbol[] playedSymbols = game.play();
				System.out.println(player1.getLabel() + " plays: " + playedSymbols[0]);
				System.out.println(player2.getLabel() + " plays: " + playedSymbols[1]);

				System.out.println("Score " + player1.getLabel() + " : " + game.getScore1());
				System.out.println("Score " + player2.getLabel() + " : " + game.getScore2());

				System.out.println("Press enter to continue or play a new game (N) or quit (Q)");
				continueOrNewGame = scanner.nextLine();
				if("Q".equalsIgnoreCase(continueOrNewGame)){
					System.exit(0);
				}
				cont = !"N".equalsIgnoreCase(continueOrNewGame);
			}
			// new game
			playersType = null;
		}
	}
	
}

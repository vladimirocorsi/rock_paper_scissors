package com.vladimiro.rps;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String playersType = null;
		final Scanner scanner = new Scanner(System.in);
		while (true) {
			
			while (!"P".equals(playersType) || !"C".equals(playersType)) {
				System.out.println("Play against computer (P) or play computer vs computer (C)");
				playersType = scanner.nextLine();
			}

			final Player player1 = new ComputerPlayer();
			final Player player2;

			if (playersType.equals("P")) {
				player2 = new HumanPlayer(new CommandLineSymbolSource());
			} else {
				player2 = new ComputerPlayer();
			}

			final Game game = new Game(player1, player2);

			boolean cont = true;
			String continueOrNewGame;
			while (cont) {
				Symbol[] playedSymbols = game.play();
				System.out.println("Player 1 plays: " + playedSymbols[0]);
				System.out.println("Player 2 plays: " + playedSymbols[2]);
				
				System.out.println("Score player 1 : " + game.getScore1());
				System.out.println("Score player 2 : " + game.getScore2());
				System.out.println("Continue (C) or play a new game (N)");
				continueOrNewGame = scanner.nextLine();
				cont = "C".equals(continueOrNewGame);
			}
			//new game
		}
	}
}

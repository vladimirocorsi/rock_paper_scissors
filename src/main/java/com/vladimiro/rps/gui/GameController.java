package com.vladimiro.rps.gui;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.vladimiro.rps.core.ComputerPlayer;
import com.vladimiro.rps.core.ComputerStrategyFactory;
import com.vladimiro.rps.core.Game;
import com.vladimiro.rps.core.HumanPlayer;
import com.vladimiro.rps.core.Player;
import com.vladimiro.rps.core.Symbol;
import com.vladimiro.rps.core.SymbolSource;

/**
 * A controller allowing to create human vs computer and computer vs computer games.
 * Once a game created, commands to play rounds are provided.
 * This class is not thread-safe: methods are designed to be called sequentially.  
 *  
 * @author vladimiro
 *
 */
public class GameController {
	
	enum ControllerStatus{
		COMPUTER_GAME, HUMAN_GAME;
	}
	
	/**
	 * A {@link SymbolSource} reading symbols from a {@link BlockingQueue}.
	 * The {@link GameController} is designed to handle one play method call
	 * at a time. With this assumption adding and removing a given symbol in the queue is 
	 * done during the time of such a method call.
	 * 
	 * @author vladimiro
	 *
	 */
	class ControllerSource implements SymbolSource{

		@Override
		public Symbol readSymbol() {
			try {
				return symbolQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}
		
	}

	private Game game;
	private Player player2;
	private Player player1;
	private final BlockingQueue<Symbol> symbolQueue = new LinkedBlockingQueue<>(1);
	private ControllerStatus status;
	
	/**
	 * Start a computer vs computer game.
	 */
	public void startComputerGame() {
		this.player1 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer 1");
		this.player2 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer 2");
		this.game = new Game(player1, player2);
		status = ControllerStatus.COMPUTER_GAME;
	}
	
	/**
	 * Start a human vs computer game.
	 */
	public void startHumanGame() {
		this.player1 = new HumanPlayer(new ControllerSource());
		this.player2 = new ComputerPlayer(ComputerStrategyFactory.randomStrategy(), "Computer");
		this.game = new Game(player1, player2);
		status = ControllerStatus.HUMAN_GAME;
	}

	/**
	 * Play a round of computer to computer game. 
	 * @return a {@link PlayResultDto} with informations about the played round.
	 */
	public PlayResultDto playComputer() {
		if(status != ControllerStatus.COMPUTER_GAME){
			throw new IllegalStateException();
		}
		Symbol[] symbols = game.play();
		return makeDto(symbols);
	}

	/**
	 * Play rock in a human to computer game round.
	 * @return a {@link PlayResultDto} with informations about the played round.
	 */
	public PlayResultDto playRock() {
		if(status != ControllerStatus.HUMAN_GAME){
			throw new IllegalStateException();
		}
		this.symbolQueue.offer(Symbol.ROCK);
		return makeDto(game.play());
	}

	/**
	 * Play paper in a human to computer game round.
	 * @return a {@link PlayResultDto} with informations about the played round.
	 */
	public PlayResultDto playPaper() {
		if(status != ControllerStatus.HUMAN_GAME){
			throw new IllegalStateException();
		}
		this.symbolQueue.offer(Symbol.PAPER);
		return makeDto(game.play());
	}

	/**
	 * Play scissors in a human to computer game round.
	 * @return a {@link PlayResultDto} with informations about the played round.
	 */
	public PlayResultDto playScissors() {
		if(status != ControllerStatus.HUMAN_GAME){
			throw new IllegalStateException();
		}
		this.symbolQueue.offer(Symbol.SCISSORS);
		return makeDto(game.play());
	}

	private PlayResultDto makeDto(Symbol[] symbols) {
		PlayResultDto dto = new PlayResultDto();
		dto.setLabelPlayer1(player1.getLabel());
		dto.setLabelPlayer2(player2.getLabel());
		dto.setScorePlayer1(game.getScore1());
		dto.setScorePlayer2(game.getScore2());
		dto.setPlayedSymbol1(symbols[0]);
		dto.setPlayedSymbol2(symbols[1]);
		return dto;
	}

}

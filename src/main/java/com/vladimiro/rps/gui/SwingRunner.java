package com.vladimiro.rps.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

/**
 * Instantiates and assembles the Swing components making the GUI. Handles the calls to
 * {@link GameController} and updates the GUI according to results.
 * 
 * @author vladimiro
 *
 */
public class SwingRunner implements Runnable {

  private final static Logger LOGGER = Logger.getLogger(SwingRunner.class.getName());

  private static final String COMPUTER = "computer";
  private static final String HUMAN = "human";
  private JPanel cards;
  private GameController gameController;
  private JPanel scorePanel;
  private JLabel score2;
  private JLabel score1;
  private JMenuBar menubar;
  private JPanel computerPanel;
  private JButton playButton;
  private JLabel played1Label;
  private JLabel played2Label;
  private JMenuItem humanMenuItem;
  private JMenuItem computerMenuItem;
  private JPanel humanPanel;
  private JButton playRockButton;
  private JButton playPaperButton;
  private JButton playScissorsButton;
  private boolean actionDisabled;

  @Override
  public void run() {
    gameController = new GameController();
    // Create and set up the window.
    JFrame frame = new JFrame("Rock Paper Scissors!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(createMenuBar());

    final Container contentPane = frame.getContentPane();
    JPanel verticalPanel = new JPanel();
    verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
    verticalPanel.add(getCards());
    verticalPanel.add(getScorePanel());
    contentPane.add(verticalPanel, BorderLayout.CENTER);

    // Display the window.
    // frame.pack();
    frame.setSize(400, 200);
    frame.setVisible(true);
  }

  private JMenuBar createMenuBar() {
    if (menubar == null) {
      menubar = new JMenuBar();
      JMenu newGameMenu = new JMenu("New Game");
      humanMenuItem = new JMenuItem("Human vs Computer");
      humanMenuItem.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          // disable actions
          setActionsDisabled(true);
          // Create new human vs computer game
          new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
              gameController.startHumanGame();
              return null;
            }

            @Override
            protected void done() {
              resetUI();
              CardLayout cl = (CardLayout) (getCards().getLayout());
              cl.show(getCards(), HUMAN);
              setActionsDisabled(false);
            };

          }.execute();
        }
      });
      computerMenuItem = new JMenuItem("Computer vs Computer");
      computerMenuItem.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          // disable actions
          setActionsDisabled(true);
          // Create new computer game
          new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
              gameController.startComputerGame();
              return null;
            }

            @Override
            protected void done() {
              resetUI();
              CardLayout cl = (CardLayout) (getCards().getLayout());
              cl.show(getCards(), COMPUTER);
              setActionsDisabled(false);
            };

          }.execute();
        }

      });
      newGameMenu.add(humanMenuItem);
      newGameMenu.add(computerMenuItem);
      menubar.add(newGameMenu);
    }
    return menubar;
  }

  private JPanel getCards() {
    if (cards == null) {
      cards = new JPanel(new CardLayout());
      cards.add("empty", new JPanel());
      cards.add(COMPUTER, getComputerPanel());
      cards.add(HUMAN, getHumanPanel());
    }
    return cards;
  }

  private JPanel getHumanPanel() {
    if (humanPanel == null) {
      humanPanel = new JPanel();
      JPanel horizontalPanel = new JPanel();
      playRockButton = new JButton("Play rock");
      playRockButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          setActionsDisabled(true);
          new PlaySwingSworker(v -> gameController.playRock()).execute();
        }
      });
      horizontalPanel.add(playRockButton);

      playPaperButton = new JButton("Play paper");
      playPaperButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          setActionsDisabled(true);
          new PlaySwingSworker(v -> gameController.playPaper()).execute();
        }

      });
      horizontalPanel.add(playPaperButton);

      playScissorsButton = new JButton("Play scissors");
      playScissorsButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          setActionsDisabled(true);
          new PlaySwingSworker(v -> gameController.playScissors()).execute();
        }
      });
      horizontalPanel.add(playScissorsButton);

      humanPanel.add(horizontalPanel);
    }
    return humanPanel;
  }

  private JPanel getComputerPanel() {
    if (computerPanel == null) {
      computerPanel = new JPanel();
      playButton = new JButton("Play");
      playButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isActionsDisabled()) {
            return;
          }
          setActionsDisabled(true);
          new PlaySwingSworker(v -> gameController.playComputer()).execute();
        }
      });
      computerPanel.add(playButton);
    }
    return computerPanel;
  }

  private JPanel getScorePanel() {
    if (scorePanel == null) {
      scorePanel = new JPanel();
      scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));

      played1Label = new JLabel();
      played2Label = new JLabel();
      scorePanel.add(played1Label);
      scorePanel.add(played2Label);

      score1 = new JLabel();
      score1.setBorder(new EmptyBorder(10, 00, 0, 0));
      score2 = new JLabel();
      scorePanel.add(score1);
      scorePanel.add(score2);
    }
    return scorePanel;
  }

  private void resetUI() {
    score1.setText("");
    score2.setText("");
    played1Label.setText("");
    played2Label.setText("");
  }

  private void setActionsDisabled(boolean b) {
    actionDisabled = b;
  }

  private boolean isActionsDisabled() {
    return actionDisabled;
  }

  class PlaySwingSworker extends SwingWorker<PlayResultDto, Object> {

    private Function<Void, PlayResultDto> function;

    PlaySwingSworker(Function<Void, PlayResultDto> doInBackGround) {
      this.function = doInBackGround;
    }

    @Override
    protected PlayResultDto doInBackground() throws Exception {
      return function.apply(null);
    }

    protected void done() {
      PlayResultDto playResultDto;
      try {
        playResultDto = get();
      } catch (InterruptedException | ExecutionException e) {
        LOGGER.log(Level.SEVERE, "Error getting symbol", e);
        throw new RuntimeException(e);
      }
      played1Label.setText(playResultDto.getLabelPlayer1() + " played "
          + playResultDto.getPlayedSymbol1().getLabel());
      played2Label.setText(playResultDto.getLabelPlayer2() + " played "
          + playResultDto.getPlayedSymbol2().getLabel());
      score1.setText(
          "Score " + playResultDto.getLabelPlayer1() + ": " + playResultDto.getScorePlayer1());
      score2.setText(
          "Score " + playResultDto.getLabelPlayer2() + ": " + playResultDto.getScorePlayer2());
      setActionsDisabled(false);
    };

  }

}

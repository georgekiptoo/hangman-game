package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class HangmanFrame extends JFrame {

    private Game game;
    private DrawPanel drawPanel;
    private KeyboardPanel keyboardPanel;
    private JLabel wordLabel;
    private JLabel errorLabel;
    private JLabel messageLabel;

    public HangmanFrame() {
        super("Hangman - Spanzuratoarea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        game = new Game();
        initComponents();
        refreshUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(30, 30, 40));

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.WEST);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(new Color(30, 30, 40));
        right.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Courier New", Font.BOLD, 28));
        wordLabel.setForeground(new Color(220, 220, 220));
        wordLabel.setAlignmentX(CENTER_ALIGNMENT);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(new Color(230, 100, 100));
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(new Color(100, 220, 100));
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        keyboardPanel = new KeyboardPanel(this::handleLetter);

        JButton btnNew = new JButton("Joc nou");
        btnNew.setFont(new Font("Arial", Font.BOLD, 13));
        btnNew.setBackground(new Color(60, 130, 200));
        btnNew.setForeground(Color.WHITE);
        btnNew.setFocusPainted(false);
        btnNew.setAlignmentX(CENTER_ALIGNMENT);
        btnNew.addActionListener(e -> startNewGame());

        right.add(Box.createVerticalStrut(10));
        right.add(wordLabel);
        right.add(Box.createVerticalStrut(12));
        right.add(errorLabel);
        right.add(Box.createVerticalStrut(8));
        right.add(messageLabel);
        right.add(Box.createVerticalStrut(16));
        right.add(keyboardPanel);
        right.add(Box.createVerticalStrut(14));
        right.add(btnNew);

        add(right, BorderLayout.CENTER);
    }

    private void handleLetter(char letter) {
        if (game.isOver()) return;

        boolean correct = game.guessLetter(letter);
        keyboardPanel.markLetter(letter, correct);
        refreshUI();

        if (game.isWon()) {
            messageLabel.setText("Felicitari! Ai castigat!");
            messageLabel.setForeground(new Color(100, 220, 100));
            keyboardPanel.disableAll();
        } else if (game.isLost()) {
            wordLabel.setText(game.getTargetWord().chars()
                    .mapToObj(c -> String.valueOf((char) c))
                    .reduce("", (a, b) -> a + b + " ").trim());

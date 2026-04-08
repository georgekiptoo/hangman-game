package model;

import util.WordBank;
import java.util.ArrayList;

public class Game {

    public static final int MAX_ERRORS = 6;

    private String targetWord;
    private boolean[] guessed;
    private ArrayList<Character> wrongLetters;
    private int errorCount;

    public Game() {
        newGame();
    }

    public void newGame() {
        targetWord = WordBank.getRandomWord();
        guessed = new boolean[targetWord.length()];
        wrongLetters = new ArrayList<>();
        errorCount = 0;
    }

    public boolean guessLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == letter) {
                guessed[i] = true;
                found = true;
            }
        }
        if (!found) {
            wrongLetters.add(letter);
            errorCount++;
        }
        return found;
    }

    public boolean isWon() {
        for (boolean b : guessed) {
            if (!b) return false;
        }
        return true;
    }

    public boolean isLost() {
        return errorCount >= MAX_ERRORS;
    }

    public boolean isOver() {
        return isWon() || isLost();
    }

    public String getTargetWord() { return targetWord; }
    public boolean[] getGuessed() { return guessed; }
    public ArrayList<Character> getWrongLetters() { return wrongLetters; }
    public int getErrorCount() { return errorCount; }

    public String getDisplayWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetWord.length(); i++) {
            sb.append(guessed[i] ? targetWord.charAt(i) : '_').append(' ');
        }
        return sb.toString().trim();
    }

    public String getErrorDisplay() {
        StringBuilder sb = new StringBuilder("Greseli (")
                .append(errorCount).append("/").append(MAX_ERRORS).append("):  ");
        for (char c : wrongLetters) sb.append(c).append("  ");
        return sb.toString();
    }
}

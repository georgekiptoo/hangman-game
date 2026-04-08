package ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class KeyboardPanel extends JPanel {

    private static final Color COLOR_DEFAULT  = new Color(55, 55, 70);
    private static final Color COLOR_CORRECT  = new Color(40, 160, 80);
    private static final Color COLOR_WRONG    = new Color(180, 50, 50);

    public KeyboardPanel(Consumer<Character> onLetterPressed) {
        setLayout(new GridLayout(0, 7, 4, 4));
        setBackground(new Color(30, 30, 40));
        buildKeys(onLetterPressed);
    }

    private void buildKeys(Consumer<Character> onLetterPressed) {
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton btn = new JButton(String.valueOf(c));
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setBackground(COLOR_DEFAULT);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setMargin(new Insets(4, 4, 4, 4));
            final char letter = c;
            btn.addActionListener(e -> onLetterPressed.accept(letter));
            add(btn);
        }
    }

    public void markLetter(char letter, boolean correct) {
        for (Component c : getComponents()) {
            if (c instanceof JButton b && b.getText().equals(String.valueOf(letter))) {
                b.setEnabled(false);
                b.setBackground(correct ? COLOR_CORRECT : COLOR_WRONG);
            }
        }
    }

    public void reset() {
        for (Component c : getComponents()) {
            if (c instanceof JButton b) {
                b.setEnabled(true);
                b.setBackground(COLOR_DEFAULT);
            }
        }
    }

    public void disableAll() {
        for (Component c : getComponents()) c.setEnabled(false);
    }
}

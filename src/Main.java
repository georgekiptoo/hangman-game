import ui.HangmanFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanFrame::new);
    }
}

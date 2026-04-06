import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Hangman extends JFrame {

    private static final String[] WORDS = {
            "CALCULATOR", "PROGRAMARE", "ALGORITM", "VARIABILA",
            "FUNCTIE", "RECURSIVITATE", "POLIMORFISM", "MOSTENIRE",
            "INTERFATA", "COMPILATOR", "DEPANARE", "STRUCTURA",
            "TASTATURA", "MONITOR", "PROCESOR", "MEMORIE",
            "RETEA", "SECURITATE", "CRIPTOGRAFIE", "BAZA"
    };

    private String targetWord;
    private boolean[] guessed;
    private ArrayList<Character> wrongList = new ArrayList<>();
    private int greseli;
    private static final int MAX_GRESELI = 6;

    private DrawPanel drawPanel;
    private JLabel wordLabel;
    private JLabel greseliLabel;
    private JLabel mesajLabel;
    private JPanel tastatura;
    private JButton btnNou;

    public Hangman() {
        super("Hangman - Spanzuratoarea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        jocNou();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(30, 30, 40));

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(260, 320));
        add(drawPanel, BorderLayout.WEST);

        JPanel dreapta = new JPanel();
        dreapta.setLayout(new BoxLayout(dreapta, BoxLayout.Y_AXIS));
        dreapta.setBackground(new Color(30, 30, 40));
        dreapta.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Courier New", Font.BOLD, 28));
        wordLabel.setForeground(new Color(220, 220, 220));
        wordLabel.setAlignmentX(CENTER_ALIGNMENT);

        greseliLabel = new JLabel("Greseli: ", SwingConstants.CENTER);
        greseliLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        greseliLabel.setForeground(new Color(230, 100, 100));
        greseliLabel.setAlignmentX(CENTER_ALIGNMENT);

        mesajLabel = new JLabel(" ", SwingConstants.CENTER);
        mesajLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mesajLabel.setForeground(new Color(100, 220, 100));
        mesajLabel.setAlignmentX(CENTER_ALIGNMENT);

        tastatura = new JPanel(new GridLayout(0, 7, 4, 4));
        tastatura.setBackground(new Color(30, 30, 40));
        construiesteTastatura();

        btnNou = new JButton("Joc nou");
        btnNou.setFont(new Font("Arial", Font.BOLD, 13));
        btnNou.setBackground(new Color(60, 130, 200));
        btnNou.setForeground(Color.WHITE);
        btnNou.setFocusPainted(false);
        btnNou.setAlignmentX(CENTER_ALIGNMENT);
        btnNou.addActionListener(e -> jocNou());

        dreapta.add(Box.createVerticalStrut(10));
        dreapta.add(wordLabel);
        dreapta.add(Box.createVerticalStrut(12));
        dreapta.add(greseliLabel);
        dreapta.add(Box.createVerticalStrut(8));
        dreapta.add(mesajLabel);
        dreapta.add(Box.createVerticalStrut(16));
        dreapta.add(tastatura);
        dreapta.add(Box.createVerticalStrut(14));
        dreapta.add(btnNou);

        add(dreapta, BorderLayout.CENTER);
    }

    private void construiesteTastatura() {
        tastatura.removeAll();
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton btn = new JButton(String.valueOf(c));
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setBackground(new Color(55, 55, 70));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setMargin(new Insets(4, 4, 4, 4));
            final char litera = c;
            btn.addActionListener(e -> proceseazaLitera(litera));
            tastatura.add(btn);
        }
        tastatura.revalidate();
        tastatura.repaint();
    }

    private void jocNou() {
        Random rand = new Random();
        targetWord = WORDS[rand.nextInt(WORDS.length)];
        guessed = new boolean[targetWord.length()];
        wrongList.clear();
        greseli = 0;

        mesajLabel.setText(" ");
        mesajLabel.setForeground(new Color(100, 220, 100));

        for (Component c : tastatura.getComponents()) {
            if (c instanceof JButton b) {
                b.setEnabled(true);
                b.setBackground(new Color(55, 55, 70));
            }
        }

        actualizeazaCuvant();
        actualizeazaGreseli();
        drawPanel.setGreseli(0);
        drawPanel.repaint();
    }

    private void proceseazaLitera(char litera) {
        boolean gasit = false;
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == litera) {
                guessed[i] = true;
                gasit = true;
            }
        }

        for (Component c : tastatura.getComponents()) {
            if (c instanceof JButton b && b.getText().equals(String.valueOf(litera))) {
                b.setEnabled(false);
                b.setBackground(gasit ? new Color(40, 160, 80) : new Color(180, 50, 50));
            }
        }

        if (!gasit) {
            wrongList.add(litera);
            greseli++;
            drawPanel.setGreseli(greseli);
            drawPanel.repaint();
        }

        actualizeazaCuvant();
        actualizeazaGreseli();
        verificaFinal();
    }

    private void verificaFinal() {
        boolean castigat = true;
        for (boolean b : guessed) {
            if (!b) {
                castigat = false;
                break;
            }
        }

        if (castigat) {
            mesajLabel.setText("Felicitari! Ai castigat!");
            dezactiveazaTastatura();
        } else if (greseli >= MAX_GRESELI) {
            StringBuilder sb = new StringBuilder();
            for (char c : targetWord.toCharArray()) sb.append(c).append(" ");
            wordLabel.setText(sb.toString().trim());
            mesajLabel.setText("Ai pierdut! Cuvantul era: " + targetWord);
            mesajLabel.setForeground(new Color(230, 80, 80));
            dezactiveazaTastatura();
        }
    }

    private void dezactiveazaTastatura() {
        for (Component c : tastatura.getComponents()) c.setEnabled(false);
    }

    private void actualizeazaCuvant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetWord.length(); i++) {
            sb.append(guessed[i] ? targetWord.charAt(i) : '_').append(' ');
        }
        wordLabel.setText(sb.toString().trim());
    }

    private void actualizeazaGreseli() {
        StringBuilder sb = new StringBuilder("Greseli (")
                .append(greseli).append("/").append(MAX_GRESELI).append("):  ");
        for (char c : wrongList) sb.append(c).append("  ");
        greseliLabel.setText(sb.toString());
    }

    // panou unde se deseneaza spanzuratoarea
    class DrawPanel extends JPanel {
        private int greseli = 0;

        public void setGreseli(int g) { this.greseli = g; }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(30, 30, 40));

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // structura spanzuratorii
            g2.setColor(new Color(180, 160, 100));
            g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(30, 290, 220, 290);  // baza
            g2.drawLine(80, 290, 80, 30);    // stalp
            g2.drawLine(80, 30, 175, 30);    // bara sus
            g2.drawLine(175, 30, 175, 70);   // coarda

            // corpul omului
            g2.setColor(new Color(220, 100, 100));
            g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            if (greseli >= 1) g2.drawOval(150, 70, 50, 50);           // cap
            if (greseli >= 2) g2.drawLine(175, 120, 175, 210);        // corp
            if (greseli >= 3) g2.drawLine(175, 140, 135, 185);        // brat stang
            if (greseli >= 4) g2.drawLine(175, 140, 215, 185);        // brat drept
            if (greseli >= 5) g2.drawLine(175, 210, 135, 260);        // picior stang
            if (greseli >= 6) g2.drawLine(175, 210, 215, 260);        // picior drept
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Hangman::new);
    }
}

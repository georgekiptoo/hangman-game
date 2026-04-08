package ui;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private int errorCount = 0;

    public DrawPanel() {
        setPreferredSize(new Dimension(260, 320));
        setBackground(new Color(30, 30, 40));
    }

    public void setErrorCount(int count) {
        this.errorCount = count;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // gallows structure
        g2.setColor(new Color(180, 160, 100));
        g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(30, 290, 220, 290);   // base
        g2.drawLine(80, 290, 80, 30);     // pole
        g2.drawLine(80, 30, 175, 30);     // top bar
        g2.drawLine(175, 30, 175, 70);    // rope

        // hangman body parts
        g2.setColor(new Color(220, 100, 100));
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (errorCount >= 1) g2.drawOval(150, 70, 50, 50);         // head
        if (errorCount >= 2) g2.drawLine(175, 120, 175, 210);      // body
        if (errorCount >= 3) g2.drawLine(175, 140, 135, 185);      // left arm
        if (errorCount >= 4) g2.drawLine(175, 140, 215, 185);      // right arm
        if (errorCount >= 5) g2.drawLine(175, 210, 135, 260);      // left leg
        if (errorCount >= 6) g2.drawLine(175, 210, 215, 260);      // right leg
    }
}

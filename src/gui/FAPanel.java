package gui;

import fap_java.Graph;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class FAPanel extends JPanel {
    
    // Images
    protected Image sword;
    protected Image clouds;
    // Locations
    protected double swordX;
    protected double cloudsX;
    protected double buttonsX;
    // Slide animation
    protected int speed;
    protected int maxxS = Constants.maxxS;
    protected int minxS = Constants.minxS; 
    protected int maxxC = Constants.maxxC;
    protected int minxC = Constants.minxC;
    
    public FAPanel() {
        super();
        sword = Graph.guimg.get("MM_sword");
        clouds = Graph.guimg.get("MM_clouds");
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        // Background
        int w = this.getWidth();
        int h = this.getHeight();

        // Paint a gradient from top to bottom
        GradientPaint gp = new GradientPaint(0, 0, Constants.top, 0, h, Constants.bottom);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        // Sword & clouds
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(clouds, (int)cloudsX, 50, 1600, 195, this);
        g2d.drawImage(sword, (int)swordX, 80, 2153, 762, this);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
}

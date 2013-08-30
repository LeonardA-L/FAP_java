package fap_java;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.JButton;

// Also does victory screen
public class PauseScreen extends Element{
    private boolean displayVictory;
    private boolean resuming;
    private Game game;
    
    private String message;
    private Team winner;
    private boolean advMode;
    
    // Buttons
    private JButton btnResume = new JButton();
    private JButton btnWorldMap = new JButton();
    private JButton btnMainMenu = new JButton();
    
    
    public PauseScreen(boolean dispVict, Game game) {
        super();
        displayVictory = dispVict;
        this.game = game;
        resuming = false;
        message="";
        
        // Init buttons
        btnResume.setText("Continuer");
        btnResume.setSize(140, 40);
        btnResume.setLocation(20, 20);
        game.add(btnResume);
        
        btnWorldMap.setText("Retour � la carte");
        btnWorldMap.setSize(140, 40);
        btnWorldMap.setLocation(20, 100);
        game.add(btnWorldMap);
        
        btnMainMenu.setText("Retour au menu");
        btnMainMenu.setSize(140, 40);
        btnMainMenu.setLocation(20, 200);
        game.add(btnMainMenu);
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.drawImage(Graph.guimg.get("pauseScreen"), 0, 0,game.getWidth(),game.getHeight(), game);
        if(!resuming){
            // Draw buttons
            //System.out.println("I'm drawin' I'm drawin' !");
            
            // Draw victory
            if(displayVictory){
                int width = 528;
                int height = 444;
                int x =(game.getWidth()-width)/2;
                int y =(game.getHeight()-height)/2;
                //TODO replace w/h with relative
                g.drawImage(Graph.guimg.get("victoryScreen"), x, y,width,height, game);
                
                Graphics2D g2d = (Graphics2D)g;
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(message);
                g.drawString(message, x+(width-textWidth)/2, y+20);
                
                if(winner != null){
                    ArrayList<Player> players = winner.getPlayersInThisTeam();
                    for(int j=0;j<players.size();j++){
                        //TODO player placement (center and space)
                        players.get(j).paintStick(g, x+20+j*30, y+100);
                    }
                }
            }
        }
    }

    public String toString() {
        return null;
    }

    public void setResuming(boolean resuming) {
        this.resuming = resuming;
        
        if(resuming){
            //Remove button
            game.remove(this.btnMainMenu);
            game.remove(this.btnResume);
            game.remove(this.btnWorldMap);
        }
    }

    public boolean isResuming() {
        return resuming;
    }

    public void setDisplayVictory(boolean displayVictory) {
        this.displayVictory = displayVictory;
    }

    public boolean isDisplayVictory() {
        return displayVictory;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getWinner() {
        return winner;
    }

    public void setAdvMode(boolean advMode) {
        this.advMode = advMode;
    }

    public boolean isAdvMode() {
        return advMode;
    }
}

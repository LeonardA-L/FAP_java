package fap_java;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

public class ScoreBar {
    
    private int totalTakable;
    private Game game;
    
    public ScoreBar(Game game) {
        super();
        this.game = game;
        totalTakable = game.getMap().getTakableCells().size();
    }
    
    public void computeScores(){
        ArrayList<Team> teams = game.getTeams();
        for(int i =0;i<teams.size();i++){
            Team te = teams.get(i);
            int score = te.getScore();
            score+=(te.getNCells()-1)*Params.rPoints;
            te.setScore(score);
        }
        //Check victory
        Team winner = game.testVictory();
        if(winner != null){
            game.endGame(winner);
        }
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        //g.drawString(""+totalTakable, 20, 10);
        int x=0;
        int notTaken = computeNotTaken();
        ArrayList<Team> teams = game.getTeams();
        int nTeams = teams.size();
        
        for(int i =0;i<teams.size();i++){
           Team te = teams.get(i);
            int score = te.getScore();
            int nCells = te.getNCells();

            double percentCell = ((double)nCells)/totalTakable;
            g.setColor(te.getColor());
            g.fillRect(x, 0, (int)(game.getDisplayer().getRWidth()*(double)percentCell), 30);
            g.setColor(Color.white);
            g.drawString(""+score, x+5, 10);
            x += (int)(game.getDisplayer().getRWidth()*(double)percentCell);

            
            
            if(i == (nTeams/2-1) && notTaken >=1){
                 percentCell = ((double)notTaken)/totalTakable;
                g.setColor(Color.GRAY);
                g.fillRect(x, 0, (int)(game.getDisplayer().getRWidth()*(double)percentCell), 30);
                x += (int)(game.getDisplayer().getRWidth()*(double)percentCell);
                g.setColor(Color.white);
                g.drawString(""+notTaken, x, 10);
            }
        }
        
        //Display skillTimes
        ArrayList<Player> players = game.getPlayers();
        for(int i=0;i<players.size();i++){
            Player p = players.get(i);
            g.setColor(p.getColor());
            //this.getGame().getThread().getCount() - this.getLastSkill() >= this.getSkillTime()
            int skillTime = (p.getSkillTime() - (game.getThread().getCount() - p.getLastSkill()))/1000 -1;
            if(skillTime<0){
                skillTime = 0;
            }
            g.drawString(""+skillTime, 15, i*30+100);
        }
    }
    
    public int computeNotTaken(){
        int notTaken = totalTakable;
        ArrayList<Team> teams = game.getTeams();
            for(int j =0;j<teams.size();j++){
                Team te = teams.get(j);
                notTaken-= te.getNCells();
            }
        return notTaken;
    }
}

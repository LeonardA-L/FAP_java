package fap_java;

import java.awt.Color;
import java.awt.Graphics;

public class Arrow extends Element{
    
    private Cell current;
    
    private int x;
    private int y;
    private int course;
    private Game game;
    private double angle;
    
    private int offsetY;
    private int offsetX;
    
    public Arrow(Cell c, int course, Game game) {
        this.course = course;
        this.current = c;
        this.game = game;
        this.x = CMap.giveTalePosition(c.getI(), c.getJ())[0]+(CMap.TW/2);
        this.y = CMap.giveTalePosition(c.getI(), c.getJ())[1] + CMap.OFFMAP+(CMap.TH/2);
        game.addObject(this);
        
        this.initConstants();
    }
    
    public void paintComponent(Graphics g) {
        //int offsetY;
        offsetY = 0;
        
        g.setColor(Color.BLACK);
        g.fillRect(x, y+offsetY, 4, 4);
    }

    public String toString() {
        return "An arrow";
    }
    
    public void effect(){
        this.y -= Params.arrowSpeed*Math.cos(this.angle);
        this.x += Params.arrowSpeed*Math.sin(this.angle);
        
        computeCell();
    }
    
    public void computeCell(){
        int[] tab = CMap.givePositionTale(x, y-(CMap.TH/2));
        Cell c = game.getMap().getCell(tab);
        if(c!=null && c != current){
            current = c;
        }
    }
    
    public void initConstants(){
        double approxAngle = -0.85832;
        switch(course){
        case 0: //TL
            offsetY = 0;
            offsetX = 0;
            angle = approxAngle; // In rad, approximation with Maple
            break;
            case 1: //TR
            angle = -approxAngle;
            offsetX = 0;
            offsetY = 0;
                break;
            case 2: //R
            angle = Math.PI/2;
            offsetY = 0;
            offsetX = 0;
                break;
            case 3: //BR    
            angle = (Math.PI)+approxAngle;
            offsetY = 0;
            offsetX = 0;
                break;
            case 4: //BL
            angle = (Math.PI)-approxAngle;
            offsetY = 0;
            offsetX = 0;
                break;
            case 5: //L
            angle = -Math.PI/2;
            offsetY = 0;
            offsetX = 0;
                break;
        default:    //TL
            angle = approxAngle; // In rad, approximation with Maple
            offsetY = 0;
            offsetX = 0;
            break;
        }
    }
}
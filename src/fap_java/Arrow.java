package fap_java;

import java.awt.Color;
import java.awt.Graphics;

public class Arrow extends Element{
    
    private Cell current;
    
    private int x;
    private int y;
    private int course;
    private Game game;
    
    public Arrow(Cell c, int course, Game game) {
        this.course = course;
        this.current = c;
        this.game = game;
        this.x = CMap.giveTalePosition(c.getI(), c.getJ())[0]+(CMap.TW/2);
        this.y = CMap.giveTalePosition(c.getI(), c.getJ())[1] + CMap.OFFMAP+(CMap.TH/2);
    }
    
    public void paintComponent(Graphics g) {
        int offsetY;
        switch(course){
        case 0:
            offsetY = 5;
            break;
            case 1:
            offsetY = 5;
                break;
            case 2:
            offsetY = 5;
                break;
            case 3:
            offsetY = 5;
                break;
            case 4:
            offsetY = 5;
                break;
            case 5:
            offsetY = 5;
                break;
        default:
            offsetY = 5;
            break;
        }
        
        g.setColor(Color.BLACK);
        g.fillRect(x, y+offsetY, 4, 4);
    }

    public String toString() {
        return "An arrow";
    }
    
    public void displacement(){
        double approxAngle = -0.85832;
                                /*if(ori == "l"){
                                        angle = -Math.PI/2;
                                }
                                else if(ori == "r"){
                                        angle = Math.PI/2;
                                        arro.gotoAndStop(2);
                                }
                                else if(ori == "tl"){
                                        angle = approxAngle; // In rad, approximation with Maple
                                }
                                else if(ori=="br"){
                                        angle = (Math.PI)+approxAngle; // In rad, approximation with Maple
                                        arro.gotoAndStop(2);
                                }
                                else if(ori == "tr"){
                                        angle = -approxAngle; // In rad, approximation with Maple
                                        arro.gotoAndStop(2);
                                }
                                else if(ori == "bl"){
                                        angle = (Math.PI)-approxAngle; // In rad, approximation with Maple
                                        arro.gotoAndStop(2);
                                }
                                else{
                                        
                                }*/
        switch(course){
            case 0:
                break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            default:
                break;
        }
    }
}

package characters;

import fap_java.Cell;
import fap_java.Game;
import fap_java.Params;
import fap_java.Player;

import java.util.ArrayList;

public class Knight extends Player{
    
    public Knight(int id, Cell c, Game game) {
        super(id, c, game, 1);
        this.setSkillTime(10);
    }

    public void getSkill() {
        int ts = this.getGame().getThread().getCount() - this.getLastSkill();
        if (ts >= this.getSkillTime()) {
            System.out.println("*BANG*");
            //Send Skill :
                                    ArrayList<Cell> neighborHoodList = this.getGame().getMap().surroundingCells(this.getCurrent());
                                    for (int i =0; i<neighborHoodList.size();i++) {
                                            Cell c = neighborHoodList.get(i);
                                            if (c != null && c.getType()==1 && c.getOwner()!=this) {
                                                    // if he kills
                                                    /*
                                                    myMap[vi][vj] = stick.id;
                                                    healthPoints[1000*vi+vj] = new Array(stick.id, initHP);
                                                    */
                                                    //if he hurts
                                                    int dammage = (int)(Params.warriorDammage*Math.pow(ts,2));
                                                    //System.out.println(dammage);
                                                    c.setHp(c.getHp()-dammage);
                                                    if(c != null && c.getType() == 1 && ((c.getHp() <=0 && c.getOwner() != null) || (c.getOwner() == null && dammage>=90))){
                                                            c.setOwner(this);
                                                            c.setHp(this.getInitHP());
                                                    }
                                            }
                                    }
                                    // Add animation
                                  //  attachAnimation("sKnight", stick._x, stick._y);
            
            this.setLastSkill(this.getGame().getThread().getCount());
        }
    }
}
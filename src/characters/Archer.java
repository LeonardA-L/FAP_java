package characters;

import fap_java.Cell;
import fap_java.Game;
import fap_java.Player;

public class Archer extends Player{
    public Archer(int id, Cell c, Game game) {
        super(id, c, game, 5);
    }

    public void getSkill() {
        if (this.getGame().getThread().getCount() - this.getLastSkill() >= this.getSkillTime()) {
            this.setLastSkill(this.getGame().getThread().getCount());
            System.out.println("*BANG*");
            //Send Skill :
        }
    }
    }

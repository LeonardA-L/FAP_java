package npcs.actions;

import fap_java.Game;
import fap_java.NPC;
import fap_java.Tools;

import gui.GameSave;

public class ASetMapValue implements Action {
    @SuppressWarnings("compatibility:-5854713175065024116")
    private static final long serialVersionUID = 8646952569855326525L;
    private String mapHash;
    private int newValue;
    
    private Action next;
    private Action origNext;
    public ASetMapValue(String mapHash, int newValue, Action next){
        super();
        this.mapHash = mapHash;
        this.newValue = newValue;
        this.next = next;
        this.origNext = next;
    }

    public void execute(NPC whoLaunches) {
        //Load datas
        GameSave gameSave = Tools.loadGame();
        //Set datas
        gameSave.getMapValues().put(mapHash, newValue);
        
        //Save datas
        Tools.saveGame(gameSave);
        
        //Loop
        whoLaunches.gotoNextAction();
        whoLaunches.execute();
        
    }

    public void reinit() {
        next = origNext;
    }
    
    public void setNext(Action next) {
        this.next = next;
    }

    public Action getNext() {
        return next;
    }

    public void setOrigNext(Action origNext) {
        this.origNext = origNext;
    }

    public Action getOrigNext() {
        return origNext;
    }

    public void setTransientValues(Game g) {
    }
}
import java.util.*;
public class MonsterParent {
    private int defenseValue;
    private String type;
    private String fighterName;
    private int speed;
    private ArrayList<Move> moveSet = new ArrayList<>();
    public MonsterParent(int defenseValue, String type, String name, int speed){
        this.defenseValue = defenseValue;
        this.type = type;
        this.fighterName = name;
        this.speed = speed;
    }

    public void createMoveSet(){}

    public int getSpeed() {
        return speed;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public String getType() {
        return type;
    }

    public String getFighterName() {
        return fighterName;
    }

    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

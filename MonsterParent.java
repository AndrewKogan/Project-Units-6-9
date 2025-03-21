import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MonsterParent {
    int defenseValue;
    String type;
    String fighterName;
    public MonsterParent(int defenseValue, String type, String name){
        this.defenseValue = defenseValue;
        this.type = type;
        this.fighterName = name;
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

    public boolean typeAdvantage(MonsterParent opponent){
        if(opponent.type.equalsIgnoreCase("water") && type.equalsIgnoreCase("grass")){
            return true;
        }
        if(opponent.type.equalsIgnoreCase("fire") && type.equalsIgnoreCase("water")){
            return true;
        }
        if(opponent.type.equalsIgnoreCase("grass") && type.equalsIgnoreCase("fire")){
            return true;
        }
        return false;
    }
}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MonsterParent {
    private int defenseValue;
    private String type;
    private String fighterName;
    public MonsterParent(int defenseValue, String type, String name, int speedValue){
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
}

public class MonsterParent {
    private int defenseValue;
    private String type;
    private String fighterName;
    private int atk;
    public MonsterParent(int defenseValue, String type, String name, int atk){
        this.defenseValue = defenseValue;
        this.type = type;
        this.fighterName = name;
        this.atk = atk;
    }

    public int getAttack() {
        return atk;
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

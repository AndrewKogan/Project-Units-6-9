public class MonsterParent {
    private int defenseValue;
    private String type;
    private String fighterName;
    private int attack;


    // Define four possible types
    public static final String FIRE = "Fire";
    public static final String WATER = "Water";
    public static final String EARTH = "Earth";
    public static final String AIR = "Air";

    public MonsterParent(int defenseValue, String type, String name, int attack) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
        this.defenseValue = defenseValue;
        this.type = type;
        this.fighterName = name;
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    private boolean isValidType(String type) {
        return type.equals(FIRE) || type.equals(WATER) || type.equals(EARTH) || type.equals(AIR);
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

    @Override
    public String toString() {
        return fighterName + " (Type: " + type + ", Defense: " + defenseValue + ")";
    }
}

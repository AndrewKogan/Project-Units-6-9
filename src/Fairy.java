import java.util.*;
public class Fairy extends MonsterParent{
    private static ArrayList<Move> moveSet;

    public Fairy(int defenseValue, String name, int speedValue){
        super(defenseValue, "Fairy", name, speedValue);
        moveSet = new ArrayList<>();
        moveSet.add(new Move("Lullaby",0, "fairy", 60, "hp+", 3));
        moveSet.add(new Move("Poisoned Scales",0, "fairy", 60, "attack+", 5));
        moveSet.add(new Move("Wing Smash",40, "fairy", 100, "none", 15));
        moveSet.add(new Move("Moonblast",60, "fairy", 100, "none", 15));
        moveSet.add(new Move("Blessing",0, "fairy", 100, "hp+", 3));
        moveSet.add(new Move("Star Song",0, "fairy", 100, "attack+", 10));
        moveSet.add(new Move("Sparkle",60, "fairy", 100, "none", 10));
        moveSet.add(new Move("Flower Nuke",80, "fairy", 70, "none", 10));
    }

    public static ArrayList<Move> fairyGetMoveSet(){
        return moveSet;
    }
}

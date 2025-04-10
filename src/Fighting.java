import java.util.*;
public class Fighting extends MonsterParent{
    private static ArrayList<Move> moveSet;

    public Fighting(int defenseValue, String name, int speedValue){
        super(defenseValue, "Fighting", name, speedValue);
        moveSet = new ArrayList<>();
        moveSet.add(new Move("Warm Up",20, "fighting", 100, "attack+", 5));
        moveSet.add(new Move("Fist Launch",80, "fighting", 100, "none", 15));
        moveSet.add(new Move("Inspiration",0, "fighting", 100, "attack+", 10));
        moveSet.add(new Move("Bear Hug",50, "fighting", 100, "none", 10));
        moveSet.add(new Move("Rocket Flurry",80, "fighting", 100, "none", 5));
        moveSet.add(new Move("Final Stand",120, "fighting", 50, "none", 5));
    }

    public static ArrayList<Move> fightingGetMoveSet(){
        return moveSet;
    }
}

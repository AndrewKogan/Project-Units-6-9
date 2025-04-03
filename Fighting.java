import java.util.*;
public class Fighting extends MonsterParent{
    private static ArrayList<Move> moveSet = new ArrayList<>();

    public Fighting(int defenseValue, String name, int speedValue){
        super(defenseValue, "Fighting", name, speedValue);
        moveSet.add(new Move("Warm Up",20, "fighting", 100, "attack+"));
        moveSet.add(new Move("Fist Launch",60, "fighting", 100, "none"));
        moveSet.add(new Move("Inspiration",0, "fighting", 100, "attack+"));
        moveSet.add(new Move("Bear Hug",50, "fighting", 100, "speed-"));
        moveSet.add(new Move("Rocket Flurry",80, "fighting", 100, "none"));
        moveSet.add(new Move("Final Stand",100, "fighting", 50, "none"));
    }

    public static ArrayList<Move> fightingGetMoveSet(){
        return moveSet;
    }
}

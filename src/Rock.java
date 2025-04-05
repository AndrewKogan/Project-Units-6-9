import java.util.*;
public class Rock extends MonsterParent{
    private static ArrayList<Move> moveSet = new ArrayList<>();

    public Rock(int defenseValue, String name, int speedValue){
        super(defenseValue, "Rock", name, speedValue);
        moveSet.add(new Move("Warm Up",20, "rock", 100, "attack+", 5));
        moveSet.add(new Move("Fist Launch",60, "rock", 100, "none", 15));
        moveSet.add(new Move("Inspiration",0, "rock", 100, "attack+", 10));
        moveSet.add(new Move("Bear Hug",50, "rock", 100, "speed-", 10));
        moveSet.add(new Move("Rocket Flurry",80, "rock", 100, "none", 5));
        moveSet.add(new Move("Final Stand",100, "rock", 50, "none", 5));
    }

    public static ArrayList<Move> fightingGetMoveSet(){
        return moveSet;
    }
}

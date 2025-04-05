import java.util.*;
public class Ice extends MonsterParent{
    private static ArrayList<Move> moveSet = new ArrayList<>();

    public Ice(int defenseValue, String name, int speedValue){
        super(defenseValue, "Ice", name, speedValue);
        moveSet.add(new Move("Warm Up",20, "ice", 100, "attack+", 5));
        moveSet.add(new Move("Fist Launch",60, "ice", 100, "none", 15));
        moveSet.add(new Move("Inspiration",0, "ice", 100, "attack+", 10));
        moveSet.add(new Move("Bear Hug",50, "ice", 100, "speed-", 10));
        moveSet.add(new Move("Rocket Flurry",80, "ice", 100, "none", 5));
        moveSet.add(new Move("Final Stand",100, "ice", 50, "none", 5));
    }

    public static ArrayList<Move> iceGetMoveSet(){
        return moveSet;
    }
}

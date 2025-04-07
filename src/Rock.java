
import java.util.*;
public class Rock extends MonsterParent{
    private static ArrayList<Move> moveSet = new ArrayList<>();

    public Rock(int defenseValue, String name, int speedValue){
        super(defenseValue, "Rock", name, speedValue);
        moveSet.add(new Move("Crystalize",0, "rock", 100, "hp+", 15));
        moveSet.add(new Move("Rock Barrage",60, "rock", 100, "none", 15));
        moveSet.add(new Move("Rock Mastery",20, "rock", 100, "attack+", 10));
        moveSet.add(new Move("Rock Catapult",50, "rock", 100, "none", 10));
        moveSet.add(new Move("Boulder Strike",80, "rock", 100, "none", 5));
        moveSet.add(new Move("Tectonic Smash",80, "rock", 100, "none", 5));
    }

    public static ArrayList<Move> rockGetMoveSet(){
        return moveSet;
    }
}

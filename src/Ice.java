package src;

import java.util.*;
public class Ice extends MonsterParent{
    private static ArrayList<Move> moveSet = new ArrayList<>();

    public Ice(int defenseValue, String name, int speedValue){
        super(defenseValue, "Ice", name, speedValue);
        moveSet.add(new Move("Cool Down",0, "ice", 80, "freeze", 10));
        moveSet.add(new Move("Icicle Blast",60, "ice", 100, "none", 15));
        moveSet.add(new Move("Snowball Fight",50, "ice", 100, "speed-", 15));
        moveSet.add(new Move("Blizzard",70, "ice", 50, "freeze", 10));
        moveSet.add(new Move("Snow Globe",30, "ice", 100, "attack+", 5));
        moveSet.add(new Move("Cold Front",80, "ice", 90, "none", 5));
    }

    public static ArrayList<Move> iceGetMoveSet(){
        return moveSet;
    }
}

import java.util.*;
public class Fairy extends MonsterParent{
    private ArrayList<Move> moveSet = new ArrayList<>();

    public Fairy(int defenseValue, String name){
        super(defenseValue, "Fairy", name);
        moveSet.add(new Move("lullaby",0, "fairy", 60, "sleep"));
        moveSet.add(new Move("",40, "fairy", 100, "none"));
    }
}

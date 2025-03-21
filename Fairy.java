import java.util.*;
public class Fairy extends MonsterParent{
    ArrayList<Move> moveSet = new ArrayList<>();
    public Fairy(int defenseValue, String name){
        super(defenseValue, "Fairy", name);
    }
}

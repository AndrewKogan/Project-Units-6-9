import java.util.ArrayList;
import java.util.Collections;

public class James extends Fighting{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public James(){
        super(500, "James", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = fightingGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 3; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Ora Ora", 100, "fighting", 100, "none", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

import java.util.ArrayList;
import java.util.Collections;

public class Steven extends Fighting{
    private ArrayList<Move> moveSet;
    public Steven(){
        super(650, "Steven", 50);
    }
    private ArrayList<Move> duo;
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
        moveSet.add(new Move("Double Kick", 70, "fighting", 100, "none", 10));

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

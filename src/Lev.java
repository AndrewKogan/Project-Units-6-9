import java.util.ArrayList;
import java.util.Collections;

public class Lev extends Rock{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public Lev(){
        super(750, "Lev", 40);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = rockGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 3; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Rock Fling", 60, "rock", 100, "speed+", 10));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

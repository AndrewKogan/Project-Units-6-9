import java.util.ArrayList;
import java.util.Collections;

public class Kyle extends Ice{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public Kyle(){
        super(800, "Kyle", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = iceGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 3; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Snow Storm", 100, "ice", 30, "freeze", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

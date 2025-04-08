import java.util.ArrayList;
import java.util.Collections;

public class Andy extends Rock{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public Andy(){
        super(700, "Andy", 40);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = rockGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 2; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Cosmic Shockwave", 100, "rock", 80, "none", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

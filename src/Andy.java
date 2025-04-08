import java.util.ArrayList;
import java.util.Collections;

public class Andy extends Rock{
    private ArrayList<Move> moveSet;
    public Andy(){
        super(700, "Andy", 40);
    }
    @Override
    public void createMoveSet(){
        ArrayList<Move> rockMoveSet  = rockGetMoveSet();
        ArrayList<Move> iceMoveSet  = Ice.iceGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * iceMoveSet.size());
                moveSet.add(iceMoveSet.get(randomIndex));
                iceMoveSet.remove(randomIndex);
            } else {
                int randomIndex = (int) (Math.random() * rockMoveSet.size());
                moveSet.add(rockMoveSet.get(randomIndex));
                rockMoveSet.remove(randomIndex);
            }
        }
        moveSet.add(new Move("Cosmic Shockwave", 100, "rock", 80, "none", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

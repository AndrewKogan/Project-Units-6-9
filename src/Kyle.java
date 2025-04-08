import java.util.ArrayList;
import java.util.Collections;

public class Kyle extends Ice{
    private ArrayList<Move> moveSet;
    public Kyle(){
        super(800, "Kyle", 50);
    }
    @Override
    public void createMoveSet(){
        ArrayList<Move> iceMoveSet  = iceGetMoveSet();
        ArrayList<Move> rockMoveSet  = Rock.rockGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * rockMoveSet.size());
                moveSet.add(rockMoveSet.remove(randomIndex));
            } else {
                int randomIndex = (int) (Math.random() * iceMoveSet.size());
                moveSet.add(iceMoveSet.remove(randomIndex));
            }
        }
        moveSet.add(new Move("Snow Storm", 100, "ice", 100, "none", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

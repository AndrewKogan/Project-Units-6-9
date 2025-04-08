import java.util.ArrayList;
import java.util.Collections;

public class Benjamin extends Fairy{
    private ArrayList<Move> moveSet;
    public Benjamin(){
        super(600, "Benjamin", 70);
    }

    @Override
    public void createMoveSet(){
        ArrayList<Move> fairyMoveSet  = fairyGetMoveSet();
        ArrayList<Move> iceMoveSet  = Ice.iceGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * iceMoveSet.size());
                moveSet.add(iceMoveSet.get(randomIndex));
                iceMoveSet.remove(randomIndex);
            } else {
                int randomIndex = (int) (Math.random() * fairyMoveSet.size());
                moveSet.add(fairyMoveSet.get(randomIndex));
                fairyMoveSet.remove(randomIndex);
            }
        }
        moveSet.add(new Move("Atmospheric Glide", 70, "fairy", 100, "none", 10));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

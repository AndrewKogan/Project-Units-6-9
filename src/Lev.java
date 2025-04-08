import java.util.ArrayList;
import java.util.Collections;

public class Lev extends Rock{
    private ArrayList<Move> moveSet;
    public Lev(){
        super(750, "Lev", 40);
    }
    @Override
    public void createMoveSet(){
        ArrayList<Move> rockMoveSet  = rockGetMoveSet();
        ArrayList<Move> fairyMoveSet  = Fairy.fairyGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * fairyMoveSet.size());
                moveSet.add(fairyMoveSet.get(randomIndex));
                fairyMoveSet.remove(randomIndex);
            } else {
                int randomIndex = (int) (Math.random() * rockMoveSet.size());
                moveSet.add(rockMoveSet.get(randomIndex));
                rockMoveSet.remove(randomIndex);
            }
        }
        moveSet.add(new Move("Rock Fling", 60, "rock", 100, "speed+", 10));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

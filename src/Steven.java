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
        ArrayList<Move> fightingMoveSet  = fightingGetMoveSet();
        ArrayList<Move> fairyMoveSet  = Fairy.fairyGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * fairyMoveSet.size());
                moveSet.add(fairyMoveSet.remove(randomIndex));
            } else {
                int randomIndex = (int) (Math.random() * fightingMoveSet.size());
                moveSet.add(fightingMoveSet.remove(randomIndex));
            }
        }
        moveSet.add(new Move("Double Kick", 70, "fighting", 100, "none", 10));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

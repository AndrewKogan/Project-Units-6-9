import java.util.ArrayList;
import java.util.Collections;

public class James extends Fighting{
    private ArrayList<Move> moveSet;
    public James(){
        super(500, "James", 50);
    }
    @Override
    public void createMoveSet(){
        ArrayList<Move> fightingMoveSet  = fightingGetMoveSet();
        ArrayList<Move> rockMoveSet  = Rock.rockGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * rockMoveSet.size());
                moveSet.add(rockMoveSet.remove(randomIndex));
            } else {
                int randomIndex = (int) (Math.random() * fightingMoveSet.size());
                moveSet.add(fightingMoveSet.remove(randomIndex));
            }
        }
        moveSet.add(new Move("Ora Ora", 100, "fighting", 100, "none", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

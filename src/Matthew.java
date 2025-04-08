import java.util.*;
public class Matthew extends Fairy {
    private ArrayList<Move> moveSet;

    public Matthew() {
        super(550, "Matthew", 60);
    }

    @Override
    public void createMoveSet(){
        ArrayList<Move> fairyMoveSet  = fairyGetMoveSet();
        ArrayList<Move> fightingMoveSet  = Fighting.fightingGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * fightingMoveSet.size());
                moveSet.add(fightingMoveSet.get(randomIndex));
                fightingMoveSet.remove(randomIndex);
            } else {
                int randomIndex = (int) (Math.random() * fairyMoveSet.size());
                moveSet.add(fairyMoveSet.get(randomIndex));
                fairyMoveSet.remove(randomIndex);
            }
        }
        moveSet.add(new Move("Final Ultra Mega Sparkles Unicorn Rainbow Blast Supreme X", 90, "fairy", 100, "none", 5));
    }

    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}
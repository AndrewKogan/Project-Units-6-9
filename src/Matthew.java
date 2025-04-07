import java.util.*;
public class Matthew extends Fairy {
    private ArrayList<Move> moveSet;

    public Matthew() {
        super(550, "Matthew", 60);
    }

    @Override
    public void createMoveSet() {
        moveSet = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Fighting.fightingGetMoveSet().get((int) (Math.random() * Fighting.fightingGetMoveSet().size())));
            } else {
                moveSet.add(fairyGetMoveSet().get((int) (Math.random() * fairyGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Final Ultra Mega Sparkles Unicorn Rainbow Blast Supreme X", 90, "fairy", 100, "none", 5));
        for (int i = 0; i < 2; i++){
            for(int j = i + 1; j < 3; j++){
                if(moveSet.get(i).equals(moveSet.get(j))){
                    createMoveSet();
                }
            }
        }
        if(moveSet.get(0).equals(moveSet.get(2))){
            createMoveSet();
        }
    }

    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}
import java.util.*;
public class Matthew extends Fairy {
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;

    public Matthew() {
        super(550, "Matthew", 60);
    }

    @Override
    public void createMoveSet() {
        moveSet = new ArrayList<>();
        duo = fairyGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 3; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Final Ultra Mega Sparkles Unicorn Rainbow Blast Supreme X", 90, "fairy", 100, "none", 5));

    }

    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}
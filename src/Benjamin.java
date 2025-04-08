import java.util.ArrayList;
import java.util.Collections;

public class Benjamin extends Fairy{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public Benjamin(){
        super(600, "Benjamin", 70);
    }

    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = fairyGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 2; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            moveSet.add(duo.get(i));
        }
        moveSet.add(new Move("Atmospheric Glide", 70, "fairy", 100, "none", 10));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

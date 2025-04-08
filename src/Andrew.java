import java.util.ArrayList;
import java.util.Collections;

public class Andrew extends Ice{
    private ArrayList<Move> moveSet;
    private ArrayList<Move> duo;
    public Andrew(){
        super(700, "Andrew", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        duo = iceGetMoveSet();
        for(int i = 0; i < duo.size()/2 + 3; i++){
            duo.remove(i);
        }
        Collections.shuffle(duo);
        for(int i = 0; i < 3 && i < duo.size(); i++){
            if(!moveSet.contains(duo.get(i))){
                moveSet.add(duo.get(i));
            }
        }
        moveSet.add(new Move("Frozen World", 0, "ice", 80, "attack+", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

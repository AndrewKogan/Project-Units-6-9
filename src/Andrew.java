import java.util.ArrayList;
public class Andrew extends Ice{
    private ArrayList<Move> moveSet;
    public Andrew(){
        super(700, "Andrew", 50);
    }
    @Override
    public void createMoveSet(){
        ArrayList<Move> iceMoveSet  = iceGetMoveSet();
        ArrayList<Move> fightingMoveSet  = Fighting.fightingGetMoveSet();
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                int randomIndex = (int) (Math.random() * Fighting.fightingGetMoveSet().size());
                moveSet.add(fightingMoveSet.remove(randomIndex));
            } else {
                int randomIndex = (int) (Math.random() * iceGetMoveSet().size());
                moveSet.add(iceMoveSet.remove(randomIndex));
            }
        }
        moveSet.add(new Move("Frozen World", 0, "ice", 80, "attack+", 5));
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}
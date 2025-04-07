import java.util.ArrayList;
public class Andrew extends Ice{
    private ArrayList<Move> moveSet;
    public Andrew(){
        super(700, "Andrew", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Fighting.fightingGetMoveSet().get((int) (Math.random() * Fighting.fightingGetMoveSet().size())));
            } else {
                moveSet.add(iceGetMoveSet().get((int) (Math.random() * iceGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Frozen World", 0, "ice", 80, "attack+", 5));
        for (int i = 0; i < 2; i++){
            for(int j = i + 1; j < 3; j++){
                if(moveSet.get(i).equals(moveSet.get(j))){
                    createMoveSet();
                }
            }
        }
    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

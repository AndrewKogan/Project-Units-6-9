import java.util.ArrayList;

public class Lev extends Rock{
    private ArrayList<Move> moveSet;
    public Lev(){
        super(750, "Lev", 40);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Fairy.fairyGetMoveSet().get((int) (Math.random() * Fairy.fairyGetMoveSet().size())));
            } else {
                moveSet.add(rockGetMoveSet().get((int) (Math.random() * rockGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Rock Fling", 60, "rock", 100, "speed+", 10));
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

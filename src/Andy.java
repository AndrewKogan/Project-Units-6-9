import java.util.ArrayList;

public class Andy extends Rock{
    private ArrayList<Move> moveSet;
    public Andy(){
        super(700, "Andy", 40);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Ice.iceGetMoveSet().get((int) (Math.random() * Ice.iceGetMoveSet().size())));
            } else {
                moveSet.add(rockGetMoveSet().get((int) (Math.random() * rockGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Cosmic Shockwave", 100, "rock", 80, "none", 5));
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

import java.util.ArrayList;

public class Benjamin extends Fairy{
    private ArrayList<Move> moveSet;
    public Benjamin(){
        super(600, "Benjamin", 70);
    }

    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Ice.iceGetMoveSet().get((int) (Math.random() * Ice.iceGetMoveSet().size())));
            } else {
                moveSet.add(fairyGetMoveSet().get((int) (Math.random() * fairyGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Atmospheric Glide", 70, "fairy", 100, "none", 10));
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

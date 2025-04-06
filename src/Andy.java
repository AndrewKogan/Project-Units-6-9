import java.util.ArrayList;

public class Andy extends Rock{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Andy(){
        super(700, "Andy", 40);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Ice.iceGetMoveSet().get((int) (Math.random() * Ice.iceGetMoveSet().size())));
            }
            else{
                moveSet.add(rockGetMoveSet().get((int) (Math.random() * rockGetMoveSet().size())));
            }
            moveSet.add(new Move("Cosmic Shockwave", 100, "rock", 80, "none", 5));
        }

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

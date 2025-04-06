import java.util.ArrayList;
public class Kyle extends Ice{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Kyle(){
        super(800, "Kyle", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Rock.rockGetMoveSet().get((int) (Math.random() * Rock.rockGetMoveSet().size())));
            }
            else{
                moveSet.add(iceGetMoveSet().get((int) (Math.random() * iceGetMoveSet().size())));
            }
            moveSet.add(new Move("Snow Storm", 100, "ice", 30, "freeze", 5));
        }

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

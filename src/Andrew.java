import java.util.ArrayList;
public class Andrew extends Ice{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Andrew(){
        super(700, "Andrew", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Fighting.fightingGetMoveSet().get((int) (Math.random() * Fighting.fightingGetMoveSet().size())));
            }
            else{
                moveSet.add(iceGetMoveSet().get((int) (Math.random() * iceGetMoveSet().size())));
            }
            moveSet.add(new Move("Frozen World", 0, "ice", 80, "freeze", 5));
        }

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

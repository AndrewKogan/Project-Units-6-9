import java.util.ArrayList;
public class James extends Fighting{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public James(){
        super(500, "James", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Rock.rockGetMoveSet().get((int) (Math.random() * Rock.rockGetMoveSet().size())));
            }
            else{
                moveSet.add(fightingGetMoveSet().get((int) (Math.random() * fightingGetMoveSet().size())));
            }
            moveSet.add(new Move("Ora Ora", 100, "fighting", 100, "none", 5));
        }

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

import java.util.ArrayList;
public class James extends Fighting{
    private ArrayList<Move> moveSet;
    public James(){
        super(500, "James", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Rock.rockGetMoveSet().get((int) (Math.random() * Rock.rockGetMoveSet().size())));
            } else {
                moveSet.add(fightingGetMoveSet().get((int) (Math.random() * fightingGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Ora Ora", 100, "fighting", 100, "none", 5));
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

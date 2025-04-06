import java.util.ArrayList;
public class Steven extends Fighting{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Steven(){
        super(650, "Steven", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Fairy.fairyGetMoveSet().get((int) (Math.random() * Fairy.fairyGetMoveSet().size())));
            }
            else{
                moveSet.add(fightingGetMoveSet().get((int) (Math.random() * fightingGetMoveSet().size())));
            }
            moveSet.add(new Move("Double Kick", 70, "fighting", 100, "none", 10));
        }

    }
    @Override
    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

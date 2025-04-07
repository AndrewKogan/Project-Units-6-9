import java.util.ArrayList;
public class Steven extends Fighting{
    private ArrayList<Move> moveSet;
    public Steven(){
        super(650, "Steven", 50);
    }
    @Override
    public void createMoveSet(){
        moveSet = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            double random = Math.random();
            if (random <= .33) {
                moveSet.add(Fairy.fairyGetMoveSet().get((int) (Math.random() * Fairy.fairyGetMoveSet().size())));
            } else {
                moveSet.add(fightingGetMoveSet().get((int) (Math.random() * fightingGetMoveSet().size())));
            }
        }
        moveSet.add(new Move("Double Kick", 70, "fighting", 100, "none", 10));
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

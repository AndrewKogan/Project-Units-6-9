import java.util.ArrayList;

public class Matthew extends Fairy{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Matthew(){
        super(500, "Matthew", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(Fighting.fightingGetMoveSet().get((int) (Math.random() * Fighting.fightingGetMoveSet().size())));
            }
            else{
                moveSet.add(fairyGetMoveSet().get((int) (Math.random() * fairyGetMoveSet().size())));
            }
            moveSet.add(new Move("Final Ultra Mega Sparkles Unicorn Rainbow Blast Supreme X", 90, "fairy", 100, "none", 5));
        }

    }

    public ArrayList<Move> getMoveSet() {
        return moveSet;
    }
}

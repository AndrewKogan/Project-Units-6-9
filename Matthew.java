import java.util.ArrayList;

public class Matthew extends Fairy{
    private ArrayList<Move> moveSet = new ArrayList<>();
    public Matthew(){
        super(100, "Mattypoo", 50);
    }
    public void createMoveSet(){
        for(int i = 0; i < 3;i++){
            double random = Math.random();
            if(random<=.33){
                moveSet.add(fightingGetMoveSet().get((int) (Math.random() * fightingGetMoveSet().size())));
            }
            else{
                moveSet.add(fairyGetMoveSet().get((int) (Math.random() * fairyGetMoveSet().size())));
            }
        }

    }

}

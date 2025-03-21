public class Move {
    int attackValue;
    String type;
    int accuracy;
    public Move(int attackValue, String type, int accuracy){
        this.type = type;
        this.attackValue = attackValue;
        this.accuracy = accuracy;
    }
    public int getAttackValue(){
        return attackValue;
    }

    public String getType() {
        return type;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

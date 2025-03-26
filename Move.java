public class Move {
    private int attackValue;
    private String type;
    private int accuracy;
    private String status;
    private String name;
    public Move(String name, int attackValue, String type, int accuracy, String status){
        this.type = type;
        this.attackValue = attackValue;
        this.accuracy = accuracy;
        this.status = status;
        this.name=name;
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

    public String getStatus(){return status;}

    public String getName(){return name;}
}

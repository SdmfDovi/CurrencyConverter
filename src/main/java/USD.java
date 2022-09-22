public class USD extends Coin{
    final double value =0.28;
    public double getValue(){
        return value;
    }
    public double calculate(double input){
        return input * getValue();
    }
}

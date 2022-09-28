package Coins;

public class USD extends Coin{
    final double value =3.52;
    public double getValue(){
        return value;
    }
    public double calculate(double input){
        return input / getValue();
    }
}

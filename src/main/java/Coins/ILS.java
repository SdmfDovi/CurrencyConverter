package Coins;

public class ILS extends Coin {
    final double value =0.28;
   public double getValue(){
       return value;
   }

    @Override
    public double calculate(double input){
        return input / getValue();
    }

}

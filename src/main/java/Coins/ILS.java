package Coins;

public class ILS extends Coin {
    final double value =3.52;
   public double getValue(){
       return value;
   }

    @Override
    public double calculate(double input){
        return input * getValue();
    }

}

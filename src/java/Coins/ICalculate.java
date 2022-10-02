package Coins;

interface ICalculate {
    default double calculate(double amountToConvert) {
        return amountToConvert;
    }
}

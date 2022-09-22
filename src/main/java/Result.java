public class Result {
    private String conversionFlow;
    private double amountConverted;
    public double getAmountConverted() {
        return amountConverted;
    }

    public void setAmountConverted(double amountConverted) {
        this.amountConverted = amountConverted;
    }

    public String getConversionFlow() {
        return conversionFlow;
    }

    public void setConversionFlow(String conversionFlow) {
        this.conversionFlow = conversionFlow;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + " Amount converted: "+amountConverted+" Conversion Flow: "+conversionFlow;
    }
}

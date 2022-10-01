/**
 * Result object that gets and sets totals converted and conversionFlow
 */
public class Result {

    private String conversionFlow;
    private double amountConverted;
    private double totalAfterConversion;

    /**
     *
     * @return the totalAfterConversion
     */
    public double getTotalAfterConversion() {
        return totalAfterConversion;
    }

    /**
     * Sets the totalAfterConversion
     * @param totalAfterConversion
     */
    public void setTotalAfterConversion(double totalAfterConversion) {
        this.totalAfterConversion = totalAfterConversion;
    }

/**
 * Gets the amountConverted
 */
    public double getAmountConverted() {
        return amountConverted;
    }
    /**
     * Sets the amountConverted
     */
    public void setAmountConverted(double amountConverted) {
        this.amountConverted = amountConverted;
    }
    /**
     * Gets the conversionFlow
     */
    public String getConversionFlow() {
        return conversionFlow;
    }
    /**
     * Sets the conversionFlow based on user choice in CoinFactory
     */
    public void setConversionFlow(String conversionFlow) {
        this.conversionFlow = conversionFlow;
    }

    /**
     *Overrides the toString method to display the amount converted, conversionFlow and totalAfterConversion
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Amount converted: "+amountConverted+" Conversion Flow: "+conversionFlow+
                " Total after conversion: " +totalAfterConversion;
    }
}

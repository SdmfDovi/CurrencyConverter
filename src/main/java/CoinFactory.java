import Coins.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Application that takes input from user to convert currency
 * from Shekel to dollar,  dollar to shekel, shekel to euro.
 * @author Dovi Y
 **/
public class CoinFactory {
    private static final ArrayList <Result> transactionRecord = new ArrayList<Result>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static int coinChoice;
    public static double amountToConvert;
    private static String coinType;
    private static String convertedSymbol;
    private static String initialSymbol;
    private static final Result result=new Result();
    private static int initialVisitCounter=0;
     static Coin conversion;

    /**
     * RUns the main of the application calls on converter.
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
         converter();
    }

    /**
     * Runs through the 3 screen options in order.
     * @throws IOException
     */
    private static void converter() throws IOException{

        firstScreen();
        secondScreen();
        thirdScreen();

    }

    /**
     * Displays the options for conversion receives user input and sets Coin type accordingly
     * catches InputMismatch
     */
    private static void firstScreen() {

        String welcome = "Welcome to currency converter";
        String choose = "Please choose an option (1\\2\\3)";
        String dToS = "1. Dollars to shekels";
        String sToD = "2. Shekels to Dollars";
        String sToE = "3. Shekels to Euro";


/**
 * Display welcome message as long as initialVisitCounter is 0
 */
        while (initialVisitCounter == 0) {
                initialVisitCounter++;
                System.out.println(welcome);
        }
        int i=0;
        while (i<1) {
            System.out.println(choose);
            System.out.println(dToS);
            System.out.println(sToD);
            System.out.println(sToE);


            try {
                Scanner choice = new Scanner(System.in);
                coinChoice = choice.nextInt();

                if (coinChoice == 1) {
                    i++;
                    coinType = "ILS";
                    initialSymbol = "$";
                    convertedSymbol = "₪";
                    result.setConversionFlow(dToS.substring(3));
                } else if (coinChoice == 2) {
                    i++;
                    coinType = "USD";
                    initialSymbol = "₪";
                    convertedSymbol = "$";
                    result.setConversionFlow(sToD.substring(3));

                } else if (coinChoice == 3) {
                    i++;
                    coinType = "EUR";
                    initialSymbol = "₪";
                    convertedSymbol = "€";
                    result.setConversionFlow(sToE.substring(3));

                } else {
                    System.out.println("Invalid choice please try again");

                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input please try again");
            }
        }
    }

    /**
     * Runs through second screen getting conversion amount and setting it to Result object
     * @throws IOException
     */
    private static void secondScreen() throws  IOException{

        System.out.println("Please enter amount to convert");
        Scanner amount = new Scanner(System.in);
        result.setAmountConverted(amount.nextDouble());
        conversion = getCoinType(Coins.valueOf(coinType));
    }

    /**
     * Displays conversion amounts and conversionFlow
     * asks is users wants to continue
     * saves transaction to array
     * if 'yes' restarts converter method
     * if 'no' runs exitScreen method
     * @throws IOException
     */
    public static void  thirdScreen() throws IOException {
        //displays 3rd screed with conversion amounts and result adds both to results ArrayList
        String transaction = ("Converting " +  result.getAmountConverted() +" "+result.getConversionFlow()+ ":");
        System.out.println(transaction);
       result.setTotalAfterConversion(Double.parseDouble(df.format(conversion.calculate(result.getAmountConverted()))));
       double totalAfterConversion = result.getTotalAfterConversion();
        String total = "Total converted amount is " +convertedSymbol+ totalAfterConversion;
        System.out.println(total);

        transactionRecord.add(result);
        int i = 0;
        while (i < 1) {
            System.out.println("Would you like to make another transaction? y/n");
            Scanner anotherTransactionChoice = new Scanner(System.in);
            String choice = anotherTransactionChoice.next();

            if (choice.equalsIgnoreCase("n")) {
               i++;
               endScreen();
            }  else if (choice.equalsIgnoreCase("y")) {
              i++;
              converter();
            } else {
                System.out.println("Invalid choice, try again");
            }
        }
    }

    /**
     * Displays end Screen with thank you message and transactions that occurred.
     * sends transaction to saveTransaction method
     * @throws IOException
     */
    public static void endScreen() throws IOException {
        System.out.println("Thanks for using our currency converter!");
        System.out.println(transactionRecord.toString());
        saveTransaction(transactionRecord);

    }

    /**
     * accepts an ArrayList, saves to a file and if the file exists will open automatically
     * @param a requires ArrayList to save to file
     * @throws IOException
     */
    public static void saveTransaction(ArrayList a) throws IOException {
        //Saves and displays transactions
        String filePath = System.getProperty("user.home") + "/Desktop/TransactionResults.txt";
        Files.writeString(Paths.get(filePath),transactionRecord.toString() );
        Desktop desktop = Desktop.getDesktop();
        File record = new File(filePath);
        if(record.exists()) desktop.open(record);

    }

    /**
     * Accepts Coins object and returns specific type of coin, ILS,USD or EUR
     * @param coin
     * @return
     */
    public static Coin getCoinType(Coins coin) throws IOException {

        switch (coin){
            case ILS:
                return new ILS();

            case USD:
                 return new USD();

            case EUR:
                return new EUR();
        }

        return null;
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CoinFactory {
    private static ArrayList <String> transactionRecord = new ArrayList<String>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static int coinChoice;
    public static double amountToConvert;
    private static String coinType;
    private static String convertedSymbol;
    private static String initialSymbol;


    private static int initialVisitCounter=0;
    private static Coin conversion;
    public static void main(String[] args) throws IOException {
    converter();

    }
    private static void converter() throws IOException{
        firstScreen();
        secondScreen();
        thirdScreen();

    }
    private static void firstScreen() {
        String welcome = "Welcome to currency converter";
        String choose = "Please choose and option (1\\2)";
        String dToS = "1. Dollars to shekels";
        String stoD = "2. Shekels to Dollars";
//        if (initialVisitCounter == 0) {
//            initialVisitCounter++;
//            System.out.println(welcome);
//            System.out.println(choose);
//            System.out.println(dToS);
//            System.out.println(stoD);
//        } else {
//            System.out.println(choose);
//            System.out.println(dToS);
//            System.out.println(stoD);
//        }

        int i = 0;
        while (i < 1) {
            if (initialVisitCounter == 0) {
                initialVisitCounter++;
                System.out.println(welcome);
                System.out.println(choose);
                System.out.println(dToS);
                System.out.println(stoD);
            } else {
                System.out.println(choose);
                System.out.println(dToS);
                System.out.println(stoD);
            }
            Scanner choice = new Scanner(System.in);
            coinChoice = choice.nextInt();
            if (coinChoice == 1) {
                i++;
                coinType = "ILS";
                initialSymbol = "$";
                convertedSymbol = "₪";
            } else if (coinChoice == 2) {
                i++;
                coinType = "USD";
                initialSymbol = "₪";
                convertedSymbol = "$";
            } else {
                System.out.println("Invalid choice please try again");

            }
        }
    }
    private static void secondScreen() throws  IOException{

        System.out.println("Please enter amount to convert");
        Scanner amount = new Scanner(System.in);
        amountToConvert =amount.nextDouble();
        conversion = getCoinType(Coins.valueOf(coinType));
    }
    public static void  thirdScreen() throws IOException {
        String transaction = ("Converting " + initialSymbol+ amountToConvert + " to " + coinType +convertedSymbol+ ":");
        System.out.println(transaction);
        String total = "Total converted amount is " +convertedSymbol+ df.format(conversion.calculate(amountToConvert));
        System.out.println(total);

        transactionRecord.add(transaction + total);
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
    public static void endScreen() throws IOException {
        System.out.println("Thanks for using our currency converter!");
        System.out.println(transactionRecord.toString());
        saveTransaction(transactionRecord);
    }

    public static void saveTransaction(ArrayList a) throws IOException {
        String filePath = System.getProperty("user.home") + "/Desktop/TransactionResults.txt";
        Files.writeString(Paths.get(filePath),transactionRecord.toString() );

    }
    public static Coin getCoinType(Coins coin){

        switch (coin){
            case ILS:
                return new ILS();

            case USD:
            return new USD ();
        }

        return null;
    }
}

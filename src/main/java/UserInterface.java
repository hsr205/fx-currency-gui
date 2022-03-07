import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        currencyConverter();
    }

    public UserInterface() {
    }

    public static void currencyConverter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the one of the following options to return data");
        System.out.println("Type 1 - Get Current Rates");
        System.out.println("Type 2 - Get Simple Currency Conversion");
        System.out.println("Type 3 - Get Historical Rates");
        System.out.println("Type 4 - exit");
        System.out.print("Choice: ");
        int options = scanner.nextInt();
        switch (options) {
            case 1 -> new GetExchangeRates().getCurrentRates();
            case 2 -> new GetExchangeRates().returnConversion();
//            case 3 -> new GetExchangeRates().getHistoricExchangeRate();
            case 3 -> System.out.println("Work in Progress");
            case 4 -> System.out.println("Goodbye");
        }
    }
}

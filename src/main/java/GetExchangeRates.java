import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class GetExchangeRates {

//    public void getAllCurrencies () {
//        try {
//            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
//            String readLine = null;
//            HttpURLConnection conection = (HttpURLConnection) new URL(url).openConnection();
//            conection.setRequestMethod("GET");
//            int responseCode = conection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
//                StringBuffer response = new StringBuffer();
//                while ((readLine = inputStream.readLine()) != null) {
//                    response.append(readLine);
//                }
//                inputStream.close();
////                List<String> testList = new ArrayList<>(Arrays.asList(response.toString()
////                                                                      .substring(response.toString().indexOf("JPY"))
////                                                                      .replaceAll("[{}\"]", "")
////                                                                      .replaceAll("(:\\d*.\\d*)", "")
////                                                                      .split(",")));
//                List<String> testList = new ArrayList<>(Arrays.asList(response.toString()
//                        .substring(response.toString().indexOf("JPY"))
//                        .replaceAll("[{}\"]", "")
////                        .replaceAll("(:\\d*.\\d*)", "")
//                        .split(",")));
//
//
////                Map<Object, Object> currenciesMap = testList.stream().collect(Collectors.toMap(s -> {
////                    for(int i =0; i<=testList.size(); i++) {
////                        s=String.valueOf(i);
////                    }
////                    return s;
////                }
////                , s -> s));
////                System.out.println(currenciesMap.values());
////                System.out.println(testList.stream().count());
////                testList.forEach(System.out::println);
//                System.out.println(testList);
//            } else {
//                throw new Exception("Error in API Call");
//            }
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//    }

//    public void returnConversion() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the Currency Converter");
//        System.out.print("What currency are you converting from: ");
//        String toCurrency = scanner.next();
//        System.out.print("What currency are you converting to: ");
//        String fromCurrency = scanner.next();
//        new GetExchangeRates().getSpecificRateConversion(toCurrency.toUpperCase(Locale.ROOT), fromCurrency.toUpperCase(Locale.ROOT));
//    }
//
//    public void returnHistoricRates() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Historic Rates");
//        System.out.print("What currency are you converting from: ");
//        String toCurrency = scanner.next();
//        System.out.print("Date From (Ex: YYYY-MM-DD): ");
//        String dateFrom = scanner.next();
//        System.out.print("Date To (Ex: YYYY-MM-DD): ");
//        String dateTo = scanner.next();
//        new GetExchangeRates().getHistoricExchangeRate(toCurrency.toUpperCase(Locale.ROOT), dateFrom, dateTo);
//    }

    public void getCurrentRates() {
        try {
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) new URL(url).openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = inputStream.readLine()) != null) {
                    response.append(readLine);
                }
                inputStream.close();
                String responseNew = response.substring(response.toString().indexOf("JPY"))
                        .replace(",", "\n")
                        .replaceAll("[{}\"]", "");
                System.out.println("USD To:");
                System.out.println(responseNew);
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void getSpecificRateConversion(String fromCurrency, String toCurrency) {
        try {
            String apikey = "603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=" + apikey + "&base_currency=" + fromCurrency;
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputSteam = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = inputSteam.readLine()) != null) {
                    response.append(readLine);
                }
                inputSteam.close();

//                System.out.println(fromCurrency + " => " + toCurrency);

                List<String> listOfCurrencies = Arrays.asList(response
                        .substring(response.toString().indexOf("JPY"))
                        .replaceAll("[{}\"]", "")
                        .replaceAll("(:\\d*.\\d*)", "")
                        .split(","));

                List<Double> listOfValues = Arrays.stream(response
                        .substring(response.toString().indexOf("JPY"))
                        .replaceAll("[{}\"]", "")
                        .replaceAll("(\\w*:)", "")
                        .split(",")).map(Double::parseDouble).toList();

                Map<String, Double> testMap = new HashMap<>();
                for (int i = 0; i < listOfCurrencies.size(); i++) {
                    testMap.put(listOfCurrencies.get(i), listOfValues.get(i));
                }


                if (testMap.containsKey(toCurrency)) {
                    System.out.println("1 " + fromCurrency + " = " + testMap.get(toCurrency) + " " + toCurrency);
                } else {
                    System.out.println(toCurrency + " did not match.");
                }

            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getHistoricExchangeRate(String currency, String fromDate, String toDate) {
        try {
            String apikey = "603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String url = "https://freecurrencyapi.net/api/v2/historical?apikey=" + apikey +
                    "&base_currency=" + currency +
                    "&date_from=" + fromDate +
                    "&date_to=" + toDate;
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = inputStream.readLine()) != null) {
                    response.append(readLine);
                }
                inputStream.close();
                String responseNew;
                if (currency.equals("AED")) {
                    responseNew = response.substring(response.toString().indexOf(currency, response.toString().indexOf(currency) + 1))
                            .replace(",", "\n")
                            .replaceAll("[{}\"]", "");
                } else {
                    responseNew = response.substring(response.toString().indexOf("AED"))
                            .replace(",", "\n")
                            .replaceAll("[{}\"]", "");
                }
                System.out.println(currency + " To:");
                System.out.println(responseNew);


            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

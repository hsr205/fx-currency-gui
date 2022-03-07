import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetExchangeRates {

//    public void getCurrentRates() {
//        try {
//            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
//            String readLine = null;
//            HttpURLConnection conection = (HttpURLConnection) new URL(url).openConnection();
//            conection.setRequestMethod("GET");
//            int responseCode = conection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                while ((readLine = inputStream.readLine()) != null) {
//                    response.append(readLine);
//                }
//                inputStream.close();
//                String responseNew = response.substring(response.toString().indexOf("JPY"))
//                        .replace(",", "\n")
//                        .replaceAll("[{}\"]", "");
//                System.out.println("1 USD To:");
//                System.out.println(responseNew);
//            } else {
//                throw new Exception("Error in API Call");
//            }
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//    }

    public void getCurrentRates(String filepath) { // Always USD To another currency
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

                String[] responseNew = response.substring(response.toString().indexOf("JPY"))
                        .replace(",", "\n")
                        .replaceAll("[{}\"]", "")
                        .split(":");

                try (CSVWriter writer = new CSVWriter(new FileWriter(filepath))) {
                    writer.writeAll(Collections.singleton(responseNew));
                } catch (IOException e) {
                    System.out.println("Exception: " + e.getMessage());
                }

            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public String getSpecificRateConversion(String fromCurrency, String toCurrency) {
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
                    return "1 " + fromCurrency + " = " + testMap.get(toCurrency) + " " + toCurrency;
//                    System.out.println("1 " + fromCurrency + " = " + testMap.get(toCurrency) + " " + toCurrency);
                } else {
                    return toCurrency + " did not match.";
                    //                    System.out.println(toCurrency + " did not match.");

                }

            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void getHistoricExchangeRate(String currency, String fromDate, String toDate, String filePath) {
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
                if (currency.equals("AED")) {
                    String[] responseNew = response.substring(response.toString().indexOf(currency, response.toString().indexOf(currency) + 1))
                            .replace(",", "\n")
                            .replaceAll("[{}\"]", "")
                            .split(":");
                    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                        writer.writeAll(Collections.singleton(responseNew));
                    } catch (IOException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                } else {
                    String[] responseNew = response.substring(response.toString().indexOf("AED"))
                            .replace(",", "\n")
                            .replaceAll("[{}\"]", "")
                            .split(":");

                    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                        writer.writeAll(Collections.singleton(responseNew));
                    } catch (IOException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }

            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

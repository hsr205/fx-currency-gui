import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetExchangeRates {

    public static void main(String[] args) {
//        new GetExchangeRates().getCurrentRates();
//        new GetExchangeRates().getSpecificRateConversion("CNY");
//        new GetExchangeRates().getHistoricExchangeRate("DOP", "2020-10-01", "2020-10-04");
        new GetExchangeRates().getAllCurrencies();
    }

    public void getAllCurrencies () {
        try {
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) new URL(url).openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = inputStream.readLine()) != null) {
                    response.append(readLine);
                }
                inputStream.close();
                List<String> testList = new ArrayList<>(Arrays.asList(response.toString()
                                                                      .substring(response.toString().indexOf("JPY"))
                                                                      .replaceAll("[{}\"]", "")
                                                                      .replaceAll("(:\\d*.\\d*)", "")
                                                                      .split(",")));

//                Map<Object, Object> currenciesMap = testList.stream().collect(Collectors.toMap(s -> {
//                    for(int i =0; i<=testList.size(); i++) {
//                        s=String.valueOf(i);
//                    }
//                    return s;
//                }
//                , s -> s));
//                System.out.println(currenciesMap.values());
//                System.out.println(testList.stream().count());
//                testList.forEach(System.out::println);
                System.out.println(testList);
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }


    public void getCurrentRates() {
        try {
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) new URL(url).openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = inputStream.readLine()) != null) {
                    response.append(readLine);
                }
                inputStream.close();
                String responseNew = response.toString().substring(response.toString().indexOf("JPY"))
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

    public void getSpecificRateConversion(String currency) {
        try {
            String apikey = "603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey=" + apikey + "&base_currency=" + currency;
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputSteam = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = inputSteam.readLine()) != null) {
                    response.append(readLine);
                }
                inputSteam.close();
                String responseNew = response.toString().substring(response.toString().indexOf(currency));
                System.out.println(currency + " To:");
                System.out.println(responseNew);
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
                StringBuffer response = new StringBuffer();
                while ((readLine = inputStream.readLine()) != null) {
                    response.append(readLine);
                }
                inputStream.close();
                String responseNew;
                if (currency.equals("AED")) {
                    responseNew = response.toString().substring(response.toString().indexOf(currency, response.toString().indexOf(currency) + 1))
                            .replace(",", "\n")
                            .replaceAll("[{}\"]", "");
                } else {
                    responseNew = response.toString().substring(response.toString().indexOf("AED"))
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

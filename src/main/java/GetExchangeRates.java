import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetExchangeRates {

    public static void main(String[] args) {
        new GetExchangeRates().getCurrentRates();
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
                        .replaceAll("[{}\"]","");
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
                System.out.println(response);
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
                System.out.println(response);
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

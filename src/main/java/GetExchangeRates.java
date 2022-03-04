import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetExchangeRates {

    public void getCurrentRates() {
        try {
            String apikey = "603b4bc0-9bf0-11ec-9e49-a14cf50a4ccb";
            String url = "https://freecurrencyapi.net/api/v2/latest";
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.println(response);
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.println(response.toString());
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
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.println(response.toString());
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

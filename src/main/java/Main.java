import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {


    public static void main(String[] args) {

//      String query = "http://www.google.com/ig/calculator?hl=en&q=" + amount + curFrom + "=?" + curTo;
//        String query = "http://www.google.com/ig/calculator?hl=en&q=1.0UnitedStatesDollar=?Euro";
        String query = "http://www.google.com";

        String output = new GetURLContents().getUrlContents(query);
        System.out.println(output);
    }




}



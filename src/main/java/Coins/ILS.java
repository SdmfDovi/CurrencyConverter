package Coins;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ILS extends Coin {
    final double value = apiValue();

    public ILS() throws IOException {
    }

    public double getValue() {
        return value;
    }

    @Override
    public double calculate(double input) {return input * getValue();}

    public double apiValue() throws IOException {
        String url_str = "https://api.exchangerate.host/convert?from=USD&to=ils";

        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        String req_result = jsonobj.get("result").getAsString();

        double currentValue= Double.parseDouble(req_result);
        return currentValue;
    }
}
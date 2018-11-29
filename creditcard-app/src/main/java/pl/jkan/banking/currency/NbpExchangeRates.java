package pl.jkan.banking.currency;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class NbpExchangeRates implements ExchangeRates {
    public static final String CURRENCY_API_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    public NbpExchangeRates() {
    }

    @Override
    public Rate getFor(Currency currency) throws RateCantBeRetrieved {
        JsonArray jsonobj = getJsonRates();

        return mapToRates(jsonobj)
                .stream().filter(r -> r.getCode().equals(currency.getCode()))
                .findFirst().orElseThrow(()-> new RateCantBeRetrieved());
    }

    private JsonArray getJsonRates() throws RateCantBeRetrieved {
        JsonParser jp = new JsonParser();
        JsonElement root = null;
        try {
            root = jp.parse(new InputStreamReader((InputStream) establishConnection(CURRENCY_API_URL).getContent()));
        } catch (IOException e) {
            throw new RateCantBeRetrieved();
        }

        return root.getAsJsonArray().get(0).getAsJsonObject().get("rates").getAsJsonArray();
    }

    private List<Rate> mapToRates(JsonArray jsonobj) {
        List<Rate> rates = new ArrayList<>();
        jsonobj.iterator().forEachRemaining(e -> rates.add(new Rate(
                e.getAsJsonObject().get("code").getAsString(),
                e.getAsJsonObject().get("mid").getAsBigDecimal())));

        return rates;
    }

    private HttpURLConnection establishConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        return request;
    }
}

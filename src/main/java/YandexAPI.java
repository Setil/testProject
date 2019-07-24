import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class YandexAPI {
    private HttpClient httpClient;
    private static final Logger  logger = LogManager.getLogger(YandexAPI.class);
    private static final String suggestionSubPath = "/suggest/suggest-ya.cgi?" +
            /*
             * Параметры взяты из реального запроса браузера. Необходимые параметры выявлены экспериментальным путем.
             * Остальные параметры оставлены закоментированными для отладки в случае некорректной работы
             */
//            "srv=morda_ru_desktop" +
//            "&wiz=TrWth" +
//            "&uil=ru" +
//            "&fact=1" +
            "&v=4" +
//            "&icon=1" +
//            "&lr=10839" +
//            "&hl=1" +
            "&bemjson=1" +
//            "&history=1" +
//            "&html=1" +
//            "&platform=desktop" +
//            "&rich_nav=1" +
//            "&show_experiment=222" +
//            "&show_experiment=224" +
//            "&verified_nav=1" +
//            "&rich_phone=1" +
//            "&yu=3780337401532705059" +
//            "&callback=jQuery214006485254398407947_1563890584470" +
//            "&pos=6" +
//            "&suggest_reqid=378033740153270505905848312134598" +
//            "&svg=1" +
//            "&hs=0" +
            "&part=%s";

    public YandexAPI() {
        httpClient = HttpClientBuilder
                .create()
                .disableRedirectHandling()
                .disableContentCompression()
                .build();
    }



    public String getFirstSuggestionFor(String searchString){
        try {
            HttpGet httpGet = new HttpGet(
                    new URI(
                            Settings.getInstance().getBaseUrl() +
                            String.format(suggestionSubPath, URLEncoder.encode(searchString, "utf-8"))
                            )
            );
            HttpResponse response = httpClient.execute(httpGet);
            return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            logger.error(e,e);
            return "";
        }
    }
}

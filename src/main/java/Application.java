import org.json.JSONArray;
import org.json.JSONObject;
public class Application {
    public static void main(String[] args) {
        String[] searchRequests = new String[]{"Погода", "Липецк", "Лото"};
        YandexAPI api = new YandexAPI();
        for (String searchRequest : searchRequests) {
            String apiResult = api.getFirstSuggestionFor(searchRequest);
            String parsedResult = getFirstResultFromApiResponse(apiResult);
            System.out.println(String.format("Запрос: [%s], результат: [%s]",
                    searchRequest,
                    parsedResult));
        }

    }

    static String getFirstResultFromApiResponse(String apiResponseJson){
        JSONArray json = new JSONArray(apiResponseJson);
        JSONArray firstItem = json.getJSONArray(1); //0й - введенный текст
        JSONObject suggestion = firstItem.getJSONArray(0).getJSONObject(2);
        JSONArray suggestionParts = suggestion.getJSONArray("bemjson");
        StringBuilder resultBuilder = new StringBuilder();
        boolean isFirst = true;
        for (Object suggestionPart : suggestionParts) {
            JSONObject part = (JSONObject) suggestionPart;
            if (part.getString("elem").equals("icon")){
                continue;
            }
            if (!isFirst){
                resultBuilder.append(" ");
            }
            isFirst = false;
            resultBuilder.append(part.getString("content"));
        }
        return resultBuilder.toString();
    }
}

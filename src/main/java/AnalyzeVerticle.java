import analyze.WordList;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class AnalyzeVerticle extends AbstractVerticle {
    public static String ANALYZE = "analyze.address";
    private WordList wordList = WordList.getInstance();

    public AnalyzeVerticle() throws IOException {
    }

    @Override
    public void start() {
        vertx.eventBus().consumer(ANALYZE, msg -> {
            JsonObject body = (JsonObject) msg.body();
            String word = body.getString("text");
            String value = wordList.getNumericallyClosestWord(word);
            String lexical = wordList.getLexicallyClosestWord(word);
            try {
                wordList.addWord(word);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            JsonObject json = new JsonObject();
            json.put("value", value);
            json.put("lexical", lexical);
            msg.reply(json);
        });
    }
}

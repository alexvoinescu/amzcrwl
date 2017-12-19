package org.pyro.amzcrawl.actions;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pyro.amzcrawl.model.Action;
import org.pyro.amzcrawl.model.Keyword;
import org.pyro.amzcrawl.responses.WebResponsable;
import org.pyro.amzcrawl.results.KeywordSearchResultRow;

import java.io.IOException;
import java.util.*;

/**
 * Created by alex on 19.12.2017.
 */
public class KeywordSearch extends AbstractAction implements WebActionable {

    private Map<String, Map> results = new HashMap<>();

    public KeywordSearch(Action action) {
        super(action);
    }

    @Override
    public WebActionable run(Action action) {

        String urlToParse = this.url;
        String refToParse = this.ref;
        Document document;
        List<KeywordSearchResultRow> allResults = new ArrayList<>();
        try {
            for (int i = 0; i < 1; i++) {
                Connection connection = this.createConnection(urlToParse, refToParse);
                document = connection.get();
                List<KeywordSearchResultRow> titles = this.getSearchResults(document);
                allResults.addAll(titles);
                String nextPage = document.select("a#pagnNextLink").first().attr("href");
                urlToParse = "https://www.amazon.com" + nextPage;
                refToParse = urlToParse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(KeywordSearchResultRow row : allResults) {
            System.out.println(row);
        }

        return this;
    }

    private List<KeywordSearchResultRow> getSearchResults(Document doc) {
        List<KeywordSearchResultRow> results = new ArrayList<>();
        Elements titles = doc.select("h2.s-access-title");
        for (Element title:titles) {
            Element sponsored = title.getElementsContainingText("Sponsored").first();
            boolean isSponsored = false;
            if(sponsored != null) {
                title.select("span").remove();
                isSponsored = true;
            }
            KeywordSearchResultRow result = new KeywordSearchResultRow(doc.baseUri(), title.html(), isSponsored);
            results.add(result);
        }
        return results;
    }

    @Override
    public WebResponsable getResults(Action action) {
        return null;
    }
}

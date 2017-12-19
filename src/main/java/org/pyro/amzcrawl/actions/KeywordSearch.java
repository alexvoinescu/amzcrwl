package org.pyro.amzcrawl.actions;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pyro.amzcrawl.model.Action;
import org.pyro.amzcrawl.model.ActionResults;
import org.pyro.amzcrawl.responses.WebResponsable;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        String results = "";
        Document document;
        try {
            for (int i = 0; i < 10; i++) {
                Connection connection = this.createConnection(urlToParse, refToParse);

                document = connection.get();
                System.out.println("Page " + i + " -> " + urlToParse);
                results += "Page " + i + "/ url: "+urlToParse+"; " + System.lineSeparator();
                results += this.getSearchResults(document);
                String nextPage = document.select("a#pagnNextLink").first().attr("href");

                urlToParse = "https://www.amazon.com" + nextPage;
                refToParse = urlToParse;
                //results += "----------------------------------\r\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ActionResults actionResults = new ActionResults();
        actionResults.setResponse(results);
        this.action.getActionResults().add(actionResults);

        System.out.println(actionResults.getResponse());

        return this;
    }

    private String getSearchResults(Document doc) {
        String results = "";
        //s-access-title
        Elements titles = doc.select("h2.s-access-title");

        for (Element title : titles) {
            results += title.html() + "; " + System.lineSeparator();
        }

        return results;
    }

    @Override
    public WebResponsable getResults(Action action) {
        return null;
    }
}

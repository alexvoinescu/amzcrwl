package org.pyro.amzcrawl.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchController {

    private Map<String, Map> results = new HashMap<>();

    @GetMapping("/crawl")
    public void getAmazon() {
        int maxPages = 10;

        Document doc;
        String url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=wrist+wraps";
        String ref = "https://www.google.ro/search?dcr=0&source=hp&ei=ako4WqSeO8mkwQLqs4XADQ&q=amazon&oq=amazon&gs_l=psy-ab.3...689.1181.0.1462.7.6.0.0.0.0.94.94.1.1.0....0...1c.1.64.psy-ab..6.1.94.0..0j35i39k1j0i67k1.0.0k2hsVIWCH0";

        try {
            for(int i=0; i<maxPages; i++) {
                doc = this.createConnection(url, ref).get();
                System.out.println("Page " + i + " -> " + url);

                String results = this.getSearchResults(url, ref);
                String nextPage = doc.select("a#pagnNextLink").first().attr("href");

                Map pgResults = new HashMap<String, String>();
                pgResults.put("url", url);
                pgResults.put("nextPage", nextPage);
                pgResults.put("results", results);

                this.results.put("page " + i, pgResults);

                url = "https://www.amazon.com" + nextPage;
                ref = url;
                System.out.println(results);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(String url, String ref) {
        return Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate, br")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:57.0) Gecko/20100101 Firefox/57.0")
                .followRedirects(true)
                .referrer(ref);
    }

    private String getSearchResults(String url, String ref) {
        String results = "";
        Document doc;
        try {
            doc = this.createConnection(url, ref).get();
            //s-access-title
            Elements titles = doc.select("h2.s-access-title");

            for(Element title: titles) {
                results += title.html() + "\r\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}

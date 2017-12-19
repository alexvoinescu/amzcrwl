package org.pyro.amzcrawl.results;

public class KeywordSearchResultRow {
    private final String url;
    private final String title;
    private final Boolean sponsored;

    public KeywordSearchResultRow(String url, String title, Boolean sponsored) {
        this.url = url;
        this.title = title;
        this.sponsored = sponsored;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    @Override
    public String toString() {
        return this.title + ", " + this.sponsored + ", " + this.url;
    }
}

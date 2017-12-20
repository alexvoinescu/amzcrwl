package org.pyro.amzcrawl.results;

import org.jsoup.nodes.Element;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class KeywordSearchResultRow {
    private String url = null;
    private String title = null;
    private Boolean sponsored = false;
    private Float stars = null;
    private Integer reviews = null;
    private Float price = null;
    private final Element element;

    public KeywordSearchResultRow(Element element) {
        this.element = element;
        try {
            this.processElement();
        } catch (NullPointerException nullEx) {
            System.out.println("Null - Error with " + nullEx.getMessage());
            System.out.println(nullEx.getStackTrace().toString());
        } catch (Exception ex) {
            System.out.println("Ex - Error with " + ex.getMessage());
            System.out.println(ex.getStackTrace().toString());
        }
    }

    private void processElement() throws ParseException {
        Element titleEl = this.element.select("h2.s-access-title").first();
        titleEl.select("span.a-offscreen").remove();
        this.title = titleEl.html();
        Element sponsored = this.element.getElementsContainingText("Sponsored").first();
        if(sponsored != null) {
            this.sponsored = true;
        }
        this.url = this.element.select("a.s-access-detail-page").html();

        String stars = this.element.select("i.a-icon-star").first().select("span.a-icon-alt").first().html();
        String[] aStars = stars.split(" out of");
        this.stars = Float.parseFloat(aStars[0].trim());

        String priceWhole = this.element.select("span.sx-price-whole").first().html() +"."+this.element.select("sup.sx-price-fractional").first().html();
        this.price = Float.parseFloat(priceWhole);

        String noReviews = this.element.select("div.a-span5").select("a.a-size-small").html();
        //this.reviews = Integer.parseInt(noReviews.replace(',','.'));
        this.reviews = NumberFormat.getNumberInstance(Locale.US).parse(noReviews).intValue();
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

    public Float getStars() {
        return stars;
    }

    public Integer getReviews() {
        return reviews;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "'" + this.title + "', " + this.sponsored + ", " + this.price + ", " + this.reviews + ", " + this.stars;
    }
}

package org.pyro.amzcrawl.actions;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.pyro.amzcrawl.model.Action;

/**
 * Created by alex on 19.12.2017.
 */
public abstract class AbstractAction implements WebActionable {

    protected String url;
    protected String ref = "https://www.google.ro/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwjSiN-2vJbYAhUIEewKHQuTAKkQFgguMAA&url=https%3A%2F%2Fwww.amazon.com%2F&usg=AOvVaw0dQbbmFVKaWRLUIdNVceg-";
    protected String header = "Accept-Encoding";
    protected String headerSecond = "gzip, deflate, br";
    protected String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:57.0) Gecko/20100101 Firefox/57.0";

    protected final Action action;

    protected Connection createConnection(String url, String ref) {
        return Jsoup.connect(url)
                .header(this.header, this.headerSecond)
                .userAgent(this.userAgent)
                .followRedirects(true)
                .referrer(this.ref);
    }

    public AbstractAction(Action action) {
        this.action = action;
        this.url = action.getUrl();
    }



}

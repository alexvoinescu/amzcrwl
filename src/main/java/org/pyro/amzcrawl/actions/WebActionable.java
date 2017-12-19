package org.pyro.amzcrawl.actions;

import org.pyro.amzcrawl.model.Action;
import org.pyro.amzcrawl.responses.WebResponsable;

/**
 * Created by alex on 19.12.2017.
 */
public interface WebActionable {

    public WebActionable run(Action action);
    public WebResponsable getResults(Action action);

}

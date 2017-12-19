package org.pyro.amzcrawl.actions;

import org.pyro.amzcrawl.model.Action;
import org.pyro.amzcrawl.responses.WebResponsable;

/**
 * Created by alex on 19.12.2017.
 */
public class ProductPageAction extends AbstractAction implements WebActionable {


    public ProductPageAction(Action action) {
        super(action);
    }

    @Override
    public WebActionable run(Action action) {
        return null;
    }

    @Override
    public WebResponsable getResults(Action action) {
        return null;
    }
}

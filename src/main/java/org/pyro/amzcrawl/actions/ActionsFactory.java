package org.pyro.amzcrawl.actions;

import org.pyro.amzcrawl.model.Action;

/**
 * Created by alex on 19.12.2017.
 */
public class ActionsFactory {
    public static WebActionable getActionType(Action action) {
        WebActionable actionable;
        switch(action.getType()) {
            case KW_SEARCH:
                actionable = new KeywordSearch();
                break;
            case PRODUCT_PAGE:
                actionable = new ProductPageAction();
                break;
            default:
                actionable = new KeywordSearch();
                break;
        }
        return actionable;
    }
}

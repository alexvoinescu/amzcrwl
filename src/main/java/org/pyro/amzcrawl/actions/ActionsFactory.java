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
                actionable = new KeywordSearch(action);
                break;
            case PRODUCT_PAGE:
                actionable = new ProductPageAction(action);
                break;
            default:
                actionable = new KeywordSearch(action);
                break;
        }
        return actionable;
    }
}

package processor;

import model.Request;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public interface PageProcessor {
    String doRequest(Request request);
}

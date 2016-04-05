package processor;

import model.Request;

/**
 * All URL handlers must implement this interface
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public interface PageProcessor {
    String doRequest(Request request);
}

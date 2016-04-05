package processor;

import model.Request;
import server.session.SessionHolder;

/**
 * if the user wants to log in again,
 * his request will handle this class
 * @author Gladush Ivan
 * @since 31.03.16.
 */
public class ExitProcessor implements PageProcessor {

    private String redirectHeader="HTTP/1.1 302 Found\n" +
            "Location: http://localhost:8080\n";

    @Override
    public String doRequest(Request request) {
        SessionHolder.removeSession(request.getSessionId());
        return redirectHeader;
    }
}

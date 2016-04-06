package processor;

import model.Request;
import model.ResponseBuilder;
import server.session.SessionHolder;

/**
 * if the user wants to log in again,
 * his request will handle this class
 * @author Gladush Ivan
 * @since 31.03.16.
 */
public class ExitProcessor implements PageProcessor {

    private static final String PAGE_FOUND = "HTTP/1.1 302 Found";
    private static final String REDIRECT_LOCATION = "Location: http://localhost:8080";


    @Override
    public String doRequest(Request request) {
        SessionHolder.removeSession(request.getSessionId());
        return new ResponseBuilder().addHeader(PAGE_FOUND).addHeader(REDIRECT_LOCATION).build();
    }
}

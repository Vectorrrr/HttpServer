package processor;

import model.Request;
import model.ResponseBuilder;
import holder.session.SessionHolder;

import static processor.Header.CLOSE_CONNECTION;
import static processor.Header.PAGE_FOUND;
import static processor.Header.REDIRECT_TO_ROOT_LOCATION;
/**
 * if the user wants to log in again,
 * his request will handle this class
 * @author Gladush Ivan
 * @since 31.03.16.
 */
public class ExitProcessor implements PageProcessor {

    @Override
    public String doRequest(Request request) {
        SessionHolder.removeSession(request.getSessionId());
        return new ResponseBuilder().addHeader(PAGE_FOUND).addHeader(REDIRECT_TO_ROOT_LOCATION).addHeader(CLOSE_CONNECTION).build();
    }
}

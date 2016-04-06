package processor;

import model.Request;
import model.ResponseBuilder;
import static  processor.Header.HTTP_NOT_FOUND;
import static processor.Header.SET_COOKIE;

/**
 * When the URL for the handler was not found, then the URL
 * will handle this class
 * @author Gladush Ivan
 * @since 05.04.16.
 */
public class NotFound  implements  PageProcessor{

    @Override
    public String doRequest(Request request) {
        return new ResponseBuilder().addHeader(HTTP_NOT_FOUND).addHeader(SET_COOKIE,-1).build();
    }
}

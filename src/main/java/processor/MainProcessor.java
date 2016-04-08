package processor;

import holder.PageHolder;
import loader.PropertyLoader;
import model.Request;
import model.ResponseBuilder;
import holder.session.SessionHolder;

import static processor.Header.*;

/**
 * Home page of the site that allows the user to log out
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class MainProcessor implements PageProcessor {
    private  String body = PageHolder.getPage(PropertyLoader.property("main.page"));

    @Override
    public String doRequest(Request request) {
        int id = request.getSessionId();
        if (SessionHolder.containsSession(id)) {
            String bodyResponse = String.format(body, request.getUserInformation());
            return new ResponseBuilder().addBody(bodyResponse).addHeader(HTTP_OK).addHeader(SET_COOKIE, id).
                    addHeader(CONTENT_LENGTH, bodyResponse.length()).addHeader(CLOSE_CONNECTION).build();
        }
        return new ResponseBuilder().addHeader(PAGE_FOUND).addHeader(REDIRECT_TO_ROOT_LOCATION).addHeader(SET_COOKIE, -1).addHeader(CLOSE_CONNECTION).build();
    }
}

package processor;

import loader.PageLoader;
import model.Request;
import model.ResponseBuilder;
import server.session.SessionHolder;

import static processor.Header.*;
/**
 * Home page of the site that allows the user to log out
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class MainProcessor implements PageProcessor {
    private static String body= PageLoader.getPage("main.page.txt");

    @Override
    public String doRequest(Request request) {
        int id = request.getSessionId();
        if (SessionHolder.containsSession(id)) {
            String bodyResponce = String.format(body, request.getUserInformation());
            return new ResponseBuilder().addBody(bodyResponce).addHeader(HTTP_OK).addHeader(SET_COOKIE, id).
                    addHeader(CONTENT_LENGTH, bodyResponce.length()).addHeader(CLOSE_CONNECTION).build();
        }
        return new ResponseBuilder().addHeader(PAGE_FOUND).addHeader(REDIRECT_TO_ROOT_LOCATION).build();

    }
}

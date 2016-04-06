package processor;

import model.Request;
import model.ResponseBuilder;
import model.TypeRequest;
import model.User;
import server.session.SessionHolder;

/**
 * Class responsible for user authentication processing on
 * the site if the user has logged in, it will automatically
 * be redirected to the main page
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class LoginProcessor implements  PageProcessor {
    private static final String PAGE_FOUND = "HTTP/1.1 302 Found";
    private static final String REDIRECT_PAGE = "Location: http://localhost:8080/Main";
    private static final String SET_COOKIE = "Set-Cookie: session=%d;";
    private static final String HTTP_OK = "HTTP/1.1 200 OK";
    private static final String CONTENT_LENGTH = "Content-Length:  %d";
    private static final String CLOSE_CONNECTION = "Connection: close";

    private String body = "<html>\n" +
            "<head><title>Login Page</title></head>\n" +
            "<body>\n" +
            "\n" +
            "<form method=\"post\"><h2>Enter your information</h2>\n" +
            "    <p>Name:<input type=\"text\" name=\"name\" required autocomplete=\"off\"></p>\n" +
            "    <p>Surname: <input type=\"text\" name=\"surname\" required autocomplete=\"off\"></p>\n" +
            "    <input type=\"submit\" value=\"Submit\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";


    @Override
    public String doRequest(Request request) {
        if (TypeRequest.GET.equals(request.typeRequest())) {
            return doGet(request);
        }
        return doPost(request);
    }

    private String doGet(Request request) {
        int sesId = request.getSessionId();
        if (SessionHolder.containsSession(sesId)) {
            redirectPageAnswer(sesId);
        }
        return new ResponseBuilder().addHeader(HTTP_OK).addHeader(String.format(CONTENT_LENGTH, body.length())).
                addHeader(CLOSE_CONNECTION).addBody(body).build();

    }

    private String doPost(Request request) {
        User user = createUser(request);
        return redirectPageAnswer(SessionHolder.addSession(user));
    }

    private User createUser(Request request) {
        return new User(request.getParameter("name"), request.getParameter("surname"));
    }

    private String redirectPageAnswer(int sessionId) {
        return new ResponseBuilder().addHeader(PAGE_FOUND).addHeader(REDIRECT_PAGE).
                addHeader(String.format(SET_COOKIE, sessionId)).build();
    }

}
